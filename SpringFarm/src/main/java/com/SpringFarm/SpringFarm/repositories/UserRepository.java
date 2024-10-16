package com.SpringFarm.SpringFarm.repositories;

import com.SpringFarm.SpringFarm.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
     Optional<User> findById(@NonNull Long id);
     Optional<User> findByEmail(@NonNull String email);
     boolean existsByEmail(@NonNull String email);
     boolean existsById( @NonNull Long id);
}
