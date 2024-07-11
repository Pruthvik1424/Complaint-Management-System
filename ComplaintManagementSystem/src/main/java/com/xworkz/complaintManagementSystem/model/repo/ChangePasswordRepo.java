package com.xworkz.complaintManagementSystem.model.repo;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;

public interface ChangePasswordRepo {

    SignUpDto findByEmailAndPassword(String email, String password);
    boolean updatePassword(String email, String newPassword);
}
