package com.hari.quizapp.Controller;

import com.hari.quizapp.Entity.Question;
import com.hari.quizapp.Service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("question")
@Slf4j
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("/allQuestion")
    public ResponseEntity<List<Question>> getAllQuestion() {
        return questionService.getAllQuestion();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }

    //save All Question
    @PostMapping("/save")
    public ResponseEntity<?> saveQuestions(@RequestBody List<Question> questions){
        return questionService.saveQuestions(questions);
    }


}
