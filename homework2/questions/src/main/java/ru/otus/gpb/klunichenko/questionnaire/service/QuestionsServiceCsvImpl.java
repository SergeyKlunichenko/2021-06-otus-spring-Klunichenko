package ru.otus.gpb.klunichenko.questionnaire.service;

import org.springframework.stereotype.Service;
import ru.otus.gpb.klunichenko.questionnaire.dao.QuestionsDao;
import ru.otus.gpb.klunichenko.questionnaire.domain.AnswerToQuestion;
import ru.otus.gpb.klunichenko.questionnaire.domain.Question;
import ru.otus.gpb.klunichenko.questionnaire.domain.User;
import ru.otus.gpb.klunichenko.questionnaire.tools.IOService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionsServiceCsvImpl implements QuestionsService {
    final private List<Question> questions; // = new ArrayList<Question>();
    final private IOService ioService;
    //final private User user;
    private List<AnswerToQuestion> answers;
    public QuestionsServiceCsvImpl(QuestionsDao questionsDao, IOService ioService, UserService userService) throws IOException {
        this.ioService = ioService;
        questions = questionsDao.getAll();




    }


    /**************************************
     *
     */
    public void askToQuestions(){
        answers = new ArrayList<AnswerToQuestion>();
        for(Question question:questions){
            ioService.printf("%s(%s)", question.getQuestionText(), question.getVariantOfAnswer());
            String answerText = ioService.readLine();

            answers.add(new AnswerToQuestion(question, answerText ));


        }

    }

    public void printResult(){
        ioService.println("------------------------------------------------------------------");
        ioService.println("Result:");

        for(AnswerToQuestion answer: answers){
            ioService.println(answer.getResult());
        }

    }




}
