package ru.otus.gpb.klunichenko.questionnaire.config.model;

public class QuestionProperty {
    private String question;
    private String answer;
    private String answers;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "QuestionProperty{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", answers='" + answers + '\'' +
                '}';
    }
}

