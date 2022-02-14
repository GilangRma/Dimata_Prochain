package com.dimata.demo.app.prochain_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class DimataProchainApplication {

	public static void main(String[] args) {
		SpringApplication.run(DimataProchainApplication.class, args);
	}

}
