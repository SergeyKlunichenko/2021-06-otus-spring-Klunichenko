package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import ru.otus.spring.service.QuestionnaireService;

@SpringBootApplication
public class QuestionnaireApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(QuestionnaireApplication.class, args);
        QuestionnaireService service = context.getBean(QuestionnaireService.class);
        service.execute();

        System.exit(0);

    }

}
