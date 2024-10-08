package com.springfarm.SpringFarm.controllers;

import com.springfarm.SpringFarm.dtos.ErrorResponseDTO;
import com.springfarm.SpringFarm.dtos.LoginDTO;
import com.springfarm.SpringFarm.dtos.UserCreateDTO;
import com.springfarm.SpringFarm.exceptions.EmailAlreadyExistsException;
import com.springfarm.SpringFarm.models.User;
import com.springfarm.SpringFarm.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    @PreAuthorize("permitAll()")
    private ResponseEntity<String> testUsers(){
        return ResponseEntity.ok("Test");
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    private ResponseEntity<String> loginUser(@Valid @RequestBody LoginDTO login){
        return ResponseEntity.ok("test");
    }

    @PostMapping("/create")
    @PreAuthorize("permitAll()")
    private ResponseEntity<?> addUser(@Valid @RequestBody UserCreateDTO user){
        try {
            User newUser = userService.createUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }catch(EmailAlreadyExistsException e){
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            ErrorResponseDTO errorResponse = new ErrorResponseDTO(
                    "Email already exists: " + user.getEmail(),
                    timestamp,
                    "Please use a different email address."
            );
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }
    }
}
