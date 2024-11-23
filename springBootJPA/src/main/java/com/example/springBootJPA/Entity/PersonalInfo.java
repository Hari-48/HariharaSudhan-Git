package com.example.springBootJPA.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "PLAYERS_INFO")
@Data
@Getter
@Setter
public class PersonalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long infoId;

    @Column(name = "BIRTH_PLACE")
    private String birthPlace;

    @Column(name = "AGE")
    private String age;

    @Column(name = "DOB")
    @JsonFormat(pattern = "dd-MM-yyy")
    private LocalDate dob;

    @Column(name = "MATCHES_PLAYED")
    private Long matchesPlayed;



//    @OneToOne
//    @JoinColumn(name = "players_id")
//    private Players players;





}
