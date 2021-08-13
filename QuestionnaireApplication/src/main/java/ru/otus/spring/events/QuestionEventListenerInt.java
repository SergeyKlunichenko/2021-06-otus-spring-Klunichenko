package ru.otus.spring.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ru.otus.spring.service.QuestionnaireService;

@Component
public class QuestionEventListenerInt implements ApplicationListener <QuestionEvent> {

    @Override
    public void onApplicationEvent(QuestionEvent questionEvent) {
        System.out.println("QuestionEventListenerInt");
        QuestionnaireService service = (QuestionnaireService)questionEvent.getSource();
        service.execute();
    }
}
