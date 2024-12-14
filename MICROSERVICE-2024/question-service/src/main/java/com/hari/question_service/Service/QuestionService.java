package com.hari.question_service.Service;

import com.hari.question_service.Entity.Question;
import com.hari.question_service.Model.QuestionWrapper;
import com.hari.question_service.Model.Response;
import com.hari.question_service.Repository.QuestionRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QuestionService {

    @Autowired
    QuestionRepo questionRepo;
    public ResponseEntity<List<Question>> getAllQuestion() {
        try {
            return new ResponseEntity<>(questionRepo.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public  ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionRepo.findByCategory(category),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }



    public ResponseEntity<?> saveQuestions(List<Question> questions) {
        try {
            questionRepo.saveAll(questions);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(String category, Integer noOfQuestions) {
        List<Integer> questionId = questionRepo.findQusByCategory(category,noOfQuestions);
        return new ResponseEntity<>(questionId,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsByIds(List<Integer> questionsIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions =questionRepo.findAllQuestionsByIds(questionsIds);
        wrappers=questions.stream().map(question -> new QuestionWrapper(question.getId(),question.getQuestionTitle(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4())).toList();
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score=0;
        for(Response response:responses){
            String getCorrectAnswer = questionRepo.findById(response.getId()).get().getRightAnswer();
            if(response.getResponse().equals(getCorrectAnswer)){
                score++;
            }
        }
        return new ResponseEntity<>(score,HttpStatus.OK);



    }
}
