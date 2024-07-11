package com.xworkz.complaintManagementSystem.model.service;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;

public interface ViewProfileService {

    SignUpDto findByUserEmail(String email);

    String getSignInUserEmail();

    SignUpDto updateEditedUser(SignUpDto signUpDto);

}
