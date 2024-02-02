package com.example.todo;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class cors implements WebMvcConfigurer{
	public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/**")
	        .allowedOrigins("http://localhost:3000") // Specific origin
	        .allowedMethods("GET", "POST", "PUT", "DELETE") // Include PUT and DELETE
	        .allowCredentials(true)
	        .allowedHeaders("Authorization", "Content-Type");
	}
}
