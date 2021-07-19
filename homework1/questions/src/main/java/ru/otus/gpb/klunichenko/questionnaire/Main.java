package ru.otus.gpb.klunichenko.questionnaire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.gpb.klunichenko.questionnaire.service.QuestionnaireService;

import java.io.IOException;

@ComponentScan
@Configuration
@PropertySource("classpath:config.properties")
public class Main {
    public static void main(String[] args) {
        System.out.println("Please answer a few questions");

        // TODO: создайте здесь класс контекста
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        // Получить сервис опроса
        QuestionnaireService service = context.getBean(QuestionnaireService.class);

        try {
            service.execute();
        } catch(IOException e) {
            e.printStackTrace(System.out);
        }


    }


}
