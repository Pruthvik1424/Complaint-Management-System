package com.xworkz.complaintManagementSystem.model.service;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import com.xworkz.complaintManagementSystem.model.repo.ChangePasswordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordServiceImpl implements ChangePasswordService {

    @Autowired
    private ChangePasswordRepo changePasswordRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    public ChangePasswordServiceImpl() {
        System.out.println("Changepassword service is created");
    }

    @Override
    public boolean changePassword(String email, String oldPassword, String newPassword,String confirmPassword) {
        SignUpDto signUpDto = changePasswordRepo.findByEmailAndPassword(email, oldPassword);
        if (signUpDto != null) {
            signUpDto.setPassword(newPassword);
            return changePasswordRepo.updatePassword(email, newPassword);
        }
        return false;
    }

    @Override
    public void sendChangePasswordToEmail(String toEmail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toEmail);
        simpleMailMessage.setSubject("Password Changed");
        simpleMailMessage.setText("Your password has been successfully changed.");
        simpleMailMessage.setFrom("pruthvik1014@gmail.com");
        javaMailSender.send(simpleMailMessage);
    }
}
