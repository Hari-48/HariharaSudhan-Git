package com.hari.quiz_service.Model;

import lombok.Data;

@Data
public class QuizDto {

    private String title;
    private String category;
    private Integer noOfQuestions;

}
