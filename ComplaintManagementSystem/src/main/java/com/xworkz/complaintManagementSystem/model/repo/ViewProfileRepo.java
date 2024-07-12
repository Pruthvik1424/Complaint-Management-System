package com.xworkz.complaintManagementSystem.model.repo;

import com.xworkz.complaintManagementSystem.dto.ProfileImageUploadDto;
import com.xworkz.complaintManagementSystem.dto.SignUpDto;

import java.util.Optional;

public interface ViewProfileRepo {

    default SignUpDto findByUserEmail(String email) {
        return null;
    }

    SignUpDto updateEditedUser(SignUpDto signUpDto);

    void saveProfileImage(ProfileImageUploadDto profileImageUploadDto);

    Optional<ProfileImageUploadDto> findByUserId(int id);


}
