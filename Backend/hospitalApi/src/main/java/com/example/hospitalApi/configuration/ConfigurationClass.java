package com.example.hospitalApi.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationClass {
    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }

}
