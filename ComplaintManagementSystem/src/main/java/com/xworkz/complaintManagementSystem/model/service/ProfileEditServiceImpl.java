package com.xworkz.complaintManagementSystem.model.service;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import com.xworkz.complaintManagementSystem.model.repo.ProfileEditRepo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
@Slf4j
public class ProfileEditServiceImpl implements ProfileEditService{

    private static final Logger log = LoggerFactory.getLogger(ProfileEditServiceImpl.class);
    @Autowired
    private ProfileEditRepo profileEditRepo;

    @Autowired
    private HttpSession httpSession;

    public ProfileEditServiceImpl(){
        log.info("running profileEditServiceImpl.... ");
    }



    @Override
    public SignUpDto updateUserDetails(SignUpDto signUpDto) {
        log.info("updateUserDetails method running in EditUserProfileServiceImpl..");


        // Set audit fields
        String updatedBy = signUpDto.getFname();
        LocalDateTime updatedOn = LocalDateTime.now();
        setAudit(signUpDto, updatedBy, updatedOn);
        profileEditRepo.updateUserDetails(signUpDto);
        signUpDto.setProfileImage("defdefaultuserimage.png");

        //set ImageUpload ad

        return signUpDto;
    }


    @Override
    public void setAudit(SignUpDto signUpDto, String updatedBy, LocalDateTime updatedOn) {
        log.info("setAudit method running in EditUserProfileServiceImpl.. ");
        signUpDto.setUpdatedBy(updatedBy);
        signUpDto.setUpdatedOn(updatedOn);

    }
}
