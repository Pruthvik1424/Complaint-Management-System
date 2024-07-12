package com.xworkz.complaintManagementSystem.controller;

import com.xworkz.complaintManagementSystem.dto.ProfileImageUploadDto;
import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import com.xworkz.complaintManagementSystem.model.repo.ViewProfileRepo;
import com.xworkz.complaintManagementSystem.model.service.SignUpService;
import com.xworkz.complaintManagementSystem.model.service.ViewProfileService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping("/")
@SessionAttributes("signUpDto")
public class EditProfileController {

    private static final String UPLOAD_DIR = "C:\\Users\\User\\Desktop\\Complaint Management System\\ImageUpload";
    private static final Logger log = LoggerFactory.getLogger(EditProfileController.class);

    @Autowired
    private ViewProfileService viewProfileService;

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private ViewProfileRepo viewProfileRepo;

    public EditProfileController() {
        EditProfileController.log.info("Running EditProfileController...");
    }

    @ModelAttribute("signUpDto")
    public SignUpDto getSignUpDto(HttpSession session) {
        SignUpDto signUpDto = (SignUpDto) session.getAttribute("signUpDto");
        if (signUpDto == null) {
            signUpDto = new SignUpDto(); // Return a new SignUpDto if not present in the session
        }
        return signUpDto;
    }

    @GetMapping("/edit-profile")
    public String editProfileForm(Model model, HttpSession session) {
        String email = viewProfileService.getSignInUserEmail();
        if (email == null || email.isEmpty()) {
            log.info("No signed-in user email found in session");
            model.addAttribute("msg1", "No signed-in user found");
            return "ProfileEdit";
        }

        log.info("Received request to edit profile for email: " + email);
        SignUpDto signUpDto = viewProfileService.findByUserEmail(email);
        if (signUpDto != null) {
            log.info("User found: " + signUpDto);
            session.setAttribute("signUpDto", signUpDto); // Set session attribute
            String imageUrl = "/images/" + signUpDto.getProfileImage();
            model.addAttribute("profileImage", imageUrl);
            model.addAttribute("signupdto", signUpDto);
            return "ProfileEdit";
        } else {
            log.info("User not found for email: " + email);
            model.addAttribute("msg1", "User not found");
            return "ProfileEdit";
        }
    }

    @PostMapping("/edit-profile")
    public String updateProfile(@Valid @ModelAttribute("signUpDto") SignUpDto signUpDto,
                                @RequestParam("file") MultipartFile multipartFile,
                                BindingResult result, Model model, HttpSession httpSession) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("error", "Profile update failed due to validation errors");
            return "ProfileEdit";
        }

        if (multipartFile.isEmpty()) {
            model.addAttribute("msg1", "Please select an image to upload");
            return "ProfileEdit";
        }

        try {
            String originalFileName = multipartFile.getOriginalFilename();
            String newFileName = signUpDto.getEmail() + "_" + originalFileName;
            Path path = Paths.get(UPLOAD_DIR, newFileName);
            Files.write(path, multipartFile.getBytes());
            signUpDto.setProfileImage(newFileName);

            Optional<SignUpDto> optionalUser = Optional.ofNullable(signUpService.findByEmail(signUpDto.getEmail()));
            if (!optionalUser.isPresent()) {
                model.addAttribute("msg2", "User not found");
                return "ProfileEdit";
            }

            SignUpDto user = optionalUser.get();

            // Save new image details with status "Yes"
            ProfileImageUploadDto profileImageUploadDto = new ProfileImageUploadDto();
            profileImageUploadDto.setImageName(newFileName);
            profileImageUploadDto.setImageType(multipartFile.getContentType());
            profileImageUploadDto.setImageSize(String.valueOf(multipartFile.getSize()));
            profileImageUploadDto.setId(user.getId());

            viewProfileRepo.saveProfileImage(profileImageUploadDto);

            signUpDto.setProfileImage(newFileName);
            SignUpDto updatedDto = viewProfileService.updateEditedUser(signUpDto);
            if (updatedDto != null) {
                model.addAttribute("msg", "Profile updated successfully");
                httpSession.setAttribute("signUpDto", updatedDto); // Update session attribute
                httpSession.setAttribute("profileImageUrl", "/images/" + newFileName); // Add image URL to session
            } else {
                model.addAttribute("msg", "Profile update failed, user not found");
            }

            // Set session for image to display
            // Add the profile image URL to the model
            String imageUrl = "/images/" + signUpDto.getProfileImage(); // Adjust the path if necessary
            model.addAttribute("profileImage", imageUrl);

            model.addAttribute("dto", signUpDto);
            model.addAttribute("image", profileImageUploadDto);

        } catch (IOException e) {
            model.addAttribute("message", "File upload failed: " + e.getMessage());
        }
        return "ProfileEdit";
    }

    @GetMapping("download")
    public String download(HttpServletResponse response, @RequestParam String fileName)
    {
        log.info("Running download method...");
        response.setContentType("image/jpeg");
        File file=new File("C:\\Users\\User\\Desktop\\Complaint Management System\\ImageUpload",fileName);
        try {
            InputStream buffer=new BufferedInputStream(new FileInputStream(file));
            ServletOutputStream outputStream =response.getOutputStream();
            IOUtils.copy(buffer,outputStream);
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
            //throw new RuntimeException(e);
        }
        return "index";

    }

    @GetMapping("/images/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
            UrlResource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body((Resource) resource);
            } else {
                throw new RuntimeException("Could not read file: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}

