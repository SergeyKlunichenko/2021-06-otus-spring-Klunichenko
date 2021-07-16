package ru.otus.gpb.klunichenko.questionnaire.domain;

public class Question {
    private String  question;
    private String correctAnswerToQuestion;
    private String  answer;
    private String variantOfAnswer;
    private boolean right = false;



    public  Question(String[] fields){
        this.question       = fields[0];
        this.correctAnswerToQuestion = fields[1];
        this.variantOfAnswer = fields[2];

    }

    public String getQuestion(){
        return question; //String.format("%s(%s):", question, variantOfAnswer);
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
        this.right  = this.answer.equals(this.correctAnswerToQuestion);

    }



    public String getResult(){
        return String.format("%s - %s (%s)" , this.question,   this.right?"Correct answer":"Wrong answer", this.answer);
    }

    public String getVariantOfAnswer() {
        return variantOfAnswer;
    }

    public void setVariantOfAnswer(String variantOfAnswer) {
        this.variantOfAnswer = variantOfAnswer;
    }

}
