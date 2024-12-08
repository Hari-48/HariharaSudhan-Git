package com.telusko.quizApp.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Question")
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    private String difficultyLevel;


}
