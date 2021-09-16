package ru.otus.gpb.klunichenko.questionnaire.tools;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Around(value="@annotation(ru.otus.gpb.klunichenko.questionnaire.tools.Logger)")
    public Object around(ProceedingJoinPoint joinPoint) throws  Throwable{
        Object obj = null;
        String nameMethod = joinPoint.getSignature().getDeclaringType().getSimpleName()+"."+joinPoint.getSignature().getName();
        System.out.println("логирование перед вызовом:"+ nameMethod);
        System.out.println("Аргументы для "+nameMethod+":");

        System.out.println(Arrays.toString(joinPoint.getArgs()));


        try {
            obj = joinPoint.proceed();
        } catch (Exception e){
            e.printStackTrace(System.out);
        }
        System.out.println("логирование после вызова:"+joinPoint.getSignature().getDeclaringType().getSimpleName()+"."+joinPoint.getSignature().getName());

        return obj;
    }
}
