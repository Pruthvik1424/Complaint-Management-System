package com.xworkz.complaintManagementSystem.controller;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import com.xworkz.complaintManagementSystem.model.service.ViewProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
@SessionAttributes("signUpDto")
public class EditProfileController {

    @Autowired
    private ViewProfileService viewProfileService;

    public EditProfileController() {
        System.out.println("Running EditProfileController...");
    }

    @GetMapping("/edit-profile")
    public String editProfileForm(Model model) {
        String email = viewProfileService.getSignInUserEmail();
        if (email == null || email.isEmpty()) {
            System.out.println("No signed-in user email found in session");
            model.addAttribute("msg1", "No signed-in user found");
            return "ProfileEdit";
        }

        System.out.println("Received request to edit profile for email: " + email);
        SignUpDto signUpDto = viewProfileService.findByUserEmail(email);
        if (signUpDto != null) {
            System.out.println("User found: " + signUpDto);
            model.addAttribute("signupdto", signUpDto);
            return "ProfileEdit";
        } else {
            System.out.println("User not found for email: " + email);
            model.addAttribute("msg1", "User not found");
            return "ProfileEdit";
        }
    }


    @PostMapping("/edit-profile")
    public String updateProfile(@Valid @ModelAttribute("signupdto") SignUpDto signUpDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "Profile update failed due to validation errors");
            return "ProfileEdit";
        }

        SignUpDto updated = viewProfileService.updateEditedUser(signUpDto);
        if (updated != null) {
            model.addAttribute("signupdto", updated);
            model.addAttribute("profileMessage", "Profile updated successfully");
        } else {
            model.addAttribute("error", "Profile update failed");
        }

        return "ProfileEdit";
    }
}
