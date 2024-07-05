package com.xworkz.complaintManagementSystem.model.repo;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;

public interface SignUpRepo {
    boolean save(SignUpDto signUpDto);

    SignUpDto findByEmailAndPassword(String email, String password);

    SignUpDto findByEmail(String email); // Add this method to find a user by email

    boolean update(SignUpDto signUpDto);

    SignUpDto existByEmail(String email);

    SignUpDto existByMobilenumber(Long mobilenumber);

    boolean resetPasswordByEmail(SignUpDto signUpDto);

}
