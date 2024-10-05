package com.springfarm.SpringFarm.controllers;

import com.springfarm.SpringFarm.dtos.UserCreateDTO;
import com.springfarm.SpringFarm.models.User;
import com.springfarm.SpringFarm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    private ResponseEntity<User> addUser(@RequestBody UserCreateDTO user){

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
