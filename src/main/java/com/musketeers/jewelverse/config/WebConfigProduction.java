package com.musketeers.jewelverse.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigProduction implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/api/**")
                // Production: Specify exact frontend URLs
                .allowedOrigins(
                    "http://localhost:3000",           // React development
                    "http://localhost:4200",           // Angular development  
                    "https://yourfrontend.com",        // Production frontend
                    "https://www.yourfrontend.com"     // Production frontend with www
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders(
                    "Authorization", 
                    "Content-Type", 
                    "X-Requested-With", 
                    "Accept"
                )
                .allowCredentials(true)                 // Allow cookies/credentials
                .maxAge(3600);                         // Cache preflight for 1 hour
    }
}
