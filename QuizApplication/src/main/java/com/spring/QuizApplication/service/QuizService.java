package com.spring.QuizApplication.service;

import com.spring.QuizApplication.model.Question;
import com.spring.QuizApplication.model.QuestionWrapper;
import com.spring.QuizApplication.model.Quiz;
import com.spring.QuizApplication.model.Response;
import com.spring.QuizApplication.repository.QuestionRepository;
import com.spring.QuizApplication.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String title, int numQ, String category) {

        Quiz quiz = new Quiz();
        List<Question> questionsByCategory = questionRepository.findQuestionsByCategory(numQ, category);
        quiz.setTitle(title);
        quiz.setQuestions(questionsByCategory);
        quizRepository.save(quiz);
        try {
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Unsuccess", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> listOfQuestions(Integer id) {

        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        List<QuestionWrapper> questionWrappers= new ArrayList<>();
        for (Question q:questions)
        {
            QuestionWrapper wrapper =new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),
                    q.getOption2(),q.getOption3(),q.getOption4());
            questionWrappers.add(wrapper);
        }

        try {
            return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> validateResponses(Integer id, List<Response> response) {

        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestions();
        int score=0;
        for(Response r:response)
        {
            for (Question q:questions){

                if(r.getId()==q.getId())
                {
                    if(r.getRightAnswer().equalsIgnoreCase(q.getRightAnswer()))
                    { score+=1;
                        break;}
                }
            }
        }
        try {
            return new ResponseEntity<>(score, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteQuiz(Integer id) {

        quizRepository.deleteById(id);
        try {
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Unsuccess", HttpStatus.BAD_REQUEST);
    }
}