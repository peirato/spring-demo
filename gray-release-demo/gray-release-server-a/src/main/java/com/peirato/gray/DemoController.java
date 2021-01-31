package com.peirato.gray;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public	class DemoController{

		@Resource
		private ServerB serverB;

		@Value("${demo-test}")
		private String test;

		@GetMapping("/get")
		public String get(){
			return test;
		}

		@GetMapping("/get_from_server_b")
		public String getFromB(){
			return serverB.get();
		}

	}