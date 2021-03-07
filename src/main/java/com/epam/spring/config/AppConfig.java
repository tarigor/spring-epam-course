package com.epam.spring.config;

import com.epam.spring.beans.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.Date;


@Configuration
@PropertySource("classpath:client.properties")
public class AppConfig {

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateTimeInstance();
    }

    @Bean
    public Date newDate(){
        return new Date();
    }

    @Resource
    private Environment environment;

    @Bean
    public Client client(){
        Client client = new Client();
        client.setId(environment.getProperty("id"));
        client.setFullName(environment.getProperty("name"));
        client.setGreeting(environment.getProperty("greeting"));
        return client;
    }


}
