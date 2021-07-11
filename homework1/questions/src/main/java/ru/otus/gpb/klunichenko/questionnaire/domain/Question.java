package ru.otus.gpb.klunichenko.questionnaire.domain;

import ru.otus.gpb.klunichenko.questionnaire.tools.ConsoleQuest;

import java.util.Scanner;

public class Question {
    private String  question;
    private String  rightAnswer;
    private String  answer;
    private ConsoleQuest console;

    public String getListOfAnswer() {
        return listOfAnswer;
    }

    public void setListOfAnswer(String listOfAnswer) {
        this.listOfAnswer = listOfAnswer;
    }

    private String  listOfAnswer;
    private boolean right = false;


    public  Question(String[] fields){
        this.question       = fields[0];
        this.rightAnswer    = fields[1];
        this.listOfAnswer   = fields[2];

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public void askToQuestion(){
        System.out.printf("%s(%s):", question, listOfAnswer);

        Scanner scn = new Scanner(System.in);
        this.answer = scn.nextLine();

        this.right  = this.answer.equals(this.rightAnswer);

        //System.out.println(this.right?"Correct answer":"Wrong answer");
    }

    public  void printResult(){
        System.out.printf( "%s - %s (%s)\r\n" , this.question,   this.right?"Correct answer":"Wrong answer", this.answer);

    }

}
