package com.xworkz.complaintManagementSystem.model.service;

import com.xworkz.complaintManagementSystem.dto.ProfileImageUploadDto;
import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import com.xworkz.complaintManagementSystem.model.repo.ImageUploadRepo;
import com.xworkz.complaintManagementSystem.model.repo.ImageUploadRepoImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ImageUploadServiceImpl implements ImageUploadService {


    static final Logger log = LoggerFactory.getLogger(ImageUploadServiceImpl.class);

    @Autowired
    private ImageUploadRepo imageUploadRepo;

    @Override
    public boolean saveImageDetails(ProfileImageUploadDto profileImageUploadDto) {
        log.info("saveImageDetails method running in ImageUploadServiceImpl..");

        boolean imageData=  imageUploadRepo.saveImage(profileImageUploadDto);

        if(imageData)
        {
            log.info("imageUploadRepo in ImageUploadServiceImpl");
        }

        else
        {
            log.info("imageUploadRepo not in ImageUploadServiceImpl");
        }
        return true;
    }

    @Override
    public Optional<ProfileImageUploadDto> getImageDetailsByUserId(int id) {
        log.info("Running getImageDetailsByUserId in ImageUploadServiceImpl");
        return imageUploadRepo.findByUserId(id);
    }

    @Override
    public void updateImageDetails(ProfileImageUploadDto profileImageUploadDto) {
        log.info("updateImageDetails method running in ImageUploadServiceImpl..");
        imageUploadRepo.imageUpdateDetails(profileImageUploadDto);



    }

//    @Override
//    public void setAllImagesInactiveForUser(int id) {
//        log.info("setAllImagesInactiveForUser method running in ImageUploadServiceImpl..");
//        imageUploadRepo.SetAllImagesInactiveForUser(id);
//
//    }


}
