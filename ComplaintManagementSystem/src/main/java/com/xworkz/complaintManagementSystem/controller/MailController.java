////package com.xworkz.complaintManagementSystem.controller;
////
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.beans.factory.annotation.Value;
////import org.springframework.mail.javamail.JavaMailSender;
////import org.springframework.mail.javamail.MimeMessageHelper;
////import org.springframework.stereotype.Service;
////
////import javax.mail.MessagingException;
////import javax.mail.internet.MimeMessage;
////
////@Service
////    public class MailController {
////
////        @Autowired
////        private JavaMailSender mailSender;
////
////        @Value("${spring.mail.username}")
////        private String fromEmail;
////
////        public void sendPassword(String to, String password) {
////            MimeMessage message = mailSender.createMimeMessage();
////            MimeMessageHelper helper = new MimeMessageHelper(message);
////
////            try {
////                helper.setTo("pruthvik1014@gmail.com");
////                helper.setFrom("aishupatil451@gmail.com");
////                helper.setSubject("Your Registration Password");
////                helper.setText("Your generated password is: " + password);
////                mailSender.send(message);
////            } catch (MessagingException e) {
////                e.printStackTrace();
////}
////
////    }
////}
//
//
//
//
//
//
//package com.xworkz.complaintManagementSystem.controller;
//
//import com.xworkz.complaintManagementSystem.dto.SignUpDto;
//import com.xworkz.complaintManagementSystem.model.service.SignUpService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.security.SecureRandom;
//
//@Controller
//@RequestMapping("/")
//public class SignInController {
//
//    @Autowired
//    private SignUpService signUpService;
//
//    public SignInController(){
//        System.out.println("Running signup controller");
//    }
//
//    @PostMapping("/signup")
//    public String onSignUp(@Valid SignUpDto signUpDto, BindingResult bindingResult, Model model, @RequestParam("email")String email){
//        System.out.println("Running onSignUp method in SignUp controller");
//
//        if(bindingResult.hasErrors()){
//            System.err.println("Signup dto has invalid data" + signUpDto);
//            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
//            model.addAttribute("errors", bindingResult.getAllErrors());
//            return "SignUp";
//        }
//
//        // Generate a random password
//        String generatedPassword = generateRandomPassword();
//        signUpDto.setPassword(generatedPassword);
//
//
//        boolean saved = this.signUpService.save(signUpDto);
//        if(saved){
//            System.out.println("Signup service is saved successfully" + signUpDto);
//            System.out.println("Generated Password: " + generatedPassword);
//            // Send email with the generated password
//            String subject ="Welcome to our complaint management system";
//
//            String body ="Hi"+signUpDto.getFname()+",\n\n your regeisteraation is successful. your password is "+ signUpDto.getPassword();
//
//            signUpService.sendPasswordEmail(email,subject,body);
//
//            model.addAttribute("msg","Signup successfull. please chech your email for your password");
//            return "Login";
//
//        }else {
//            System.err.println("Signup service is not saved" + signUpDto);
//        }
//        model.addAttribute("name", signUpDto.getFname() + "Signup successfully");
//        model.addAttribute("password", generatedPassword);
//
//        return "Login";
//    }
//
//    private String generateRandomPassword() {
//        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
//        SecureRandom random = new SecureRandom();
//        StringBuilder password = new StringBuilder();
//
//        for (int i = 0; i < 12; i++) {
//            int index = random.nextInt(chars.length());
//            password.append(chars.charAt(index));
//        }
//        return password.toString();
//    }
//
//    @PostMapping("/login")
//    public String onLogin(@RequestParam String email, @RequestParam String password, Model model) {
//        System.out.println("Login method is running");
//
//        System.out.println("Email :" + email);
//        System.out.println("Password :" + password);
//
//        SignUpDto signUpDto = this.signUpService.findByEmailAndPassword(email, password);
//        if (signUpDto != null) {
//            System.out.println("Login successful for email: " + email);
//            model.addAttribute("message", signUpDto.getFname() + " Login is successful");
//            return "Success";
//        } else {
//
//
//            signUpService.incrementFailedAttempts(email);
//            int failedAttempts = signUpService.getFailedAttempts(email);
//            System.out.println("Failed attempts for " + email + ": " + failedAttempts);
//            if (failedAttempts >= 3) {
//                model.addAttribute("error", "Your account is locked due to too many failed attempts.");
//                System.out.println(email+"  : Your Account is locked due to too many failed attempts with wrong password ");
//                model.addAttribute("disableButton",true);
//            } else {
//                model.addAttribute("error", "Invalid email id and password. Attempts: " + failedAttempts);
//                System.out.println("Invalid email id and password");
//                model.addAttribute("disableButton",false);
//            }
//            model.addAttribute("failedAttempts",failedAttempts);
//            return"Login";
//
//        }
//    }
//
//
//
//
//
//
//}




