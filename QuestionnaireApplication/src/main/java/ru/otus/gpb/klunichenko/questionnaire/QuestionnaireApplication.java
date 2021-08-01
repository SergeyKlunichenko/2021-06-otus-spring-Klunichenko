package ru.otus.gpb.klunichenko.questionnaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import ru.otus.gpb.klunichenko.questionnaire.config.GlobalProperties;
import ru.otus.gpb.klunichenko.questionnaire.config.QuestionsProperties;
import ru.otus.gpb.klunichenko.questionnaire.service.QuestionnaireService;

@SpringBootApplication
//public class QuestionnaireApplication implements CommandLineRunner {
	public class QuestionnaireApplication {
	@Autowired
	private GlobalProperties globalProperties;

	@Autowired
	private QuestionsProperties questionsProperties;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(QuestionnaireApplication.class, args);
		MessageSource msg = context.getBean(MessageSource.class);
		QuestionnaireService service = context.getBean(QuestionnaireService.class);

		service.execute();


	}
/*
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Test");
		System.out.println(globalProperties);
		System.out.println(questionsProperties);

	}
 */
}
