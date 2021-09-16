package ru.otus.gpb.klunichenko.questionnaire;

import org.springframework.context.annotation.*;
import ru.otus.gpb.klunichenko.questionnaire.service.QuestionnaireService;
import ru.otus.gpb.klunichenko.questionnaire.tools.IOService;

@PropertySource("classpath:questions.properties")
@ComponentScan
@Configuration
@EnableAspectJAutoProxy

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuestionnaireService service = context.getBean(QuestionnaireService.class);
        service.execute();

    }


}
