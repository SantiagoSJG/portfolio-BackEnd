package com.Portfolio.LogIn.config;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://portfoliofrontend.web.app");
    }
    
    public void configureCors(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://portfoliofrontend.web.app");
    }
    
}