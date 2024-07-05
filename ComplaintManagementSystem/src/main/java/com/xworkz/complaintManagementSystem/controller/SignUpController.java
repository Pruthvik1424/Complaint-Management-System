package com.xworkz.complaintManagementSystem.controller;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import com.xworkz.complaintManagementSystem.model.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.SecureRandom;

@Controller
@RequestMapping("/")
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    public SignUpController(){
        System.out.println("Running signup controller");
    }

    @PostMapping("/signup")
    public String onSignUp(@Valid SignUpDto signUpDto, BindingResult bindingResult, Model model, @RequestParam("email")String email){
        System.out.println("Running onSignUp method in SignUp controller");

        if(bindingResult.hasErrors()){
            System.err.println("Signup dto has invalid data" + signUpDto);
            bindingResult.getAllErrors().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "SignUp";
        }

        // Generate a random password
        String generatedPassword = generateRandomPassword();
        signUpDto.setPassword(generatedPassword);


        boolean saved = this.signUpService.save(signUpDto);
        if(saved){
            System.out.println("Signup service is saved successfully" + signUpDto);
            System.out.println("Generated Password: " + generatedPassword);
            // Send email with the generated password
            String subject ="Welcome to our complaint management system";

            String body ="Hi"+signUpDto.getFname()+",\n\n your regeisteraation is successful. your password is "+ signUpDto.getPassword();

            signUpService.sendPasswordEmail(email,subject,body);

            model.addAttribute("msg","Signup successfull. please chech your email for your password");
            return "SignIn";

        }else {
            System.err.println("Signup service is not saved" + signUpDto);
        }
        model.addAttribute("name", signUpDto.getFname() + "Signup successfully");
        model.addAttribute("password", generatedPassword);

        return "SignIn";
    }

    private String generateRandomPassword() {
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }

//    @PostMapping("/forgotpassword")
//    public ResponseEntity<String> resetPassword(@RequestParam String email) {
//        boolean isReset = signUpService.resetPasswordByEmail(email);
//        if (isReset) {
//            return ResponseEntity.ok("Password reset email sent");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password reset failed");
//        }
//    }

}
