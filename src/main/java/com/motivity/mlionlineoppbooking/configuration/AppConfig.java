package com.motivity.mlionlineoppbooking.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {

//    @Bean
//    public JavaMailSender javaMailSender()
//    {
//        return new JavaMailSenderImpl();
//    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();

    }
    @Bean
    public  ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
}
