package com.telusko.quizApp.Service;

import com.telusko.quizApp.Model.Question;
import com.telusko.quizApp.Repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepo questionRepo;

    public List<Question> getAllQuestion() {
        return questionRepo.findAll();
    }
}
