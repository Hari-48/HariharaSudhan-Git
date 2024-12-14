package com.hari.quiz_service.Service;

import com.hari.quiz_service.Entity.Quiz;
import com.hari.quiz_service.Model.QuestionWrapper;
import com.hari.quiz_service.Model.QuizInterface;
import com.hari.quiz_service.Model.Response;
import com.hari.quiz_service.Repository.QuizRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QuizService {

    @Autowired
    QuizRepo quizRepo;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, Integer numQ, String title) {
        List<Integer> questionsIds = quizInterface.generateQuestionsForQuiz(category,numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsIds(questionsIds);
        quizRepo.save(quiz);
        return new ResponseEntity<String>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

        Quiz quiz = quizRepo.findById(Long.valueOf(id)).get();
        log.info("Quiz:{}",quiz);
        List<Integer> listOfQuestionsIds = quiz.getQuestionsIds();
        log.info("Quiz:{}",listOfQuestionsIds);

       List<QuestionWrapper> questionForUser =  quizInterface.getQuestionsByIds(listOfQuestionsIds).getBody();
        log.info("Quiz:{}",questionForUser);


//        Optional<Quiz> quiz = quizRepo.findById(id);
//        try {
//            List<Question> questionsFromDB = quiz.get().getQuestions();
//            List<QuestionWrapper> questionForUser = questionsFromDB.stream().map(question -> new QuestionWrapper(question.getId(),
//                    question.getQuestionTitle(),
//                    question.getOption1(),
//                    question.getOption2(),
//                    question.getOption3(),
//                    question.getOption4())).toList();
//            return new ResponseEntity<>(questionForUser, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
        return new ResponseEntity<List<QuestionWrapper>>(questionForUser, HttpStatus.BAD_REQUEST);







    }

    public ResponseEntity<Integer> calculateResult(List<Response> responses) {
        Integer score = quizInterface.getScore(responses).getBody();
        return new ResponseEntity<Integer>(score, HttpStatus.OK);
    }




//        try {
//            Quiz quiz = quizRepo.findById(id).get();
//            List<Question> questions = quiz.getQuestions();
//            int score = 0;
//            for (Response response : responses) {
//                for (Question question : questions) {
//                    if (question.getRightAnswer().equals(response.getResponse())) {
//                        score++;
//                    }
//                }
//            }
//            return new ResponseEntity<Integer>(score, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
}
