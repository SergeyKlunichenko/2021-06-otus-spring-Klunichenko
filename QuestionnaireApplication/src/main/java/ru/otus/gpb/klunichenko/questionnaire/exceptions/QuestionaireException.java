package ru.otus.gpb.klunichenko.questionnaire.exceptions;


public class QuestionaireException extends Error{
    public QuestionaireException(Throwable cause){
        super(cause);
    }

    public QuestionaireException(String message){
        super(message);
    }
}
