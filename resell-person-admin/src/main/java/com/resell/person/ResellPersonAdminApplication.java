package com.resell.person;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ResellPersonAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResellPersonAdminApplication.class, args);
	}

}
