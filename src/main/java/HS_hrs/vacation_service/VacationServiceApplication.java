package HS_hrs.vacation_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VacationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacationServiceApplication.class, args);
	}

}