package com.sik.study.spring5.ch04.config;

import com.sik.study.spring5.ch04.FoodProviderService;
import com.sik.study.spring5.ch04.highschool.FoodProviderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("highschool")
public class HighshcoolConfig {
    @Bean
    public FoodProviderService foodProviderService() {
        return new FoodProviderServiceImpl();
    }
}
