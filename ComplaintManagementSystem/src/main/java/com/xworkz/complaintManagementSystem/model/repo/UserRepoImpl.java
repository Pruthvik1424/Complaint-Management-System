package com.xworkz.complaintManagementSystem.model.repo;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
@Slf4j
public class UserRepoImpl implements UserRepo {

    private static final Logger log = LoggerFactory.getLogger(ImageUploadRepoImpl.class);

    @Autowired
    private  EntityManagerFactory entityManagerFactory;

    public UserRepoImpl(){
        log.info("Created UserRepoImpl..");
    }



}
