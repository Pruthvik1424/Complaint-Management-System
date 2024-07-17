package com.xworkz.complaintManagementSystem.dto;

import org.springframework.web.bind.annotation.SessionAttributes;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "sign_up")

public class SignUpDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "please enter your first name ")
    @Column(name = "first_name")
    private String fname;

    @NotNull(message = "please enter your last name ")
    @Column(name = "last_name")
    private String lname;

    @NotNull(message = "please enter your email id ")
    @Column(name = "email_id")
    private String email;

    @NotNull(message = "please enter valid mobile number")
    @Min(1111111111)
    @Max(9999999999L)
    @Column(name = "mobile_number")
    private Long mobilenumber;

    @NotNull(message = "please enter valid mobile number")
    @Min(1111111111)
    @Max(9999999999L)
    @Column(name="alternate_mobile_number")
    private Long alternatemobilenumber;

    @NotNull(message = "please enter your address below ")
    @Column(name = "address")
    private String address;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private LocalDateTime updatedOn;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "password")
    private String password;

    @Column(name = "failed_login_attempts")
    private int failedLoginAttempts = 0; // Default value

    @Column(name = "account_locked")
    private boolean accountLocked = true;// Default value


    @Transient
    private String agree;


    @Column(name = "profile_image")
    private String profileImage;

    public SignUpDto(){
        System.out.println("No param constructor in signUpDto ");
    }

    public SignUpDto(int id, String fname, String lname, String email, Long mobilenumber, Long alternatemobilenumber, String address, String createdBy, LocalDateTime createdOn, String updatedBy, LocalDateTime updatedOn, boolean isActive, String password, int failedLoginAttempts, boolean accountLocked, String agree, String profileImage) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.mobilenumber = mobilenumber;
        this.alternatemobilenumber = alternatemobilenumber;
        this.address = address;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.updatedBy = updatedBy;
        this.updatedOn = updatedOn;
        this.isActive = isActive;
        this.password = password;
        this.failedLoginAttempts = failedLoginAttempts;
        this.accountLocked = accountLocked;
        this.agree = agree;
        this.profileImage = profileImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(Long mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public Long getAlternatemobilenumber() {
        return alternatemobilenumber;
    }

    public void setAlternatemobilenumber(Long alternatemobilenumber) {
        this.alternatemobilenumber = alternatemobilenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    @Override
    public String toString() {
        return "SignUpDto{" +
                "id=" + id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", mobilenumber=" + mobilenumber +
                ", alternatemobilenumber=" + alternatemobilenumber +
                ", address='" + address + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn=" + createdOn +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedOn=" + updatedOn +
                ", isActive=" + isActive +
                ", password='" + password + '\'' +
                ", failedLoginAttempts=" + failedLoginAttempts +
                ", accountLocked=" + accountLocked +
                ", agree='" + agree + '\'' +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }


}