//  private static final String UPLOAD_DIR = "C:\\Users\\Anil\\Desktop\\ImageUpload\\";
//    @Autowired
//    private EditInfoService editInfoService;
//    @Autowired
//    private ImageService imageService;
//    @Autowired
//    private SignUpService signUpService;
//
//    public FileUploadController() {
//        System.out.println("Created FileUploadController");
//    }
//
//    @PostMapping("/editProfile")
//    public String uploadFile(@RequestParam("file") MultipartFile multipartFile,
//                             SignUpDTO signUpDTO,
//                             Model model, HttpSession session) {
//        if (multipartFile.isEmpty()) {
//            model.addAttribute("message", "Please select a file to upload");
//            return "ProfileEdit";
//        }
//        try {
//            String originalFilename = multipartFile.getOriginalFilename();
//            String newFilename = signUpDTO.getEmail() + "_" + originalFilename;
//            Path path = Paths.get(UPLOAD_DIR, newFilename);
//            Files.write(path, multipartFile.getBytes());
//            signUpDTO.setProfileImage(newFilename);
//
//            Optional<SignUpDTO> optionalUser = signUpService.findByEmail(signUpDTO.getEmail());
//            if (!optionalUser.isPresent()) {
//                model.addAttribute("message", "User not found.");
//                return "ProfileEdit";
//            }
//
//            SignUpDTO user = optionalUser.get();
//
//            // Deactivate all previous images for the user
//            imageService.deactivateAllImagesForUser(user.getId());
//
//            // Save new image details with status "Yes"
//            ImageDownloadDTO imageDTO = new ImageDownloadDTO();
//            imageDTO.setImageName(newFilename);
//            imageDTO.setImageType(multipartFile.getContentType());
//            imageDTO.setImageSize(multipartFile.getSize());
//            imageDTO.setUserId(user.getId());
//            imageDTO.setCreatedAt(LocalDateTime.now());
//            imageDTO.setCreatedBy(signUpDTO.getEmail());
//            imageDTO.setModifiedAt(LocalDateTime.now());
//            imageDTO.setModifiedBy(signUpDTO.getEmail());
//            imageDTO.setStatus("Yes");
//            imageService.saveImageDetails(imageDTO);
//
//            user.setProfileImage(newFilename);
//            signUpService.saveUser(user);
//
//            signUpDTO.setProfileImage(newFilename);
//            SignUpDTO updatedDTO = editInfoService.updateUserProfile(signUpDTO.getEmail(), signUpDTO);
//            if (updatedDTO != null) {
//                model.addAttribute("message", "Profile updated successfully!");
//            } else {
//                model.addAttribute("message", "Profile update failed. User not found.");
//            }
//
//            // Set session for image to display
//            String imageUrl = "/images/" + newFilename;
//            session.setAttribute("profileImage", imageUrl);
//
//            model.addAttribute("dto", signUpDTO);
//            model.addAttribute("image", imageDTO);
//
//        } catch (IOException e) {
//            model.addAttribute("message", "File upload failed: " + e.getMessage());
//        }
//        return "ProfileEdit";
//    }