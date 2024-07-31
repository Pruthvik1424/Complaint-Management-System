package com.xworkz.complaintManagementSystem.model.service;

import com.xworkz.complaintManagementSystem.dto.DepartmentAdminDto;
import com.xworkz.complaintManagementSystem.model.repo.DepartmentAdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DepartmentAdminServiceImpl implements DepartmentAdminService{

    @Autowired
    private DepartmentAdminRepo departmentAdminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public DepartmentAdminServiceImpl(){
        System.out.println("Running DepartmentAdminServiceImpl... ");
    }

    // here admin add department admin
    @Override
    public boolean addDepartmentAdmin(DepartmentAdminDto departmentAdminDto) {
        System.out.println("running addDepartmentAdmin in DepartmentAdminServiceImpl");

      boolean savedDepartmentAdmin =  departmentAdminRepo.addDepartmentAdmin(departmentAdminDto);

      if(savedDepartmentAdmin){
          System.out.println("Department admin added successfully , data saved in database successfully ...");
      }else {
          System.out.println("Department admin not added  , data  not saved in database.");
      }
        return true;
    }

// here department admin signin
    @Override
    public DepartmentAdminDto findByEmailAndPassword(String email, String password ) {

       DepartmentAdminDto departmentAdminDto = departmentAdminRepo.findByEmail(email);
       if(departmentAdminDto!=null && passwordEncoder.matches(password, departmentAdminDto.getPassword()) )
       {
           System.out.println("dto found :"+departmentAdminDto);
           return departmentAdminDto;
       }

        System.out.println("dto not found : "+departmentAdminDto);
        return null;
    }

}
