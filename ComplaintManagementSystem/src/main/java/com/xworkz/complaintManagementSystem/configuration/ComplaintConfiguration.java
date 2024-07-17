package com.xworkz.complaintManagementSystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

//onblur="return validateForm()"

@Configuration
@ComponentScan("com.xworkz.complaintManagementSystem")
@PropertySource("classpath:application.properties")
@EnableWebMvc


public class ComplaintConfiguration implements WebMvcConfigurer {
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


    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/javascript/");
        //set path for image display


            // Add mapping for images, CSS, and JS files
            registry.addResourceHandler("/images/**")
                    .addResourceLocations("file:///C:/Users/User/Desktop/Complaint Management System/ImageUpload/");

    }

    @Bean
    public StandardServletMultipartResolver standardServletMultipartResolver() {
        return new StandardServletMultipartResolver();
    }


    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(5242880); // 5MB
        return multipartResolver;
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }



}
