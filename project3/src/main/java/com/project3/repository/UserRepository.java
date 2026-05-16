package com.project3.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project3.model.Role;
import com.project3.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByRole(Role role);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}