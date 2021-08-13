package ru.otus.spring.events;

import org.springframework.context.ApplicationEvent;

public class QuestionEvent extends ApplicationEvent {

    public QuestionEvent(Object source){
        super(source);
    }

}
