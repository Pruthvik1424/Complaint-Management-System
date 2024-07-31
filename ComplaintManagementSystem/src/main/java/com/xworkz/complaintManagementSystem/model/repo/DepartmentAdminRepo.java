package com.xworkz.complaintManagementSystem.model.repo;

import com.xworkz.complaintManagementSystem.dto.DepartmentAdminDto;

public interface DepartmentAdminRepo {

    //for department admin signup
    boolean addDepartmentAdmin(DepartmentAdminDto departmentAdminDto);

    //for department admin signin
    DepartmentAdminDto findByEmailAndPassword(String email, String password);

    DepartmentAdminDto findByEmail(String email);
}
