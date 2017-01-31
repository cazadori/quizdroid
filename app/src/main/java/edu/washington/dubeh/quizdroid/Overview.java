package edu.washington.dubeh.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Overview extends Activity {

    private String[] questions;
    private String[][] possibleAnswers;
    private int[] answers;
    private String description;
    public final static String EXTRA_OVERVIEW = "edu.washington.dubeh.quizdroid.overview.count";
    public static Quiz quiz;
    private int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview);

        Intent intent = getIntent();
        String selected = intent.getStringExtra(MainActivity.EXTRA_CATEGORY);
        Button begin = (Button) findViewById(R.id.begin);
        TextView summary = (TextView) findViewById(R.id.description);
        TextView numOfQs = (TextView) findViewById(R.id.questions);

        if(selected.equals("Math")) {
            questions = new String[]{"Math Question 1", "Math Question 2",
                                    "Math Question 3", "Math Question 4"};
            description = "These are math questions";
        } else if(selected.equals("Physics")) {
            questions = new String[]{"Physics Question 1", "Physics Question 2",
                                    "Physics Question 3", "Physics Question 4"};
            description = "These are physics questions";
        } else {
            questions = new String[]{"Marvel Question 1", "Marvel Question 2",
                                    "Marvel Question 3", "Marvel Question 4"};
            description = "These are Marvel questions";
        }
        possibleAnswers = new String[][]{{"answer 1", "answer 2", "answer 3", "answer 4"},
                {"answer 1", "answer 2", "answer 3", "answer 4"},
                {"answer 1", "answer 2", "answer 3", "answer 4"},
                {"answer 1", "answer 2", "answer 3", "answer 4"}};
        answers = new int[]{0, 1, 3, 2};

        summary.setText(description);
        numOfQs.setText("There are " + questions.length + " questions");
        quiz = new Quiz(questions, possibleAnswers, answers);

        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Overview.this, Question.class);
                intent.putExtra(EXTRA_OVERVIEW, 0);
                startActivity(intent);
            }
        });
    }
}
