package com.xworkz.complaintManagementSystem.model.service;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;

import java.time.LocalDateTime;

public interface ProfileEditService {


    SignUpDto updateUserDetails(SignUpDto signUpDto);


    public void setAudit(SignUpDto signUpDto,  String updatedBy, LocalDateTime updatedOn);

}
