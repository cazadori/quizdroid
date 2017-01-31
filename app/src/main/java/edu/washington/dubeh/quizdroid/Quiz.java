package edu.washington.dubeh.quizdroid;

import android.os.Parcelable;

public class Quiz {
    private String[] questions;
    private int numOfQuestions;
    private String[][] possibleAnswers;
    private int[] answers;
    private int correctAnswers;

    public Quiz(String[] questions, String[][] possibleAnswers, int[] answers) {
        this.questions = questions;
        this.numOfQuestions = questions.length;
        this.possibleAnswers = possibleAnswers;
        this.answers = answers;
        correctAnswers = 0;
    }

    public String question(int location) {
        return questions[location];
    }

    public int numOfQuestions() {
        return numOfQuestions;
    }

    public int correctAnswers() {
        return correctAnswers;
    }

    public String[] possibleAnswers(int location) {
        return possibleAnswers[location];
    }

    public String answer(int location) {
        return possibleAnswers[location][answers[location]];
    }

    public void answerWasCorrect() {
        correctAnswers++;
    }
}
