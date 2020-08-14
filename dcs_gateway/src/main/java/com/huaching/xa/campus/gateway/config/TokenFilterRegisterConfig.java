package com.huaching.xa.campus.gateway.config;

import com.huaching.xa.campus.gateway.filter.ErrorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenFilterRegisterConfig {

    @Bean
    ErrorFilter errorFilter(){
        return new ErrorFilter();
    }

}
