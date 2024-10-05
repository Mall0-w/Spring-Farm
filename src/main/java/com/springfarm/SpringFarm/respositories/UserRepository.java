package com.springfarm.SpringFarm.respositories;

import com.springfarm.SpringFarm.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
