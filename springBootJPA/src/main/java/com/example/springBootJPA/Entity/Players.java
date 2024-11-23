package com.example.springBootJPA.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Set;

@Entity
@Table(name = "CRICK_PLAYER")
@Getter
@Setter
public class Players {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long playersId;
    @Column(name = "PLAYERS_NAME", nullable = false)
    private String name;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "JERSEY_NO")
    private Long jerseyNumber;
    @Column(name = "BATTING_STYLE")
    private String battingStyle;

    @Column(name = "BOWLING_STYLE")
    private String bowlingStyle;

    @OneToOne
    @JoinColumn(name = "players_personalInfo_Id")
    private PersonalInfo personalInfo;


    // Many player can be play in one Team ;-
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToMany
    @JoinTable(name = "players_franchise", joinColumns = @JoinColumn(name = "players_id"), inverseJoinColumns = @JoinColumn(name = "franchise_id")


    )
    private Set<Franchise> franchiseSet;


}

