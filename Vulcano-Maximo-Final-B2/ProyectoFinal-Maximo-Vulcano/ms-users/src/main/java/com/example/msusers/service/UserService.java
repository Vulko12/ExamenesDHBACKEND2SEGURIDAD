package com.example.msusers.service;

import com.example.msusers.model.Bill;
import com.example.msusers.model.User;
import com.example.msusers.repository.FeignBillsRepository;
import com.example.msusers.repository.IUserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private IUserRepository userRepository;
    private FeignBillsRepository feignBillsRepository;
    
    public UserService(IUserRepository userRepository, FeignBillsRepository feignBillsRepository) {
        this.userRepository = userRepository;
        this.feignBillsRepository = feignBillsRepository;
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
    
    public List<Bill> getByUserId(String id) {
        ResponseEntity<List<Bill>> response = feignBillsRepository.getByUserId(id);
        return response.getBody();
    }
    
}
