package com.resell.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ResellRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResellRegisterApplication.class, args);
	}

}
