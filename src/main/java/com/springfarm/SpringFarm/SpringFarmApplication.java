package com.springfarm.SpringFarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class SpringFarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFarmApplication.class, args);
	}


	@GetMapping("/")
	@PreAuthorize("permitAll()")
	public ResponseEntity<String> testConn(){
		return ResponseEntity.ok("This is the Spring Farm Backend");
	}
}
