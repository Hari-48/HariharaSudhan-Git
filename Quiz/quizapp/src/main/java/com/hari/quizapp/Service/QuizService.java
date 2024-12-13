package com.hari.quizapp.Service;

import com.hari.quizapp.Entity.Question;
import com.hari.quizapp.Entity.Quiz;
import com.hari.quizapp.Model.QuestionWrapper;
import com.hari.quizapp.Model.Response;
import com.hari.quizapp.Repository.QuestionRepo;
import com.hari.quizapp.Repository.QuizRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QuizService {
    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    QuizRepo quizRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionRepo.findQusByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        try {
            quizRepo.save(quiz);
            return new ResponseEntity<String>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed", HttpStatus.NOT_ACCEPTABLE);


    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Long id) {
        Optional<Quiz> quiz = quizRepo.findById(id);

        try {
            List<Question> questionsFromDB = quiz.get().getQuestions();
            List<QuestionWrapper> questionForUser = questionsFromDB.stream().map(question -> new QuestionWrapper(question.getId(),
                    question.getQuestionTitle(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4())).toList();
            return new ResponseEntity<>(questionForUser, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<List<QuestionWrapper>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Integer> calculateResult(Long id, List<Response> responses) {
        try {
            Quiz quiz = quizRepo.findById(id).get();
            List<Question> questions = quiz.getQuestions();
            int score = 0;
            for (Response response : responses) {
                for (Question question : questions) {
                    if (question.getRightAnswer().equals(response.getResponse())) {
                        score++;
                    }
                }
            }
            return new ResponseEntity<Integer>(score, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
