package com.hari.question_service.Controller;


import com.hari.question_service.Entity.Question;
import com.hari.question_service.Model.QuestionWrapper;
import com.hari.question_service.Model.Response;
import com.hari.question_service.Service.QuestionService;
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
        log.info("---");
        return questionService.getAllQuestion();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) {
        return questionService.getQuestionByCategory(category);
    }

    //save All Question
    @PostMapping("/save")
    public ResponseEntity<?> saveQuestions(@RequestBody List<Question> questions) {
        return questionService.saveQuestions(questions);
    }


    @PostMapping("/generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestParam String category,@RequestParam Integer noOfQuestions){
        return questionService.generateQuestionsForQuiz(category,noOfQuestions);
    }

    @GetMapping("/getQuestionsById")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(@RequestParam List<Integer> questionsIds){
        return questionService.getQuestionsByIds(questionsIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }


}
