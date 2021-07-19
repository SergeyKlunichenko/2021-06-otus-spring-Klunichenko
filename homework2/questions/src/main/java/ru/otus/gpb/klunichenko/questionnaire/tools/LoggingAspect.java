package ru.otus.gpb.klunichenko.questionnaire.tools;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* ru.otus.gpb.klunichenko.questionnaire.dao.*.*(..))")
    public void logBeforeDao(JoinPoint joinPoint){
        System.out.println("Dao call method:"+ joinPoint.getSignature().getName());
    }

    @Before("execution(* ru.otus.gpb.klunichenko.questionnaire.service.QuestionsService.*(..))")
    public void logBeforeService(JoinPoint joinPoint){
        System.out.println("Service call method:"+ joinPoint.getSignature().getName());

    }


}
