package edu.washington.dubeh.quizdroid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by icazadori on 2/14/2017.
 */

public class Topic {
    private String title;
    private String shortDesc;
    private String longDesc;
    private List<Question> questions;

    public Topic(String title, String shortDesc, String longDesc) {
        this.title = title;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        this.questions = new ArrayList<Question>();
    }

    public String getTitle() {
        return title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }
}
