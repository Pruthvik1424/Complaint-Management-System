package com.xworkz.complaintManagementSystem.controller;

import com.xworkz.complaintManagementSystem.model.service.ChangePasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ChangePasswordController {

    @Autowired
    private ChangePasswordService changePasswordService;

    public ChangePasswordController() {
        System.out.println("Running ChangePasswordController");
    }

    @PostMapping("/changepassword")
    public String changePassword(@RequestParam("email") String email,
                                 @RequestParam("oldpassword") String oldPassword,
                                 @RequestParam("newpassword") String newPassword,
                                 @RequestParam("confirmpassword") String confirmPassword) {
        System.out.println("Executing changePassword method in ChangePasswordController");

        if (newPassword.equals(confirmPassword)) {
            boolean passwordChanged = changePasswordService.changePassword(email, oldPassword, newPassword,confirmPassword);
            if (passwordChanged) {
                changePasswordService.sendChangePasswordToEmail(email);
                return "SignIn"; // Redirect to login page after password change
            } else {
                return "ChangePassword";


            }
        } else {
            return "ChangePassword";
        }
    }
}
