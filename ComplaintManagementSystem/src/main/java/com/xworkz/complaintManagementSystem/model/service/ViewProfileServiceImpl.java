package com.xworkz.complaintManagementSystem.model.service;

import com.xworkz.complaintManagementSystem.dto.SignUpDto;
import com.xworkz.complaintManagementSystem.model.repo.ViewProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class ViewProfileServiceImpl implements ViewProfileService {

    @Autowired
    private ViewProfileRepo viewProfileRepo;

    @Autowired
    private HttpSession httpSession;

    public ViewProfileServiceImpl() {
        System.out.println("Running View profile service impl....");
    }

    @Override
    public SignUpDto findByUserEmail(String email) {
        System.out.println("running findbyuseremail method in veiwProfileServiceImpl");
        SignUpDto result = viewProfileRepo.findByUserEmail(email);
        if(result != null){
            System.out.println("searched result in service");
            return result;
        }else {
            System.out.println("not searched result in service");
        }
        return viewProfileRepo.findByUserEmail(email);
    }

    @Override
    public String getSignInUserEmail() {
        return (String) httpSession.getAttribute("signedInUserEmail");
    }

    @Override
    public SignUpDto updateEditedUser(SignUpDto signUpDto) {
        SignUpDto existingUser = viewProfileRepo.findByUserEmail(signUpDto.getEmail());
        if (existingUser != null) {
            existingUser.setFname(signUpDto.getFname());
            existingUser.setLname(signUpDto.getLname());
            existingUser.setMobilenumber(signUpDto.getMobilenumber());
            existingUser.setAlternatemobilenumber(signUpDto.getAlternatemobilenumber());
            existingUser.setAddress(signUpDto.getAddress());
            return viewProfileRepo.updateEditedUser(existingUser);
        } else {
            System.out.println("No user found to update: " + signUpDto.getEmail());
        }
        return null;
    }

}
