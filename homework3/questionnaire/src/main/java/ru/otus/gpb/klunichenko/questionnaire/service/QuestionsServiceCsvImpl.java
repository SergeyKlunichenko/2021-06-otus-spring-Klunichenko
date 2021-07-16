package ru.otus.gpb.klunichenko.questionnaire.service;

import org.springframework.stereotype.Service;
import ru.otus.gpb.klunichenko.questionnaire.dao.QuestionsDao;
import ru.otus.gpb.klunichenko.questionnaire.domain.Question;
import ru.otus.gpb.klunichenko.questionnaire.tools.IOService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionsServiceCsvImpl implements QuestionsService {
    final private List<Question> questions = new ArrayList<Question>();
    final private IOService ioService;
    public QuestionsServiceCsvImpl(QuestionsDao questionsDao, IOService ioService) throws IOException {
        this.ioService = ioService;
        questionsDao.getAll();
        loadQuestions(questionsDao.getRows());

    }

    /************************************
     * Загрузить вопросы
     * @param rows
     */
    public void loadQuestions(List<String[]> rows){
        for(String[] fields:rows){
            questions.add(new Question(fields));
        }

    }

    /**************************************
     *
     */
    public void askToQuestions(){
        for(Question question:questions){
            ioService.printf("%s(%s)", question.getQuestion(), question.getVariantOfAnswer());
            String answer = ioService.readLine();
            question.setAnswer(answer);

        }

    }

    public void printResult(){
        ioService.println("------------------------------------------------------------------");
        ioService.println("Result:");
        for(Question question:questions){
            ioService.println(question.getResult());
        }
    }




}
