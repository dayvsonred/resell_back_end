package com.resell.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ResellBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResellBffApplication.class, args);
	}

}
