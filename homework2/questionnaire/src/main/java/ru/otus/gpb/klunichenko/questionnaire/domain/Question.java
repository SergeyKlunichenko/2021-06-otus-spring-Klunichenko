package ru.otus.gpb.klunichenko.questionnaire.domain;

import ru.otus.gpb.klunichenko.questionnaire.tools.ConsoleQuest;

import java.util.Scanner;

public class Question {
    private String  question;
    private String correctAnswerToQuestion;
    private String  answer;
    private String variantOfAnswer;
    private ConsoleQuest console;
    private boolean right = false;


    public  Question(String[] fields,  ConsoleQuest console){
        this.question       = fields[0];
        this.correctAnswerToQuestion = fields[1];
        this.variantOfAnswer = fields[2];
        this.console        =  console;

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswerToQuestion() {
        return correctAnswerToQuestion;
    }

    public void setCorrectAnswerToQuestion(String correctAnswerToQuestion) {
        this.correctAnswerToQuestion = correctAnswerToQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getVariantOfAnswer() {
        return variantOfAnswer;
    }

    public void setVariantOfAnswer(String variantOfAnswer) {
        this.variantOfAnswer = variantOfAnswer;
    }

    public void askToQuestion(){
        console.printf("%s(%s):", question, variantOfAnswer);

        Scanner scn = new Scanner(System.in);
        this.answer = scn.nextLine();

        this.right  = this.answer.equals(this.correctAnswerToQuestion);
    }

    public  void printResult(){

        console.printf( "%s - %s (%s)\r\n" , this.question,   this.right?"Correct answer":"Wrong answer", this.answer);

    }

}
