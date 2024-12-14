package com.example.springBootJPA.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Table(name = "FRANCHISE")
@Data

public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String host;

    @ManyToMany(mappedBy = "franchiseSet")
    private Set<Players> playersSet ;

}
