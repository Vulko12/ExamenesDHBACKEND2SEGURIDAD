package com.example.msusers.controller;

import com.example.msusers.model.User;
import com.example.msusers.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserRestController {
    private UserService userService;
    
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> findById(@PathVariable String id) {
        Optional<User> foundUser = userService.findById(id);
        if (foundUser.isPresent()) {
            foundUser.get().setBills(userService.getByUserId(id));
            return ResponseEntity.ok(foundUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
