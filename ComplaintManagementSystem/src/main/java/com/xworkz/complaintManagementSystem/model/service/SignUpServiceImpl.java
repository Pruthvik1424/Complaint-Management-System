package com.xworkz.complaintManagementSystem.model.service;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import com.xworkz.complaintManagementSystem.model.repo.SignUpRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class SignUpServiceImpl implements SignUpService {

    private static final Logger log = LoggerFactory.getLogger(SignUpServiceImpl.class);
    @Autowired
    private SignUpRepo signUpRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Map<String, SignUpDto> users = new HashMap<>();

    public SignUpServiceImpl() {
        System.out.println("Running SignupServiceImpl");
    }

    @Override
    public boolean save(SignUpDto signUpDto) {
        System.out.println("Running save method in SignUpServiceImpl");

        String createdBy = "pruthvi";
        LocalDateTime createdOn = LocalDateTime.now();
        String updatedBy = "NA";
        LocalDateTime updatedOn = null;
        boolean isActive = true;

        setAudit(signUpDto, createdBy, createdOn, updatedBy, updatedOn, isActive);
        signUpDto.setProfileImage("defdefaultuserimage.png");

        boolean saved = signUpRepo.save(signUpDto);
        if (saved) {
            System.out.println("Signup DTO saved successfully: " + signUpDto);
        } else {
            System.err.println("Signup DTO not saved: " + signUpDto);
        }
        users.put(signUpDto.getEmail(), signUpDto);
        return saved;
    }

    @Override
    public void setAudit(SignUpDto signUpDto, String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, boolean isActive) {
        System.out.println("Running setAudit method in SignUpServiceImpl");
        signUpDto.setCreatedBy(createdBy);
        signUpDto.setCreatedOn(createdOn);
        signUpDto.setUpdatedBy(updatedBy);
        signUpDto.setUpdatedOn(updatedOn);
        signUpDto.setActive(isActive);
    }

    @Override
    public SignUpDto findByEmailAndPassword(String email, String password) {
        SignUpDto signUpDto = signUpRepo.findByEmail(email);
        if (signUpDto != null && passwordEncoder.matches(password, signUpDto.getPassword())) {
            System.out.println(signUpDto);
            return signUpDto;
        }

        return null; // Or throw an exception if you prefer
    }



    @Override
    public void sendPasswordEmail(String toEmail, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);
        simpleMailMessage.setFrom("pruthvik1014@gmail.com");

        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void incrementFailedAttempts(String email) {
        SignUpDto user = signUpRepo.findByEmail(email);
        if (user != null) {
            int attempts = user.getFailedLoginAttempts() + 1;
            user.setFailedLoginAttempts(attempts);
            if (attempts >= 3) {
                user.setAccountLocked(true);
            }
            signUpRepo.update(user);
        }
    }

    @Override
    public int getFailedAttempts(String email) {
        SignUpDto user = signUpRepo.findByEmail(email);
        return (user != null) ? user.getFailedLoginAttempts() : 0;
    }

    @Override
    public void resetFailedAttempts(String email) {
        SignUpDto user = signUpRepo.findByEmail(email);
        if (user != null) {
            user.setFailedLoginAttempts(0);
            signUpRepo.update(user);
        }
    }

    @Override
    public void lockAccount(String email) {
        SignUpDto user = signUpRepo.findByEmail(email);
        if (user != null) {
            user.setAccountLocked(true);
            signUpRepo.update(user);
        }
    }

    @Override
    public boolean existByEmail(String email) {
        System.out.println("Running existByEmail method in SignUpServiceImpl");
        System.out.println("Email: " + email);
        SignUpDto mail = signUpRepo.existByEmail(email);
        return mail != null;
    }

    @Override
    public boolean existByMobilenumber(Long mobilenumber) {
        System.out.println("Running existByMobilenumber method in SignUpServiceImpl");
        System.out.println("Mobile number: " + mobilenumber);
        SignUpDto mobile = signUpRepo.existByMobilenumber(mobilenumber);
        return mobile != null;
    }

    @Override
    public SignUpDto findByEmail(String email) {
        System.out.println("running findbyEmail method in signupservice impl");

        SignUpDto signUpDto = signUpRepo.findByEmail(email);
        if(signUpDto!=null){
            System.out.println("found Signupdto in service"+signUpDto);
            return signUpDto;
        }else {
            System.out.println("not found result in service"+signUpDto);
        }
        return signUpRepo.findByEmail(email);
    }




    @Override
    public boolean resetPasswordByEmail(SignUpDto signUpDto) {
        System.out.println("Running resetPasswordByEmail method in SignUpServiceImpl");
        return signUpRepo.resetPasswordByEmail(signUpDto);
    }

}
