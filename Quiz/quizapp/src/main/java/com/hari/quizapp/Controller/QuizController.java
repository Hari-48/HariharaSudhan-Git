package com.hari.quizapp.Controller;

import com.hari.quizapp.Entity.Question;
import com.hari.quizapp.Service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("question")
@Slf4j
public class QuizController {

    @Autowired
    QuestionService questionService;
    @GetMapping("/allQuestion")
    public List<Question> getAllQuestion() {
//        List<Question> questions = questionService.getAllQuestion();
//        log.info("Questions Response: {}", "questions");
//        questions.forEach(question -> log.info("Question Title: {}", question.getQuestionTitle()));
        return questionService.getAllQuestion();

    }

}
