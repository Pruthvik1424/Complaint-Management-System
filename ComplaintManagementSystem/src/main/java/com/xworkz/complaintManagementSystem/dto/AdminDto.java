package com.xworkz.complaintManagementSystem.dto;


import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Data
@ToString
@Slf4j
@Entity

@Table(name = "admin_details")
public class AdminDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private int adminId;

    @Column(name = "name")
    private String name;

    private String email;

    private String password;



}
