package com.xworkz.complaintManagementSystem.controller;


import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xworkz.complaintManagementSystem.dto.AddDepartmentsDto;
import com.xworkz.complaintManagementSystem.dto.AdminDto;
import com.xworkz.complaintManagementSystem.dto.ComplaintDto;
import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import com.xworkz.complaintManagementSystem.model.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    public AdminController(){
        System.out.println("Running admin controller.....");
    }



    @GetMapping("/admin")
    public String  onAdminLogin(AdminDto adminDto, @RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redirectAttributes, Model model) {
        System.out.println("adminDetails method in AdminController");

        // Check if email and password are present
        if (email == null || password == null) {
            System.out.println("Email or password is missing");
            redirectAttributes.addFlashAttribute("errorAdminMessage", "Email and password are required.");
            return "redirect:/AdminPage";
        }



        boolean data = adminService.findByEmailAndPassword(email, password);

        if (data) {
            System.out.println("findByEmailAndPassword successful in AdminController..");
            redirectAttributes.addFlashAttribute("adminMessage", "Login successful");

            model.addAttribute("AdminProfilePageMessage", "Welcome to Admin profile");
            return "AdminProfile";
            //return "redirect:/adminPage";
        } else {
            System.out.println("findByEmailAndPassword not successful in AdminController");
            redirectAttributes.addFlashAttribute("errorAdminMessage", "Failed to login. Please check your email and password.");
            return "AdminPage";
        }
    }

    @GetMapping("adminPage")
    public String showAdminPage() {
        return "AdminPage";
    }

    //view user details(SignUp details)
    @RequestMapping("view-user-details")
    public String viewUserDetails(SignUpDto signUpDto, Model model) {
        System.out.println("viewUserDetails method in AdminController..");
        List<SignUpDto> signUpDtoData = adminService.findById(signUpDto);

        if (signUpDtoData != null) {
            System.out.println("view-user-details successful in AdminController..");
            model.addAttribute("ViewUserDetails", signUpDtoData);
            return "AdminViewUserDetails";

        } else {
            System.out.println("view-user-details not  successful in AdminController..");
        }
        return "AdminViewUserDetails";
    }

    //view Raise complaint details
    @GetMapping("View-raise-complaint")
    public String viewRaiseComplaintDetails(ComplaintDto complaintDto,AddDepartmentsDto addDepartmentsDto, Model model) {
        System.out.println("viewUserDetails method running in AdminController");

        List<ComplaintDto> viewcomplaintData = adminService.findByComplaintId(complaintDto);
        List<AddDepartmentsDto> departments = adminService.getAllDepartments(); // Retrieve department names


        if (!viewcomplaintData.isEmpty()) {
            System.out.println("View raise complaint data successful in AdminController");
            model.addAttribute("complaintData", viewcomplaintData);

            model.addAttribute("departments",departments);
            System.out.println("viewcomplaintData :"+viewcomplaintData);
            System.out.println("viewcomplaintData size: " + viewcomplaintData.size());
            System.out.println("departments size: " + departments.size());

            return "AdminViewComplaintDetails";
        } else {
            System.out.println("View raise complaint data not successful in AdminController.");
        }
        return "AdminViewComplaintDetails";
    }

    // Combined search endpoint for both OR and AND conditions
    @PostMapping("/ComplaintTypeSearch")
    public String searchByComplaintType(ComplaintDto complaintDto, Model model) {

        System.out.println("searchByComplaintType method running in AdminController..!!");



        List<ComplaintDto> listOfTypeAndCity = adminService.searchByComplaintTypeAndCity(complaintDto.getComplaintType(), complaintDto.getCity());
        List<AddDepartmentsDto> departments = adminService.getAllDepartments(); // Retrieve department names

        //  System.out.println("TypeAndCity : " + listOfTypeAndCity);

        if (!listOfTypeAndCity.isEmpty()) {
            // System.out.println("searchByComplaintTypeAndCity successful in AdminController");
            model.addAttribute("complaintData", listOfTypeAndCity);
           // model.addAttribute("viewRaiseComplaint", listOfTypeAndCity);
            model.addAttribute("departments",departments);
            return "AdminViewComplaintDetails";
        } else {
            List<ComplaintDto> listOfTypeOrCity = adminService.searchByComplaintTypeOrCity(complaintDto.getComplaintType(), complaintDto.getCity());

            //System.out.println("TypeOrCity : " + listOfTypeOrCity);
            if (!listOfTypeOrCity.isEmpty()) {
                //   System.out.println("searchByComplaintTypeOrCity ");
                model.addAttribute("complaintData", listOfTypeOrCity);
                model.addAttribute("departments",departments);

                return "AdminViewComplaintDetails";

            }
        }

        return "AdminViewComplaintDetails";
    }



    //save add departments from here
    @PostMapping("add-departments")
    public String saveAddDepartments(AddDepartmentsDto addDepartmentsDto,Model model){
        System.out.println("running saveAddDepartments method in admincontroller...");
       boolean savedDepartments = adminService.saveDepartments(addDepartmentsDto);
       if(savedDepartments){
           System.out.println("departments added successfully....");
           model.addAttribute("departrmentssucccessmsg","Departments added successfully..");
       }else {
           System.out.println("departments not added...do it properly");
           model.addAttribute("departmentserrormsg",addDepartmentsDto);
       }
        return "AddDepartments";
    }

    @GetMapping("department")
    public String save(AddDepartmentsDto departmentDto, Model model) {
        model.addAttribute("savedfailed", "save Department successfully");
        return "AddDepartment";
    }

    @GetMapping("department-profile")
    public String saved(AddDepartmentsDto departmentDto, Model model) {
        model.addAttribute("savedsuccess", "save Department successfully");
        return "AdminProfile";
    }

    @PostMapping("/allocate-department")
    public String allocateDepartment(
            @RequestParam("complaintId") int complaintId,
            @RequestParam("departmentsId") int departmentsId,
            @RequestParam("status") String status,
            Model model
    ) {
        try {
            System.out.println("Running allocate department");

            // Call the service method to allocate department
            adminService.allocateDepartment(complaintId, departmentsId,status);
            System.out.println("complaintId"+complaintId);
            System.out.println("departmentsId"+departmentsId);
            model.addAttribute("successMessage", "Department allocated successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Failed to allocate department. Please try again.");
            e.printStackTrace();
        }
        return "AdminViewComplaintDetails";

    }

}



