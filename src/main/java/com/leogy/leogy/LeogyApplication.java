package com.leogy.leogy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.leogy.leogy.model")
public class LeogyApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeogyApplication.class, args);
	}
	


}
