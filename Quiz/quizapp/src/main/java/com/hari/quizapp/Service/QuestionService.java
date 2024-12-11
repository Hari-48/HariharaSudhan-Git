package com.hari.quizapp.Service;

import com.hari.quizapp.Entity.Question;
import com.hari.quizapp.Repository.QuestionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;
    public List<Question> getAllQuestion() {
        return questionRepo.findAll();
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionRepo.findByCategory(category);
    }

    public ResponseEntity<?> saveQuestions(List<Question> questions) {
        questionRepo.saveAll(questions);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }
}
