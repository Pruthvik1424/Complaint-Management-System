package com.xworkz.complaintManagementSystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;


@Component
public class MailConfiguration {
    @Bean
    public JavaMailSender javaMailSender(){
       JavaMailSenderImpl emailSender = new JavaMailSenderImpl();
       emailSender.setHost("smtp.gmail.com");
       emailSender.setPort(587);
       emailSender.setUsername("pruthvik1014@gmail.com");
       emailSender.setPassword("uvrk xlnq jyyy ikdn");
        Properties properties=emailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol","smtp");
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.ssl.trust","smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.debug","true");
        return emailSender;
    }
}
