package com.tfriends.reservoirapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.tfriends.*"})
public class ReservoirapplicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservoirapplicationApplication.class, args);
	}

}
