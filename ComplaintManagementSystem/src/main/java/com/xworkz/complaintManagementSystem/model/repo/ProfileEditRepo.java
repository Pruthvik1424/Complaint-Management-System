package com.xworkz.complaintManagementSystem.model.repo;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;

public interface ProfileEditRepo {


    SignUpDto findByEmail(String email);

    void updateUserDetails(SignUpDto signUpDto);

}
