package com.wondersgroup.datareport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DatareportApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatareportApplication.class, args);
	}
}
