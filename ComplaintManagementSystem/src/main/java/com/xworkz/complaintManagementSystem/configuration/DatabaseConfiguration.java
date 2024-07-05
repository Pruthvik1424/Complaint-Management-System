package com.xworkz.complaintManagementSystem.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    public DatabaseConfiguration(){
        System.out.println("Database configuration is created");
    }

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.driver}")
    private String driver;


    @Bean
    public DataSource dataSource()
    {
        System.out.println("Registered DataSource");
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(url);
        driverManagerDataSource.setUsername(username);
        driverManagerDataSource.setPassword(password);
        driverManagerDataSource.setDriverClassName(driver);
        return driverManagerDataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean factoryBean(DataSource dataSource){
     LocalContainerEntityManagerFactoryBean bean= new LocalContainerEntityManagerFactoryBean();
     bean.setDataSource(dataSource);
        JpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(jpaVendorAdapter);
        bean.setPackagesToScan("com.xworkz.complaintManagementSystem");
        return bean;
    }
}
