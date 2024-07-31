package com.xworkz.complaintManagementSystem.dto;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Slf4j
@Data
@ToString

@Table(name = "add_department_admin_details")
public class DepartmentAdminDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_admin_id")
    private int departmentAdminId;

    @Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;

    @Column(name = "email")
    private String email;

    @Column(name = "mobilenumber")
    private String mobilenumber;

    @Column(name = "password")
    private String password;



    public DepartmentAdminDto(){
        System.out.println("DepartmentAdmin Dto is created...");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public int getDepartmentAdminId() {
        return departmentAdminId;
    }

    public void setDepartmentAdminId(int departmentAdminId) {
        this.departmentAdminId = departmentAdminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    @Override
    public String toString() {
        return "DepartmentAdminDto{" +
                "departmentAdminId=" + departmentAdminId +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", email='" + email + '\'' +
                ", mobilenumber='" + mobilenumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
