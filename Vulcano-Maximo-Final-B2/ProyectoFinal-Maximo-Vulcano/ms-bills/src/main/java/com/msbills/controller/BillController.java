package com.msbills.controller;

import com.msbills.models.Bill;
import com.msbills.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService service;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_user')")
    public ResponseEntity<List<Bill>> getAll() {
        return ResponseEntity.ok().body(service.getAllBill());
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<List<Bill>> getByUserId(@PathVariable String id) {
        return ResponseEntity.ok().body(service.getByUserId(id));
    }
    
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('GROUP_/PROVIDERS')")
    public ResponseEntity<Bill> addBill(@RequestBody Bill bill) {
        return ResponseEntity.ok().body(service.addBill(bill));
    }
}
