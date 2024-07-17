package com.xworkz.complaintManagementSystem.controller;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import com.xworkz.complaintManagementSystem.model.service.SignUpService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
@Slf4j
@SessionAttributes("signUpDto")

public class UserController {

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private HttpSession httpSession;


    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(){
        log.info("Created USerController.....");
    }

   // <!-- from here profile action is start --!>


    // this action is for view profile
   @GetMapping("/viewprofile")
   public String viewProfile( Model model){
       // Assuming you have a method to get the currently logged-in user's email
       String userEmail =  (String) httpSession.getAttribute("signedInUserEmail");
       System.out.println("Signed-in user email :"+userEmail);
       log.info("Signed-in user email :"+userEmail);

       if(userEmail!=null)
       {
           // Fetch user data based on the email
           SignUpDto signUpDto = signUpService.findByEmail(userEmail);
           System.out.println(signUpDto);
           log.info("signin dto : "+signUpDto);
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
