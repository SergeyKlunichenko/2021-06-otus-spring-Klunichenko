package ru.otus.spring.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public class CustomEventPublisher {
    private final ApplicationEventPublisher publisher;

    @Autowired
    public CustomEventPublisher(ApplicationEventPublisher publisher){
        this.publisher = publisher;
    }

    public void  doPublishEvent(final String message){
        QuestionEvent event =  new QuestionEvent(this);
        publisher.publishEvent(event);
    }

}
