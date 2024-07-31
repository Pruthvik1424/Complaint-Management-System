package com.xworkz.complaintManagementSystem.dto;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@ToString
@Slf4j
@Entity

@Table(name = "departments_details")
public class AddDepartmentsDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departments_id")
    private int departmentsId;

    @NotNull(message = "please select departments type")
    @Column(name = "departments_type")
    private String departmentsType;

    @NotNull(message = "please enter department adddress")
    @Column(name = "departments_address")
    private String departmentsAddress;

    @NotNull(message = "please enter city of department")
    @Column(name = "departments_city")
    private String departmentsCity;

   // @NotNull(message = "please select country")

    @Column(name = "no_of_employee")
    private int noOfEmployee;



    public AddDepartmentsDto(){
        System.out.println("Running AddDepartments Dto....");
    }

    public AddDepartmentsDto(int departmentsId, String departmentsType, String departmentsAddress, String departmentsCity, int noOfEmployee) {
        this.departmentsId = departmentsId;
        this.departmentsType = departmentsType;
        this.departmentsAddress = departmentsAddress;
        this.departmentsCity = departmentsCity;
        this.noOfEmployee = noOfEmployee;
    }

    public int getDepartmentsId() {
        return departmentsId;
    }

    public void setDepartmentsId(int departmentsId) {
        this.departmentsId = departmentsId;
    }

    public String getDepartmentsType() {
        return departmentsType;
    }

    public void setDepartmentsType(String departmentsType) {
        this.departmentsType = departmentsType;
    }

    public String getDepartmentsAddress() {
        return departmentsAddress;
    }

    public void setDepartmentsAddress(String departmentsAddress) {
        this.departmentsAddress = departmentsAddress;
    }

    public String getDepartmentsCity() {
        return departmentsCity;
    }

    public void setDepartmentsCity(String departmentsCity) {
        this.departmentsCity = departmentsCity;
    }

    public int getNoOfEmployee() {
        return noOfEmployee;
    }

    public void setNoOfEmployee(int noOfEmployee) {
        this.noOfEmployee = noOfEmployee;
    }

    @Override
    public String toString() {
        return "AddDepartmentsDto{" +
                "departmentsId=" + departmentsId +
                ", departmentsType='" + departmentsType + '\'' +
                ", departmentsAddress='" + departmentsAddress + '\'' +
                ", departmentsCity='" + departmentsCity + '\'' +
                ", noOfEmployee=" + noOfEmployee +
                '}';
    }
}
