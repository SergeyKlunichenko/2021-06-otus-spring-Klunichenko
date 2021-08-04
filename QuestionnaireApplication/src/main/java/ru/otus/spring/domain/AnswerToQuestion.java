package ru.otus.spring.domain;

import ru.otus.spring.config.MessageService;

public class AnswerToQuestion {
    private final   Question question;
    private final    String answerToQuestionText;
    private final  MessageService messageService;

    public AnswerToQuestion(Question question, String answerToQuestionText, MessageService messageService){
        this.question               =   question;
        this.answerToQuestionText   =   answerToQuestionText;
        this.messageService         =   messageService;
    }

    public String getResult(){
        Question question = this.question;
        String resultOfAnswerToQuestion = answerToQuestionText.equals(question.getCorrectAnswerToQuestion())?messageService.getMessage("messages.answer.correct", null):messageService.getMessage("messages.answer.wrong", null);
        String result = String.format("%s - %s (%s)", question.getQuestionText(), resultOfAnswerToQuestion, this.answerToQuestionText);
        return  result;
    }


}
