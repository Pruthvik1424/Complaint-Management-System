package com.xworkz.complaintManagementSystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("com.xworkz.complaintManagementSystem")
public class SpringMvcConfiguration {

    public SpringMvcConfiguration(){
        System.out.println("Running no arg const in SpringMvcConfiguration ");
    }







}
