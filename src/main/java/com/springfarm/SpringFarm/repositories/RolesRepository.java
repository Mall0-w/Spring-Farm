package com.springfarm.SpringFarm.repositories;

import com.springfarm.SpringFarm.models.Roles;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RolesRepository extends CrudRepository<Roles, Long> {
    Optional<Roles> findByName(String name);
}
