package com.example.mtbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class MovieTickBookingSystemApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieTickBookingSystemApiApplication.class, args);
	}

}
