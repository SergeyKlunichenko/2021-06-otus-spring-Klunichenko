package ru.otus.gpb.klunichenko.questionnaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.gpb.klunichenko.questionnaire.service.QuestionnaireService;

import java.io.IOException;

@SpringBootApplication
public class QuestionnaireApplication {


	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(QuestionnaireApplication.class, args);
		QuestionnaireService service = context.getBean(QuestionnaireService.class);
		MessageSource msg = context.getBean(MessageSource.class);

		try {
			service.execute();
		} catch (IOException e){
			e.printStackTrace(System.out);

		}
	}

}
