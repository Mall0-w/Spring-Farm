package com.springfarm.SpringFarm;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springfarm.SpringFarm.controllers.UserController;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTests {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testUsers() throws Exception {
        mockMvc.perform(get("/users/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("Test"));
    }

    @Test
    void loginUser() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("test@example.com");
        loginDTO.setPassword("password");

        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("test"));
    }

    @Test
    void addUser_Success() throws Exception {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setEmail("newuser@example.com");
        userCreateDTO.setFirstName("John");
        userCreateDTO.setLastName("Doe");
        userCreateDTO.setPassword("password123");
        userCreateDTO.setRoles(new HashSet<>());

        User newUser = new User();
        newUser.setId(1L);
        newUser.setEmail(userCreateDTO.getEmail());
        newUser.setFirstName(userCreateDTO.getFirstName());
        newUser.setLastName(userCreateDTO.getLastName());

        when(userService.createUser(any(UserCreateDTO.class))).thenReturn(newUser);

        mockMvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("newuser@example.com"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    void addUser_EmailAlreadyExists() throws Exception {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        userCreateDTO.setEmail("existing@example.com");
        userCreateDTO.setFirstName("Jane");
        userCreateDTO.setLastName("Doe");
        userCreateDTO.setPassword("password123");
        userCreateDTO.setRoles(new HashSet<>());

        when(userService.createUser(any(UserCreateDTO.class)))
                .thenThrow(new EmailAlreadyExistsException("Email already exists: " + userCreateDTO.getEmail()));

        mockMvc.perform(post("/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userCreateDTO)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Email already exists: existing@example.com"))
                .andExpect(jsonPath("$.details").value("Please use a different email address."));
    }
}