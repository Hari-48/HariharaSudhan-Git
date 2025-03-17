package com.example.Hari.Mapping.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Table(name = "TEAM")
@Data
@Getter
@Setter

public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country ;
    private Long ranking;



    @OneToMany(mappedBy = "team")
    private Set<Players> playersSet ;

}
