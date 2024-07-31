package com.xworkz.complaintManagementSystem.controller;

import com.xworkz.complaintManagementSystem.dto.AddDepartmentsDto;
import com.xworkz.complaintManagementSystem.dto.DepartmentAdminDto;
import com.xworkz.complaintManagementSystem.model.service.AdminService;
import com.xworkz.complaintManagementSystem.model.service.DepartmentAdminService;
import com.xworkz.complaintManagementSystem.model.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.SecureRandom;
import java.util.List;


@Controller
@RequestMapping("/")

public class DepartmentAdminController {

    @Autowired
    private DepartmentAdminService departmentAdminService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DepartmentAdminController(){
        System.out.println("runnning DepartmentAdminController.....");
    }

    //here Admin adding  department admin
    @PostMapping("/adddepartmentadmin")
    public String onAddDepartmentAdmin(DepartmentAdminDto departmentAdminDto, Model model,@RequestParam("email") String email){
        System.out.println("running onAddDepartmentAdmin in DepartmentAdminController.....");

        List<AddDepartmentsDto> departments = adminService.getAllDepartments();// Retrieve department names
        model.addAttribute("departments",departments);

        // Generate a random password and ecode password and save in database
        String generatedPassword = generateRandomPassword();
      departmentAdminDto.setPassword(passwordEncoder.encode(generatedPassword));


        boolean addDepartmentAdmin = departmentAdminService.addDepartmentAdmin(departmentAdminDto);
        if(addDepartmentAdmin){
           System.out.println("Department admin added successfully...");
            System.out.println(addDepartmentAdmin);
            System.out.println(departmentAdminDto);
            System.out.println("password for department admin :"+generatedPassword);
            System.out.println("encoded password for department admin :"+passwordEncoder.encode(generatedPassword));
           model.addAttribute("departmentadminaddedsuccess","Department admin added successfully...");
            model.addAttribute("password", generatedPassword);

            // Send email with the generated password
            String subject = "Welcome to our complaint management system";
            String body = "Hi" + departmentAdminDto.getName() + ",\n\n your are added as a Department admin successfully."+",\n\n your password is :" + generatedPassword +"\n\n please signin by providing your email and password";
            signUpService.sendPasswordEmail(email, subject, body);

            model.addAttribute("msg", "Signup successfull. please chech your email for your password");



        }else {
            System.out.println("Department admin is not added...");
            model.addAttribute("departmentadminaddedfailed","Department admin is failed to add");
            return "AddDepartmentAdmin";
        }

        return "AddDepartmentAdmin";
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


    //here department admin signin
    @PostMapping("/departmentadminsignin")
    public String onDepartmentAdminSignIn(@RequestParam("email") String email, @RequestParam("password") String password,Model model){
        System.out.println("Runnning onDepartmentAdminSignIn in DepartmentAdminController...");

     DepartmentAdminDto departmentAdminDto = departmentAdminService.findByEmailAndPassword(email, password);
     if(departmentAdminDto!=null){
         System.out.println("department admin sign in successfully");
         model.addAttribute("signinsuccess","Department admin Sign in successfully.");
     }else {
         System.out.println("department admin is not signed in");
         model.addAttribute("signinfailed","department admin is not signed in");
         return "DepartmentAdminSignIn";
     }
        return "DepartmentAdminProfile";
    }
}
