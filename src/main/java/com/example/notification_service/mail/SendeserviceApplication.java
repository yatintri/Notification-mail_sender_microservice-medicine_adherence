package com.example.notification_service.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SendeserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendeserviceApplication.class, args);
	}

}
