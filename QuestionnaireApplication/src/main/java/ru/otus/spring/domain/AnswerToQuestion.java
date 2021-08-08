package ru.otus.spring.domain;

public class AnswerToQuestion {
    private final   Question question;
    private final    String answerToQuestionText;

    public AnswerToQuestion(Question question, String answerToQuestionText){
        this.question               =   question;
        this.answerToQuestionText   =   answerToQuestionText;
    }
    public Question getQuestion() {
        return question;
    }

    public String getAnswerToQuestionText() {
        return answerToQuestionText;
    }


}
