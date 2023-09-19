package com.example.msusers.model;

import lombok.*;

import java.util.List;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class User {
    private String id;
    private String userName;
    private List<Bill> bills;
}
