package com.xworkz.complaintManagementSystem.model.service;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import com.xworkz.complaintManagementSystem.model.repo.SignUpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private SignUpRepo signUpRepo;

    @Autowired
    private JavaMailSender javaMailSender;

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
        return signUpRepo.findByEmailAndPassword(email, password);
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


//        SignUpDto user = signUpRepo.findByEmail(email);
//        if (user == null) {
//            System.out.println("Email not found in database");
//            return false;
//        }
//
//        String newPassword = generatePassword();
//        user.setPassword(newPassword);
//        boolean isUpdated = signUpRepo.resetPasswordByEmail(user);
//
//        if (isUpdated) {
//            String subject = "New Password";
//            String body = "Your new password is: " + newPassword;
//            sendPasswordEmail(email, subject, body);
//            System.out.println("Password reset successful");
//        } else {
//            System.err.println("Failed to update password in database");
//        }

//        return isUpdated;
    }


    private String generatePassword() {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            sb.append((char) ('A' + secureRandom.nextInt(26)));
        }
        return sb.toString();
    }

}
