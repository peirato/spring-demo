package com.peirato.gray;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
public class GrayReleaseServerBApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrayReleaseServerBApplication.class, args);
	}

	@RestController
	class DemoController{

		@Value("${demo-test}")
		private String test;

		@GetMapping("/get")
		public String get(){
			return test;
		}

	}


}
