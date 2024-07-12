package com.xworkz.complaintManagementSystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//onblur="return validateForm()"

@Configuration
@ComponentScan("com.xworkz.complaintManagementSystem")
@EnableWebMvc
public class ComplaintConfiguration {
    public ComplaintConfiguration(){
        System.out.println("Complaint Configuratin is created");
    }

    @Bean
    public ViewResolver viewResolver()
    {
        System.out.println("Registring viewResolver");
        InternalResourceViewResolver internalResourceViewResolver=new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(5242880); // 5MB
        return multipartResolver;
    }

}
