package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.config.MessageService;
import ru.otus.spring.dao.QuestionsDao;
import ru.otus.spring.domain.AnswerToQuestion;
import ru.otus.spring.domain.Question;
import ru.otus.spring.tools.IOService;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionsServiceImpl implements QuestionsService {
    private  final QuestionsDao questionsDao;
    private  final IOService ioService;
    private  final MessageService messageService;
    public QuestionsServiceImpl(QuestionsDao questionsDao, IOService ioService, MessageService messageService)  {
        this.ioService = ioService;
        this.questionsDao = questionsDao;
        this.messageService = messageService;
    }

    public List<AnswerToQuestion> askToQuestions() {
        List<AnswerToQuestion> answers = new  ArrayList<>();
        List<Question> questions = questionsDao.getAll();
        for(Question question:questions){
            ioService.printf("%s(%s)", question.getQuestionText(), question.getVariantOfAnswer());
            String answerText = ioService.readLine();
            answers.add(new AnswerToQuestion(   question,   answerText) );
        }
        return answers;
    }
    public void printResult(List<AnswerToQuestion> answers){
        for(AnswerToQuestion answer: answers){
            Question question = answer.getQuestion();
            String resultOfAnswerToQuestion = answer.getAnswerToQuestionText().equals(question.getCorrectAnswerToQuestion())?messageService.getMessage("messages.answer.correct"):messageService.getMessage("messages.answer.wrong");
            String result = String.format("%s - %s (%s)", question.getQuestionText(), resultOfAnswerToQuestion, answer.getAnswerToQuestionText());
            ioService.println(result);
        }
    }




}
