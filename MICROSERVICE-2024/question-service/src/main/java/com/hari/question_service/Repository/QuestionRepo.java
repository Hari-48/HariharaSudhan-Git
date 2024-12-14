package com.hari.question_service.Repository;


import com.hari.question_service.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id FROM Questions q where q.category = :category order by rand() Limit :numQ",nativeQuery = true)
    List<Integer> findQusByCategory(String category, int numQ);

    @Query(value = "SELECT * FROM Questions q where q.id IN (:questionsIds)",nativeQuery = true)
    List<Question> findAllQuestionsByIds(List<Integer> questionsIds);

//
//    @Query(value = "SELECT * FROM Questions q WHERE q.id IN (:questionsIds)", nativeQuery = true)
//    List<Question> findAllQuestionsByIds(@Param("questionsIds") List<Integer> questionsIds);

}
