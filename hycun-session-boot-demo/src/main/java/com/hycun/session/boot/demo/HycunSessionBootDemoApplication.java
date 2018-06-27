package com.hycun.session.boot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class HycunSessionBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HycunSessionBootDemoApplication.class, args);
	}
}
