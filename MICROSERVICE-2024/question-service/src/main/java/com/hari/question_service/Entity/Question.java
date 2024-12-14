package com.hari.question_service.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity


@Table(name = "Questions")

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID")
    private Integer id;
    @Column(name = "QUESTION_TITLE")
    private String questionTitle;
    @Column(name = "OPTION_1")
    private String option1;
    @Column(name = "OPTION_2")
    private String option2;
    @Column(name = "OPTION_3")
    private String option3;
    @Column(name = "OPTION_4")
    private String option4;
    @Column(name = "RIGHTS_ANSWER")
    private String rightAnswer;
    @Column(name = "DIFFICULTY_LEVEL")
    private String difficultyLevel;
    @Column(name = "CATEGORY")
    private String category;



}
