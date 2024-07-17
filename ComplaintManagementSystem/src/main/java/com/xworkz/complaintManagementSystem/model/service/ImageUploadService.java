package com.xworkz.complaintManagementSystem.model.service;

import com.xworkz.complaintManagementSystem.dto.ProfileImageUploadDto;
import com.xworkz.complaintManagementSystem.dto.SignUpDto;

import java.util.Optional;

public interface ImageUploadService {


    boolean saveImageDetails(ProfileImageUploadDto profileImageUploadDto);

    Optional<ProfileImageUploadDto> getImageDetailsByUserId(int id);


    /// New method for updating image details
    void updateImageDetails(ProfileImageUploadDto profileImageUploadDto); // New method


    //  void setAudit(EditProfileImageDTO editProfileImageDTO , String updatedBy, LocalDateTime updatedOn);
   // void setAllImagesInactiveForUser(int id);  // New method declaration




}
