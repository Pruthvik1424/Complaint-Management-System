package com.xworkz.complaintManagementSystem.model.service;

import com.xworkz.complaintManagementSystem.dto.ProfileImageUploadDto;
import com.xworkz.complaintManagementSystem.dto.SignUpDto;

import java.util.Optional;

public interface ViewProfileService {

    SignUpDto findByUserEmail(String email);

    String getSignInUserEmail();

    SignUpDto updateEditedUser(SignUpDto signUpDto);

    Optional<ProfileImageUploadDto> getImageDetailsByUserId(int id);


}
