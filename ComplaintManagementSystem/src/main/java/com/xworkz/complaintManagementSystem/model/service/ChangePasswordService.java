package com.xworkz.complaintManagementSystem.model.service;

public interface ChangePasswordService {

    boolean changePassword(String email, String oldPassword, String newPassword, String confirmPassword);

    public void sendChangePasswordToEmail(String toEmail);

}
