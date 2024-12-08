package com.telusko.quizApp.Controller;

import com.telusko.quizApp.Model.Question;
import com.telusko.quizApp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {


    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestion")

    public List<Question> getAllQuestion() {
        //return "Getting All Question";
        return questionService.getAllQuestion();

    }


}