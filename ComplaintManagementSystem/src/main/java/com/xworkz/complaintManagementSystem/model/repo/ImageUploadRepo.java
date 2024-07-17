package com.xworkz.complaintManagementSystem.model.repo;

import com.xworkz.complaintManagementSystem.dto.ProfileImageUploadDto;
import com.xworkz.complaintManagementSystem.dto.SignUpDto;

import java.util.Optional;

public interface ImageUploadRepo {



    boolean saveImage(ProfileImageUploadDto profileImageUploadDto);



    Optional<ProfileImageUploadDto> findByUserId(int id);  //id from signUp id

    //to update image table

    void imageUpdateDetails(ProfileImageUploadDto profileImageUploadDto);

    //void SetAllImagesInactiveForUser(int id);

    //ProfileImageUploadDto findByUserId(String email);

}
