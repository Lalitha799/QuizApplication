package com.spring.QuizApplication.controller;

import com.spring.QuizApplication.model.Question;
import com.spring.QuizApplication.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }

    @PostMapping("addQuestions")
    public ResponseEntity<String> addQuestions(@RequestBody Question question)
    {
        return questionService.addQuestions(question);
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestions(@PathVariable Integer id)
    {
        return questionService.deleteQuestions(id);
    }

    @GetMapping("getBycategory")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@RequestParam String category)
    {
        return  questionService.getQuestionsByCategory(category);
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> deleteQuestionsByCategory(@RequestParam String category)
    {
        return questionService.deleteQuestionByCategory(category);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateQuestionsById(@PathVariable Integer id,@RequestBody Question question)
    {


       return questionService.updateQuestionsById(id,question);
    }
}
