package com.shareitinerary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ShareitineraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShareitineraryApplication.class, args);
	}

}
