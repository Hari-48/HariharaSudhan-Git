package com.telusko.quizApp.Repository;

import com.telusko.quizApp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {


}
