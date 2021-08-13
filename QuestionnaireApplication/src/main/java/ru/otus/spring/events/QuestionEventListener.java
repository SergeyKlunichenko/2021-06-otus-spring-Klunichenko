package ru.otus.spring.events;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.otus.spring.service.QuestionnaireService;

@Component
public class QuestionEventListener {
    @EventListener
    public void onApplicationEvent(QuestionEvent event){
        System.out.println("onApplicationEvent");
        QuestionnaireService service = (QuestionnaireService)event.getSource();
        service.execute();
    }
}
