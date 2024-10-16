package com.SpringFarm.SpringFarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringFarmApplication {

	@GetMapping("/")
	public ResponseEntity<String> connect(){
		return ResponseEntity.ok("Hello World!");
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringFarmApplication.class, args);
	}

}
