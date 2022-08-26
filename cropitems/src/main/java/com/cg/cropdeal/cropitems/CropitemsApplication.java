package com.cg.cropdeal.cropitems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CropitemsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CropitemsApplication.class, args);
	}

}
