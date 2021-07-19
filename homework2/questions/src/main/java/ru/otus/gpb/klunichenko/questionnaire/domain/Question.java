package ru.otus.gpb.klunichenko.questionnaire.domain;

public class Question {
            private String questionText;
            private String correctAnswerToQuestion;
            private String  answer;
            private String variantOfAnswer;
            private boolean right = false;



    public  Question(String[] fields){
        this.questionText = fields[0];
        this.correctAnswerToQuestion = fields[1];
        this.variantOfAnswer = fields[2];

    }

    public String getQuestionText(){
        return questionText;
    }


    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectAnswerToQuestion() {
        return correctAnswerToQuestion;
    }

    public void setCorrectAnswerToQuestion(String correctAnswerToQuestion) {
        this.correctAnswerToQuestion = correctAnswerToQuestion;
    }

    public String getVariantOfAnswer() {
        return variantOfAnswer;
    }

    public void setVariantOfAnswer(String variantOfAnswer) {
        this.variantOfAnswer = variantOfAnswer;
    }

}
