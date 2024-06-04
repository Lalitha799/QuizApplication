package com.spring.QuizApplication;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectDemo {

 //Validating input

     private static final Logger LOGGER=LoggerFactory.getLogger(AspectDemo.class);

    @Around("execution(* com.spring.QuizApplication.controller.QuestionController.*(..)) && args( id)")
    public Object beforeAdvice(ProceedingJoinPoint jp, int id) throws Throwable {

        if(id<0)
        {
            id=-id;
        }
        Object obj=jp.proceed(new Object[]{id});
        return obj;

    }

//    @Around("execution(* com.spring.QuizApplication.controller.QuestionController.*(..))")
//    public void beforeAdvice(ProceedingJoinPoint jp) throws Throwable {
//
//        long startTime=System.currentTimeMillis();
//        jp.proceed();
//        long endTime=System.currentTimeMillis();
//        LOGGER.info("Around method"+ (endTime-startTime)+"ms");
//    }

//     @Before("execution(* com.spring.QuizApplication.controller.QuestionController.*(..))")
//    public void beforeAdvice(){
//         LOGGER.info("Before method");
//     }

//    @After("execution(* com.spring.QuizApplication.controller.QuestionController.*(..))")
//    public void beforeAdvice(){
//        LOGGER.info("after  method(When there is no exception)");
//    }

//    @AfterReturning("execution(* com.spring.QuizApplication.controller.QuestionController.*(..))")
//    public void beforeAdvice(){
//        LOGGER.info("after  return");
//    }

//    @AfterThrowing("execution(* com.spring.QuizApplication.controller.QuestionController.*(..))")
//    public void beforeAdvice(){
//        LOGGER.info("after  throwing exception");
//    }




}
