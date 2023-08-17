package com.osmonailev.notification.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("classpath:mail-props.properties")
public class mailConfig {
    @Autowired
    private Environment environment;

    @Bean
    public JavaMailSender javaMailSender() {
     JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
     mailSender.setHost("smtp.gmail.com");
     mailSender.setPort(587);

     mailSender.setUsername(environment.getProperty("mail.username"));
     mailSender.setUsername(environment.getProperty("mail.password"));

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol",environment.getProperty("mail.transport.protocol"));
        properties.put("mail.smtp.auto",environment.getProperty("mail.smtp.auto"));
        properties.put("emil.smtp.starttls.enable",environment.getProperty("emil.smtp.starttls.enable"));
        properties.put("mail.debug",environment.getProperty("mail.debug"));
        return mailSender;
    }
}
