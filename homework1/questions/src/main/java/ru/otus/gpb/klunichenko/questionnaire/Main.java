package ru.otus.gpb.klunichenko.questionnaire;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.gpb.klunichenko.questionnaire.service.QuestionnaireService;

public class Main {


    public static void main(String[] args) {
        System.out.println("Please answer a few questions");

        // TODO: создайте здесь класс контекста
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");

        // Получить сервис опроса
        QuestionnaireService service = context.getBean(QuestionnaireService.class);

        try {

            service.execute();
            System.out.println("Thank you for the answers");
        } catch(Exception e) {
            e.printStackTrace(System.out);
        }


    }


}
