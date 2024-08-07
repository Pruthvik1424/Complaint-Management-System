package com.xworkz.complaintManagementSystem.controller;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import com.xworkz.complaintManagementSystem.model.service.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.SecureRandom;

@Controller
@RequestMapping("/")
@Slf4j
@SessionAttributes("signUpDto")

public class SignInController {

    private static final Logger log = LoggerFactory.getLogger(SignInController.class);
    @Autowired
    private SignUpService signUpService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SignInController(){
        SignInController.log.info("Running signin controller");
    }

    @PostMapping("signin")
    public String onSignin(@RequestParam String email, @RequestParam String password, Model model) {
        log.info("Signin method is running in controller");
        log.info("Email: " + email);
        log.info("Password: " + password);

        SignUpDto signUpDto = this.signUpService.findByEmailAndPassword(email, password);
        if (signUpDto != null) {
            log.info("SignIn successful for email: " + email);
            model.addAttribute("message", signUpDto.getFname() + " : Welcome To Complaint Management System");

            // Set the signed-in user email in session
            log.info("Setting session attribute 'signedInUserEmail' with value: " + email);
            httpSession.setAttribute("signedInUserEmail", email);
            httpSession.setAttribute("email", email);

            // Set the user data in session
            httpSession.setAttribute("signUpDto", signUpDto);

            // Set the profile image in the session
            String profileImageUrl = "/images/" + signUpDto.getProfileImage();
            httpSession.setAttribute("profileImage", profileImageUrl);

            model.addAttribute("ProfilePageMessage", "Welcome To Issue Management System, " + signUpDto.getFname());
            return "Profile";
        } else {
            signUpService.incrementFailedAttempts(email);
            int failedAttempts = signUpService.getFailedAttempts(email);
            log.info("Failed attempts for " + email + ": " + failedAttempts);
            if (failedAttempts >= 3) {
                model.addAttribute("error", "Your account is locked due to too many failed attempts.");
                log.info(email + " : Your Account is locked due to too many failed attempts with wrong password ");
                model.addAttribute("disableButton", true);
            } else {
                model.addAttribute("error", "Invalid email id and password. Attempts: " + failedAttempts);
                log.info("Invalid email id and password");
                model.addAttribute("disableButton", false);
            }
            model.addAttribute("failedAttempts", failedAttempts);
            return "Profile";
        }
    }

    @PostMapping("forgotpassword")
    public String resetPassword(@RequestParam("email") String email, Model model) {
        log.info("Running resetPassword method in signin controller");

        SignUpDto signUpDto = signUpService.findByEmail(email);
        if (signUpDto != null) {
            String newPassword = generateRandomPassword();
            signUpDto.setPassword(passwordEncoder.encode(newPassword)); // Set the new password abd encode that newly generated password
            boolean isUpdated = signUpService.resetPasswordByEmail(signUpDto); // Update the user's password in the database
            if (isUpdated) {
                signUpService.sendPasswordEmail(email, "Password Reset", "Your new password is: " + newPassword);
                model.addAttribute("message", "A new password has been sent to your email.");
                log.info("your new encoded password is :"+passwordEncoder.encode(newPassword)+": and this is your decoded password : "+newPassword);
                return "SignIn"; // Redirect to the sign-in page
            } else {
                model.addAttribute("error", "Failed to reset password. Please try again.");
                return "ForgotPassword"; // Stay on the forgot-password page
            }
        } else {
            model.addAttribute("error", "Email not found.");
            return "ForgotPassword"; // Stay on the forgot-password page
        }
    }

    private String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_-+=<>?";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 12; i++) { // Generate a 12-character password
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }
}
