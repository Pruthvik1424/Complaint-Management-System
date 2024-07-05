package com.xworkz.complaintManagementSystem.controller;

import com.xworkz.complaintManagementSystem.model.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AjaxController {

    @Autowired
    private SignUpService
            signUpService;
    public AjaxController(){
        System.out.println("running rest controller of Ajaxcontroller");
    }


//    @GetMapping("/validateEmail/{email}")
//    public String emailValidation(@PathVariable String email){
//        System.out.println("email :"+email);
//        if(signUpService.existByEmail(email)){
//            return "<span style='color : red '>Email Already Exist</span>";
//        }else {
//            return null;
//        }
//    }

    @GetMapping("/validateEmail/{email}")
    public String emailValidation(@PathVariable String email) {
        System.out.println("Email: " + email);
        if (signUpService.existByEmail(email)) {
            return "<span style='color : red '>Email Already Exist</span>";
        } else {
            return "<span style='color : green '>Email Available</span>";
        }
    }

    @GetMapping("/validateMobilenumber/{mobilenumber}")
    public String mobilenumberValidation(@PathVariable String mobilenumber) {
        System.out.println("MobileNumber :" + mobilenumber);
        Long mobileNumber = Long.parseLong(mobilenumber);
        if (signUpService.existByMobilenumber(Long.valueOf(mobileNumber))) {
            return "<span style ='color : red '>This mobilenumber is already exist</span>";
        } else {
            return "<span style= 'color : green'>This contact is available </span>";
        }
    }



}
