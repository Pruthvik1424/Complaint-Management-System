package com.xworkz.complaintManagementSystem.controller;

import com.xworkz.complaintManagementSystem.dto.ProfileImageUploadDto;
import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import com.xworkz.complaintManagementSystem.model.service.ImageUploadService;
import com.xworkz.complaintManagementSystem.model.service.ProfileEditService;
import com.xworkz.complaintManagementSystem.model.service.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping("/")
@SessionAttributes({"signUpDto", "profileImageUploadDto"})
@Slf4j
public class EditProfileController {

    private static final String UPLOAD_DIR = "C:\\Users\\User\\Desktop\\Complaint Management System\\ImageUpload";
    private static final Logger log = LoggerFactory.getLogger(EditProfileController.class);


    @Autowired
    private ProfileEditService profileEditService;

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private ImageUploadService imageUploadService;

    @Autowired
    private HttpSession httpSession;

    public EditProfileController() {
        EditProfileController.log.info("Running EditProfileController...");
    }

    @GetMapping("/edit-profile")
    public String editProfileForm(@RequestParam("email") String email,Model model, HttpSession session) {
//        String signedInUserEmail = profileEditService.getSignedInUserEmail();
        System.out.println(email);
        SignUpDto signUpDto = signUpService.findByEmail(email);
        System.out.println("controller "+signUpDto);
//        if (signedInUserEmail == null && signedInUserEmail.equals(email)) {
      if (signUpDto != null && signUpDto.getEmail().equalsIgnoreCase(email)){
//            SignUpDto signUpDto = profileEditService.getUserDetails(email);
//            log.info("No signed-in user email found in session");
                httpSession.setAttribute("signUpDto",signUpDto);
                model.addAttribute("editSignUpDto", signUpDto);
                return "ProfileEdit";

        }

        model.addAttribute("error","Error fetching user details");
        return "ProfileEdit";
//        log.info("Received request to edit profile for email: " + email);
//        SignUpDto signUpDto = profileEditService.getUserDetails(email);
//        if (signUpDto != null) {
//            log.info("User found: " + signUpDto);
//            session.setAttribute("signUpDto", signUpDto);  // Set session attribute
//            model.addAttribute("signUpDto", signUpDto);
//            return "ProfileEdit";
//        } else {
//            log.info("User not found for email: " + email);
//            model.addAttribute("msg1", "User not found");
//            return "ProfileEdit";
//        }
    }

    @PostMapping("/edit-profile")
    public String updateProfile( SignUpDto signUpDto,
                                @RequestParam("email") String muEmail,
                                @RequestParam("multipartFile") MultipartFile multipartFile,
                                BindingResult result, Model model, HttpSession session) throws IOException {
//        String email = viewProfileService.getSignInUserEmail();


        System.out.println("data from the client "+signUpDto);
        if (result.hasErrors()) {
            model.addAttribute("error", "Profile update failed due to validation errors");
            return "ProfileEdit";
        }

        // Retain the original email
        SignUpDto existingUser = (SignUpDto) session.getAttribute("signUpDto");
        if (existingUser != null) {
            signUpDto.setEmail(existingUser.getEmail());
        }

        if (multipartFile.isEmpty()) {
            model.addAttribute("msg1", "Please select an image to upload");
            return "ProfileEdit";
        }

        try {
            String newFileName = null;
            if (multipartFile != null && !multipartFile.isEmpty()) {
                String originalFileName = multipartFile.getOriginalFilename();
                newFileName = signUpDto.getEmail()+ "_" + originalFileName;
                Path path = Paths.get(UPLOAD_DIR, newFileName);
                log.info("path: {}", path);
                Files.write(path, multipartFile.getBytes());
                signUpDto.setProfileImage(newFileName);

                //hereactive inactive

                Optional<ProfileImageUploadDto> existingImage = imageUploadService.getImageDetailsByUserId(signUpDto.getId());

                ProfileImageUploadDto profileImageUploadDto = new ProfileImageUploadDto();
                profileImageUploadDto.setImageName(newFileName);
                profileImageUploadDto.setImageName(originalFileName);
                profileImageUploadDto.setImageType(multipartFile.getContentType());
                profileImageUploadDto.setImageSize(String.valueOf(multipartFile.getSize()));
                profileImageUploadDto.setId(signUpDto.getId());

                if (existingImage.isPresent()) {
                    imageUploadService.saveImageDetails(profileImageUploadDto);
                } else {
                    imageUploadService.saveImageDetails(profileImageUploadDto);
                }
            }


            SignUpDto updatedUserData = profileEditService.updateUserDetails(signUpDto);
            if (updatedUserData != null) {
                session.setAttribute("signUpDto", updatedUserData); // Update session attribute
                model.addAttribute("signUpDto", updatedUserData);
                model.addAttribute("msg", "Profile updated successfully");

                session.setAttribute("fname", updatedUserData.getFname());
                session.setAttribute("lname", updatedUserData.getLname());
                //session.setAttribute("email", updatedUserData.getEmail());
                session.setAttribute("mobilenumber", updatedUserData.getMobilenumber());
                session.setAttribute("alternatemobilenumber", updatedUserData.getAlternatemobilenumber());
                session.setAttribute("address", updatedUserData.getAddress());

                if (newFileName != null) {
                    String imageurl = "/images/" + newFileName;
                    session.setAttribute("profileImage", imageurl);
                    model.addAttribute("imageurl", imageurl);
                }

                // Display in console
                log.info("Image upload");
                log.info("file getName: {}", multipartFile.getName());
                log.info("file getContentType: {}", multipartFile.getContentType());
                log.info("file getResource: {}", multipartFile.getResource());
                log.info("file getOriginalFilename: {}", multipartFile.getOriginalFilename());
                log.info("File uploaded: {}, ContentType: {}", multipartFile.getOriginalFilename(), multipartFile.getContentType());
                return "ProfileEdit"; // Redirect to edit profile page
            } else {
                model.addAttribute("msg", "Profile update failed, user not found");
            }
        } catch (IOException e) {
            model.addAttribute("message", "File upload failed: " + e.getMessage());
            log.error("Error uploading file", e);
        }
        return "ProfileEdit";
    }
}
