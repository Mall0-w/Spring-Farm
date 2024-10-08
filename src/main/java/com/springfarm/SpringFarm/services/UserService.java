package com.springfarm.SpringFarm.services;

import com.springfarm.SpringFarm.dtos.UserCreateDTO;
import com.springfarm.SpringFarm.exceptions.EmailAlreadyExistsException;
import com.springfarm.SpringFarm.models.Roles;
import com.springfarm.SpringFarm.models.User;
import com.springfarm.SpringFarm.repositories.RolesRepository;
import com.springfarm.SpringFarm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(UserCreateDTO dto) {
        if(userRepository.existsByEmail(dto.getEmail()))
            throw new EmailAlreadyExistsException("Email already exists: " + dto.getEmail());

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassHash(passwordEncoder.encode(dto.getPassword()));

        if (dto.getRoles() != null && !dto.getRoles().isEmpty()) {
            Set<Roles> roles = new HashSet<>();
            for (String roleName : dto.getRoles()) {
                Roles role = rolesRepository.findByName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
                roles.add(role);
            }
            user.setRoles(roles);
        } else {
            // Assign default role if none provided
            Roles userRole = rolesRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Default role not found"));
            user.getRoles().add(userRole);
        }

        return userRepository.save(user);
    }
}
