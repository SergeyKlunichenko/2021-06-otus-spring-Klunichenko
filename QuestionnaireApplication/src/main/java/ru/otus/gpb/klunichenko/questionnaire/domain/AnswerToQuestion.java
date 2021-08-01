package ru.otus.gpb.klunichenko.questionnaire.domain;

public class AnswerToQuestion {
    private final   Question question;
    private final    String answerToQuestionText;

    public AnswerToQuestion(Question question, String answerToQuestionText){
        this.question               = question;
        this.answerToQuestionText   = answerToQuestionText;
    }

    public String getResult(){
        Question question = this.question;
        String resultOfAnswerToQuestion = answerToQuestionText.equals(question.getCorrectAnswerToQuestion())?"Correct":"Wrong";
        String result = String.format("%s - %s (%s)", question.getQuestionText(), resultOfAnswerToQuestion, this.answerToQuestionText);
        return  result;
    }


}
