package com.rickyslash.nbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NbsApplication {

	public static void main(String[] args) {
		System.out.println("Hello World. This is Rickyslash!");
		SpringApplication.run(NbsApplication.class, args);
	}

}
