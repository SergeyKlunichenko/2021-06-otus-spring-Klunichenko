package ru.otus.gpb.klunichenko.questionnaire.service;

import org.springframework.stereotype.Service;
import ru.otus.gpb.klunichenko.questionnaire.dao.QuestionsDao;
import ru.otus.gpb.klunichenko.questionnaire.domain.AnswerToQuestion;
import ru.otus.gpb.klunichenko.questionnaire.domain.Question;
import ru.otus.gpb.klunichenko.questionnaire.tools.IOService;
import ru.otus.gpb.klunichenko.questionnaire.tools.Logged;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionsServiceImpl implements QuestionsService {
    private  final QuestionsDao questionsDao;
    private  final IOService ioService;
    public QuestionsServiceImpl(QuestionsDao questionsDao, IOService ioService)  {
        this.ioService = ioService;
        this.questionsDao = questionsDao;
    }

    @Logged
    public List<AnswerToQuestion> askToQuestions() {
        List<AnswerToQuestion> answers = new  ArrayList<>();
        List<Question> questions = questionsDao.getAll();
        for(Question question:questions){
            ioService.printf("%s(%s)", question.getQuestionText(), question.getVariantOfAnswer());
            String answerText = ioService.readLine();
            answers.add(new AnswerToQuestion(question, answerText ));
        }
        return answers;
    }
    @Logged
    public void printResult(List<AnswerToQuestion> answers){
        for(AnswerToQuestion answer: answers){
            ioService.println(answer.getResult());
        }
    }




}
