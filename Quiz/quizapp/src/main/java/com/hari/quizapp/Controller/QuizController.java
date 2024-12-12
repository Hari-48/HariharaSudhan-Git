package com.hari.quizapp.Controller;

import com.hari.quizapp.Model.QuestionWrapper;
import com.hari.quizapp.Service.QuizService;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {


    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category ,@RequestParam int numQ , @RequestParam String title ){
        return quizService.createQuiz(category,numQ,title);
    }


    @GetMapping("/getQues/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable Long id){
        return quizService.getQuizQuestions(id);

    }
}
