package com.spring.QuizApplication.service;

import com.spring.QuizApplication.model.Question;
import com.spring.QuizApplication.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        try{

            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestions(Question question) {

        questionRepository.save(question);
        try{
            return new ResponseEntity<>("Success",HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  new ResponseEntity<>("Unsuccess",HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> deleteQuestions(Integer id) {

        questionRepository.deleteById(id);
        try{
            return new ResponseEntity<>("Success",HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  new ResponseEntity<>("Unsuccess",HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {

        List<Question> byCategory = questionRepository.findByCategory(category);
        try{
            return new ResponseEntity<>(byCategory, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuestionByCategory(String category) {

        questionRepository.deleteByCategory(category);

        try{
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  new ResponseEntity<>("Unsuccess",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> updateQuestionsById(Integer id,  Question question) {

        Question byId = questionRepository.findById(id).get();
        byId.setCategory(question.getCategory());
        byId.setDifficultylevel(question.getDifficultylevel());
        questionRepository.save(byId);

        try{
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  new ResponseEntity<>("Not Updated",HttpStatus.BAD_REQUEST);
    }
}
