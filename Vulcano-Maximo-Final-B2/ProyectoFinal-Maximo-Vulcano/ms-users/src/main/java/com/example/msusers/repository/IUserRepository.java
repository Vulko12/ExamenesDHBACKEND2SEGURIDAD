package com.example.msusers.repository;

import com.example.msusers.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    List<User> findAll();
    Optional<User> findById(String id);
}
