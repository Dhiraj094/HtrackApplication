package com.ktpl.mitadt;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Htrack Backend Application Spring boot Entry Class
 * 
 * @author Dheeraj Dabhade
 * @date 12 June 2021
 *
 */
@SpringBootApplication
@EnableScheduling
public class HtackApplication {

	public static void main(String[] args) {
		SpringApplication.run(HtackApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}