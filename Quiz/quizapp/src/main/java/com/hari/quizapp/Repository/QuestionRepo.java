package com.hari.quizapp.Repository;

import com.hari.quizapp.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Integer> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM Questions q where q.category = :category order by rand() Limit :numQ",nativeQuery = true)
    List<Question> findQusByCategory(String category, int numQ);


}
