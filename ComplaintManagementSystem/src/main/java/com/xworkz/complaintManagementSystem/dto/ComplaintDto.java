package com.xworkz.complaintManagementSystem.dto;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Slf4j
@Table(name = "complaint_raise_details")
public class ComplaintDto {

    private static final Logger log = LoggerFactory.getLogger(ComplaintDto.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "complaint_id")
    private int complaintId;

    @Column(name = "complaint_type")
    //@NotNull(message = "please select the complaint type")
    private String complaintType;

    @NotNull(message = "please select country")
    @Column(name = "country")
    private String country;

    //@NotNull(message = "please select state")
    @Column(name = "state")
    private String state;

    //@NotNull(message = "please select city")
    @Column(name = "city")
    private String city;

    //@NotNull(message = "please select area")
    @Column(name = "area")
    private String area;

    @NotNull(message = "please enter your address")
    @Column(name = "address")
    private String address;

    //@NotNull(message = "please give the complaint discription below")
    @Column(name = "description")
    private String description;

    @Transient
    //@NotNull(message = "please check agree")
    private String agree;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private  SignUpDto userid;

    public SignUpDto getUserid() {
        return userid;
    }

    public void setUserid(SignUpDto userid) {
        this.userid = userid;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department",referencedColumnName = "departments_id")
    private AddDepartmentsDto department;

    public AddDepartmentsDto getDepartment() {
        return department;
    }


    public void setDepartment(AddDepartmentsDto department) {
        this.department = department;
    }

    @Column(name = "status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ComplaintDto(){
        log.info("Created Complaint dto...");
    }

    public int getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public String getComplaintType() {
        return complaintType;
    }

    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAgree() {
        return agree;
    }

    public void setAgree(String agree) {
        this.agree = agree;
    }

    @Override
    public String toString() {
        return "ComplaintDto{" +
                "complaintId=" + complaintId +
                ", complaintType='" + complaintType + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", agree='" + agree + '\'' +
                ", userid=" + userid +
                ", department=" + department +
                ", status='" + status + '\'' +
                '}';
    }
}
