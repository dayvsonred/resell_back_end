package com.resell.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ResellGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResellGatewayApplication.class, args);
	}

}
