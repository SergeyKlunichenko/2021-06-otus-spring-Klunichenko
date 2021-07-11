package ru.otus.gpb.klunichenko.questionnaire;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.gpb.klunichenko.questionnaire.service.QuestionnaireService;

@PropertySource("classpath:questions.properties")
@ComponentScan
@Configuration
public class Main {

    public static void main(String[] args) {



        // TODO: создайте здесь класс контекста
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        QuestionnaireService service = context.getBean(QuestionnaireService.class);


        try {
            service.execute();
        } catch(Exception e) {
            e.printStackTrace(System.out);
        }


    }


}
