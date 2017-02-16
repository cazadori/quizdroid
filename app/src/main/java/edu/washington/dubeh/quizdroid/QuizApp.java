package edu.washington.dubeh.quizdroid;

import android.app.Application;
import android.util.Log;


/**
 * Created by icazadori on 2/13/2017.
 */

public class QuizApp extends Application {
    private TopicRepository instance = new TopicRepository();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("QuizApp", "onCreate fired");
    }

    public TopicRepository getRepository() {
        return instance;
    }
}
