package com.springfarm.SpringFarm;

import com.springfarm.SpringFarm.controllers.UserController;
import com.springfarm.SpringFarm.dtos.ErrorResponseDTO;
import com.springfarm.SpringFarm.dtos.LoginDTO;
import com.springfarm.SpringFarm.dtos.UserCreateDTO;
import com.springfarm.SpringFarm.exceptions.EmailAlreadyExistsException;
import com.springfarm.SpringFarm.models.User;
import com.springfarm.SpringFarm.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTests {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoginUser_Success() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("test@example.com");
        loginDTO.setPassword("password");

        User user = new User(); // Assume User has necessary fields

        when(userService.loginUser(any(LoginDTO.class))).thenReturn(user);

        ResponseEntity<String> response = userController.loginUser(loginDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("login Successful", response.getBody());
    }

    @Test
    void testLoginUser_InvalidCredentials() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("test@example.com");
        loginDTO.setPassword("wrongPassword");

        when(userService.loginUser(any(LoginDTO.class))).thenReturn(null);

        ResponseEntity<String> response = userController.loginUser(loginDTO);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid password or Email", response.getBody());
    }

    @Test
    void testAddUser_Success() {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setEmail("newuser@example.com");
        userCreateDTO.setPassword("password");

        User newUser = new User(); // Assume User has necessary fields
        when(userService.createUser(any(UserCreateDTO.class))).thenReturn(newUser);

        ResponseEntity<?> response = userController.addUser(userCreateDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newUser, response.getBody());
    }

    @Test
    void testAddUser_EmailAlreadyExists() {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setEmail("existinguser@example.com");
        userCreateDTO.setPassword("password");

        when(userService.createUser(any(UserCreateDTO.class))).thenThrow(new EmailAlreadyExistsException("Email already exists"));

        ResponseEntity<?> response = userController.addUser(userCreateDTO);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Email already exists: " + userCreateDTO.getEmail(), ((ErrorResponseDTO) response.getBody()).getMessage());
    }
}
