package com.spring.QuizApplication.repository;

import com.spring.QuizApplication.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String category);



//    void deleteByDifficultylevel(String difficultylevel);

    @Query(value = "SELECT * FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ" ,nativeQuery = true)
    List<Question> findQuestionsByCategory(int numQ, String category);

//    void deleteByCategory(String category);

   // void deleteAllByCategory(String category);
   //@Query(value = "Delete from Question q where q.category=:category",nativeQuery = true)
    void deleteByCategory(String category);
}
