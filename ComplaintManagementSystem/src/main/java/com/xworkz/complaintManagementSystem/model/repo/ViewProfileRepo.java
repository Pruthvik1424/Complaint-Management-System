package com.xworkz.complaintManagementSystem.model.repo;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;

public interface ViewProfileRepo {

    default SignUpDto findByUserEmail(String email) {
        return null;
    }

    SignUpDto updateEditedUser(SignUpDto signUpDto);
}
