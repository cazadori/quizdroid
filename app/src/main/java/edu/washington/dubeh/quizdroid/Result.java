package edu.washington.dubeh.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends Activity {

    private int location;
    private String answer;
    private boolean isLastQuestion = false;
    private static final String EXTRA_RESULT = "edu.washington.dubeh.quizdroid.result.location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        Intent intent = getIntent();
        location = intent.getIntExtra(Question.EXTRA_QUESTION_LOCATION, -1);
        answer = intent.getStringExtra(Question.EXTRA_QUESTION_ANSWER);
        Button nextButton = (Button) findViewById(R.id.nextButton);
        TextView correctAnswers = (TextView) findViewById(R.id.correctAnswers);
        if(answer.equals(Overview.quiz.answer(location))) {
            Overview.quiz.answerWasCorrect();
        }
        location++;
        if(location == Overview.quiz.numOfQuestions()) {
            isLastQuestion = true;
            nextButton.setText("Finish");
        }

        correctAnswers.setText("You have " + Overview.quiz.correctAnswers() + " out of "
                + Overview.quiz.numOfQuestions() + " right");

        nextButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(isLastQuestion) {
                    intent = new Intent(Result.this, MainActivity.class);
                } else {
                    intent = new Intent(Result.this, Question.class);
                    intent.putExtra(Overview.EXTRA_OVERVIEW, location);
                }
                startActivity(intent);
            }
        });

    }
}
