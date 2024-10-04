package com.springfarm.SpringFarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringFarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFarmApplication.class, args);
	}


	@GetMapping("/")
	public ResponseEntity<String> testConn(){
		return ResponseEntity.ok("This is the Spring Farm Backend");
	}
}
