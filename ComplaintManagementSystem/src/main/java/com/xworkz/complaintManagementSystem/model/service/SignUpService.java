package com.xworkz.complaintManagementSystem.model.service;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;

import java.time.LocalDateTime;

public interface SignUpService {
    //Signup dto passing to service
    boolean save(SignUpDto signUpDto);
    //along with signup audit data also sending
   public void setAudit(SignUpDto signUpDto, String createdBy, LocalDateTime createdOn, String updatedBy , LocalDateTime updatedOn, boolean isActive);

   SignUpDto findByEmailAndPassword(String email, String password);

   public void sendPasswordEmail(String toEmail,String subject,String body);

    //checking wrong password and lock the account

    void incrementFailedAttempts(String email);

    int getFailedAttempts(String email);

    void resetFailedAttempts(String email);

    void lockAccount(String email);

    boolean existByEmail(String email);

    boolean existByMobilenumber(Long mobilenumber);

    SignUpDto findByEmail(String email);



    boolean resetPasswordByEmail(SignUpDto signUpDto);



}
