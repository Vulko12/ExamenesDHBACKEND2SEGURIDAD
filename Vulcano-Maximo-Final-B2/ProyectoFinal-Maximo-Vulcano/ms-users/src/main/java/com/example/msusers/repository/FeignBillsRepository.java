package com.example.msusers.repository;

import com.example.msusers.configuration.feign.OAuthFeignConfig;
import com.example.msusers.model.Bill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-bills", url = "http://localhost:8079", configuration = OAuthFeignConfig.class)
public interface FeignBillsRepository {
    @GetMapping(value = "/bills/{id}")
    ResponseEntity<List<Bill>> getByUserId(@PathVariable String id);
}
