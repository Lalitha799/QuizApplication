package com.spring.QuizApplication.controller;

import com.spring.QuizApplication.model.QuestionWrapper;
import com.spring.QuizApplication.model.Response;
import com.spring.QuizApplication.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String title, @RequestParam int numQ, @RequestParam String category )
    {
        return quizService.createQuiz(title,numQ,category);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>>  listOfQuestions(@PathVariable Integer id)
    {
        return quizService.listOfQuestions(id);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String>  deleteQuiz(@PathVariable Integer id)
    {
        return quizService.deleteQuiz(id);
    }

    @PostMapping("response/{id}")
    public ResponseEntity<Integer> validateResponses(@PathVariable Integer id,@RequestBody List<Response> response)
    {
       return quizService.validateResponses(id,response);
    }
}
