package com.xworkz.complaintManagementSystem.model.repo;

import com.xworkz.complaintManagementSystem.dto.AddDepartmentsDto;
import com.xworkz.complaintManagementSystem.dto.AdminDto;
import com.xworkz.complaintManagementSystem.dto.ComplaintDto;
import com.xworkz.complaintManagementSystem.dto.SignUpDto;

import java.util.List;

public interface AdminRepo {


   //admin can login using email and password
    AdminDto findByEmailAndPassword(String email, String password);

    //from here admin can view all users who signed up
    List<SignUpDto> findById(SignUpDto signUpDto);

    //admin can view all user raise Complaint details(ComplaingtDto)
    List<ComplaintDto> findByComplaintId(ComplaintDto complaintDto);

    //search by complaint type
    List<ComplaintDto> searchByComplaintTypeAndCity(String complaintType, String city);

    //search by complaintType and city
    List<ComplaintDto> searchByComplaintTypeOrCity(String complaintType, String city);

   //Add departments from here
   boolean saveDepartments(AddDepartmentsDto addDepartmentsDto);

    //getAllDepartments
    List<AddDepartmentsDto> getAllDepartments();

    //asigning departments and adding status
   // void allocateDepartment(int complaintId, int departmentsId,String status);


    //updating status and allocation in complaint table
    void allocateDepartment(int complaintId, int departmentsId,String status);





}
