package edu.washington.dubeh.quizdroid;

/**
 * Created by icazadori on 2/14/2017.
 */

public class Question {
    private String question;
    private String[] answers;
    private int answer;

    public Question(String question, String[] answers, int answer) {
        this.question = question;
        this.answers = answers;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public String getAnswer() {
        return answers[answer];
    }
}
