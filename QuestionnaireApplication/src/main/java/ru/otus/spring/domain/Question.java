package ru.otus.spring.domain;

public class Question {
    private final String questionText;
    private final String correctAnswerToQuestion;
    private final String variantOfAnswer;

    public Question(String questionText, String correctAnswerToQuestion, String variantOfAnswer) {
        this.questionText = questionText;
        this.correctAnswerToQuestion = correctAnswerToQuestion;
        this.variantOfAnswer = variantOfAnswer;
    }
    public String getQuestionText() {
        return questionText;
    }
    public String getVariantOfAnswer() {
        return variantOfAnswer;
    }

    public String getCorrectAnswerToQuestion() {
        return correctAnswerToQuestion;
    }

}
