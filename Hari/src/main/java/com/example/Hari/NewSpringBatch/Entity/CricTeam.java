package com.example.Hari.NewSpringBatch.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

@Table(name = "CRICZ")


public class CricTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID")
    private Long id ;

    @Column(name = "NAME")
    private String name ;

    @Column(name = "ROLE")
    private String role;


}
