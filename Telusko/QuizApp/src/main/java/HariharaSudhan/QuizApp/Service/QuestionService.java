package com.telusko.quizApp.Service;

import com.telusko.quizApp.Entity.Question;
import com.telusko.quizApp.Repository.QuestionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class QuestionService {

    private static final Logger log = LoggerFactory.getLogger(QuestionService.class);

    @Autowired
    QuestionRepo questionRepo;
    public List<Question> getAllQuestion() {
        List<Question> questions = questionRepo.findAll();
        log.info("Fetched Questions: {}", questions);
        return questions;
    }
}
