package com.RecruitService.RecruiterService;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@SpringBootApplication
public class RecruiterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecruiterServiceApplication.class, args);
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	/*
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000")); //only requests from these origins (URL of the web page) are accepted
        config.setAllowedMethods(Arrays.asList("GET","POST"));
	config.setAllowedHeaders(Arrays.asList("content-type"));	  
        config.setAllowCredentials(true);   //this is important in preflight request (OPTIONS) to tell the browser, that it can send credentials, e.g. cookies along the subsequent request
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return configSource;
    }
    */
	 
}
