package com.xworkz.complaintManagementSystem.controller;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import com.xworkz.complaintManagementSystem.model.service.ViewProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@SessionAttributes("signUpDto")
public class ViewProfileController {

    @Autowired
    private ViewProfileService viewProfileService;

    @Autowired
    private HttpSession httpSession;

    public ViewProfileController(){
        System.out.println("Running ViewProfileController...");
    }

    @GetMapping("/viewprofile")
    public String viewProfile( Model model){
        // Assuming you have a method to get the currently logged-in user's email
     String userEmail =  (String) httpSession.getAttribute("signedInUserEmail");

        System.out.println("Signed-in user email :"+userEmail);


        if(userEmail!=null)
        {
            // Fetch user data based on the email
            SignUpDto signUpDto = viewProfileService.findByUserEmail(userEmail);


            // Add the user data to the model

            model.addAttribute("signUpDto",signUpDto);
        }

        else
        {
            System.out.println("user email found in session");
        }



        // Return the view name
        return "ViewProfile";
    }
}
