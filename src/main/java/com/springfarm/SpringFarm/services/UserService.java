package com.springfarm.SpringFarm.services;

import com.springfarm.SpringFarm.respositories.RolesRepository;
import com.springfarm.SpringFarm.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;
}
