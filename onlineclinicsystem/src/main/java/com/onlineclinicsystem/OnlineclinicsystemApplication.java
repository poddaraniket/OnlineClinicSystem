package com.onlineclinicsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.onlineclinicsystem")
public class OnlineclinicsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineclinicsystemApplication.class, args);
	}

}
