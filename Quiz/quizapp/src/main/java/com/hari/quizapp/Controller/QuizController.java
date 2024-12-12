package com.hari.quizapp.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")
public class QuizController {


    @PostMapping("/create")

    public ResponseEntity<String> createQuiz(@RequestParam String category ,@RequestParam String numQ , @RequestParam String title ){
        return new ResponseEntity<>("Hi", HttpStatus.OK);
    }
}
