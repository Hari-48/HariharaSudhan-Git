package com.telusko.quizApp.Controller;

import com.telusko.quizApp.Entity.Question;
import com.telusko.quizApp.Service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("question")

public class QuestionController {
    private static final Logger log = LoggerFactory.getLogger(QuestionService.class);


    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestion")
    public List<Question> getAllQuestion() {
        List<Question> questions = questionService.getAllQuestion();
        log.info("Questions Response: {}", questions);
        questions.forEach(question -> log.info("Question Title: {}", question.getQuestionTitle()));



        return questions;

    }


}