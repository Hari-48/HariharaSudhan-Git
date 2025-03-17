package com.example.springBootJPA.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "USER")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name ;
    private String firstName;
    private String lastName;
    private String role;
    private String gender;
}
