package com.Portfolio.LogIn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class CorsConfigImpl implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        
        registry.addMapping("/**");
        
        registry.addMapping("/login")
                    .allowedOrigins("*")
                    .allowedMethods("*")
                    .allowedHeaders("*");
        
        registry.addMapping("/api/**")
                    .allowedOrigins("*")
                    .allowedMethods("*");
        
        registry.addMapping("/media/****")
                    .allowedOrigins("*")
                    .allowedMethods("*")
                    .allowedHeaders("*");
    }
    
}
