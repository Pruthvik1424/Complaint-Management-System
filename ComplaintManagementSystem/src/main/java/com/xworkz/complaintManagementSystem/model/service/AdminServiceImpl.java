package com.xworkz.complaintManagementSystem.model.service;


import com.xworkz.complaintManagementSystem.dto.AddDepartmentsDto;
import com.xworkz.complaintManagementSystem.dto.AdminDto;
import com.xworkz.complaintManagementSystem.dto.ComplaintDto;
import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import com.xworkz.complaintManagementSystem.model.repo.AdminRepo;
import com.xworkz.complaintManagementSystem.model.repo.AdminRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepo adminRepo;

    public AdminServiceImpl(){
        System.out.println("Running adminserviceimpl .....");
    }


    @Override
    public boolean findByEmailAndPassword(String email, String password) {
        System.out.println("findByEmailAndPassword method in Service Implementation");

        AdminDto data = adminRepo.findByEmailAndPassword(email, password);

        if (data != null) {
            System.out.println("findByEmailAndPassword successful in AdminServiceImpl");
            return true;
        } else {
            System.out.println("findByEmailAndPassword not successful in AdminServiceImpl");
        }
        return false;
    }

    @Override
    public List<SignUpDto> findById(SignUpDto signUpDto) {
        System.out.println("findById method in AdminServiceImpl..");
        List<SignUpDto> dtoData = adminRepo.findById(signUpDto);
        if (dtoData != null) {
            System.out.println("findById data successful in AdminServiceImpl..");
            return dtoData;
        } else {
            System.out.println("findById data not successful in AdminServiceImpl..");
        }
        return Collections.emptyList();
    }

    @Override
    public List<ComplaintDto> findByComplaintId(ComplaintDto complaintDto) {
        System.out.println("findById  method for getting complaintDtoList in AdminServiceImpl..");
        List<ComplaintDto> complaintDtoList = adminRepo.findByComplaintId(complaintDto);
        if (complaintDtoList != null) {
            System.out.println("findById complaintDtoList successful in AdminServiceImpl..");
            return complaintDtoList;
        } else {
            System.out.println("findById complaintDtoList not successful in AdminServiceImpl..");
        }
        return Collections.emptyList();
    }

    @Override
    public List<ComplaintDto> searchByComplaintTypeAndCity(String complaintType, String city) {
        System.out.println("searchByComplaintTypeORCity method running in AdminServiceImpl..");
        List<ComplaintDto> data = adminRepo.searchByComplaintTypeAndCity(complaintType, city);

        if (!data.isEmpty()) {
            System.out.println("searchByComplaintTypeORCity successful in AdminServiceImpl..");
            return data;
        } else {
            System.out.println("searchByComplaintTypeORCity not successful in AdminServiceImpl..");
        }
        return Collections.emptyList();
    }

    @Override
    public List<ComplaintDto> searchByComplaintTypeOrCity(String complaintType, String city) {
        System.out.println("searchByComplaintTypeAndCity method running in AdminServiceImpl..");

        List<ComplaintDto> listOfData = adminRepo.searchByComplaintTypeOrCity(complaintType, city);
        if (!listOfData.isEmpty()) {
            System.out.println("searchComplaintTypeAndCity successful in AdminServiceImpl");
            return listOfData;
        } else {
            System.out.println("searchByComplaintTypeAndCity not successful in AdminServiceImpl..");
        }

        return Collections.emptyList();
    }

    @Override
    public boolean saveDepartments(AddDepartmentsDto addDepartmentsDto) {
        System.out.println("saveDepartment method running in AdminServiceImpl..");

        boolean savedDepartments = adminRepo.saveDepartments(addDepartmentsDto);

        System.out.println("savedDepartments :" + savedDepartments);

        if (savedDepartments) {
            System.out.println("saveDepartment  successful in AdminServiceImpl..");
        }

        else
        {
            System.out.println("saveDepartment not successful in AdminServiceImpl..");
            return false;
        }
        return true;
    }

    @Override
    public List<AddDepartmentsDto> getAllDepartments() {
        return adminRepo.getAllDepartments(); // Retrieve all departments
    }

//    @Override
//    public void allocateDepartment(int complaintId, int departmentsId, String status) {
//        System.out.println("Running  allocateDepartment method in adminserviceimpl...");
//        // Delegate the department allocation to the repository
//        adminRepo.allocateDepartment(complaintId, departmentsId,status);
//    }


    @Override
    public void allocateDepartment(int complaintId, int departmentsId,String status) {
        System.out.println("Running  allocateDepartment method in adminserviceimpl...");
        // Delegate the department allocation to the repository
        adminRepo.allocateDepartment(complaintId, departmentsId,status);
    }
}
