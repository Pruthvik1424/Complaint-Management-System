package com.xworkz.complaintManagementSystem.controller;


import com.xworkz.complaintManagementSystem.dto.ComplaintDto;
import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import com.xworkz.complaintManagementSystem.model.service.ComplaintRaiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
@SessionAttributes("signUpDto")
public class ComplaintRaiseController {
    @Autowired
    private ComplaintRaiseService complaintRaiseService;

    public ComplaintRaiseController()
    {
        System.out.println("Created ComplaintRaiseController");
    }

    @PostMapping("/raise-complaint")
    public String raiseComplaint(@ModelAttribute("signUpDto") SignUpDto signUpDto, @ModelAttribute("ComplaintDto") ComplaintDto complaintDto, Model model)
    {
        System.out.println("Running raiseComplaint method in RaiseComplaintController...");
        // Accessing id from SignupDto
        int signedInUserId = signUpDto.getId();
        System.out.println("Signed in user ID: " + signedInUserId);

        // Set the signed in user ID in raiseComplaintDto
        SignUpDto userDto = new SignUpDto();
        userDto.setId(signedInUserId);
        complaintDto.setUserid(userDto);

        boolean save=complaintRaiseService.saveRaiseComplaintType(complaintDto);

        if(save)
        {
            System.out.println("Controller:save raiseComplaint details successfully"+complaintDto);
            model.addAttribute("raiseComplaintSucess","saved raiseComplaint details successfully");
            return "ComplaintRaise";
        }

        else {
            model.addAttribute("ErrorRaiseComplaintSucess"," Not saved raiseComplaint details successfully");
            System.out.println("Controller:not save raiseComplaint details successfully"+complaintRaiseService);
        }
        return "ComplaintRaise";
    }

    //view RaiseComplaint
    @GetMapping("view-complaint")
    public String viewRaiseComplaint(Model model, @ModelAttribute("signUpDto") SignUpDto signUpDTO) {
        int userId = signUpDTO.getId();
        List<ComplaintDto> complaints = complaintRaiseService.getComplaintsByUserId(userId);
        model.addAttribute("viewRaiseComplaint", complaints);
        return "ComplaintView";
    }

    //edit Raise_Complaint

    @GetMapping("/edit-complaint/{complaintId}")
    public String showEditComplaintForm(@PathVariable("complaintId") int complaintId, Model model) {
        ComplaintDto complaintDto = complaintRaiseService.getComplaintById(complaintId);
        model.addAttribute("complaintDto", complaintDto);//values should be retain in page
        return "ComplaintEdit";
    }

    @PostMapping("update-complaint-detailes")
    public String updateComplaint(@ModelAttribute("ComplaintDto") ComplaintDto complaintDto, Model model) {
        System.out.println("ComplaintDto :"+complaintDto);
        List<ComplaintDto> isUpdated = complaintRaiseService.updateRaiseComplaintUserDetails(complaintDto);
        if (isUpdated!=null) {
            model.addAttribute("updateMsg", "Complaint updated successfully!");
            System.out.println("Updated successfullyyy(controller)....");
            model.addAttribute("viewRaiseComplaint", isUpdated); //for table data view raise complaint
            return "ComplaintEdit";
        }
        else {
            model.addAttribute("updateErrorMsg", "Failed to update complaint. Please try again.");
        }
        return "ComplaintEdit";
    }
}
