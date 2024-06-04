package com.spring.QuizApplication.repository;

import com.spring.QuizApplication.model.Question;
import com.spring.QuizApplication.model.Quiz;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.hibernate.query.NativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Integer> {




}
