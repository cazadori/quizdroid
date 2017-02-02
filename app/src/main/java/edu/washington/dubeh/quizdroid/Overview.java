package edu.washington.dubeh.quizdroid;

import android.app.Activity;
import android.app.FragmentTransaction;
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
    private int current = 0;
    private Button button;
    private QuizQuestion quizQuestion;
    private int questionsCorrect = 0;

    public final static String EXTRA_OVERVIEW = "edu.washington.dubeh.quizdroid.overview.count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview);

        Intent intent = getIntent();
        String selected = intent.getStringExtra(MainActivity.EXTRA_CATEGORY);
        button = (Button) findViewById(R.id.begin);

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

        QuizOverview quizOverview = QuizOverview.newInstance(description, questions.length);
        FragmentTransaction overviewTransaction = getFragmentManager().beginTransaction();
        overviewTransaction.replace(R.id.fragment_placeholder, quizOverview);
        overviewTransaction.commit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bText = button.getText().toString();
                if(bText.equals("Begin") || bText.equals("Next")) {
                    //start question fragment
                    button.setText("Submit");
                    button.setEnabled(false);

                    quizQuestion = QuizQuestion.newInstance(questions[current], possibleAnswers[current]);
                    FragmentTransaction overviewTransaction = getFragmentManager().beginTransaction();
                    overviewTransaction.replace(R.id.fragment_placeholder, quizQuestion);
                    overviewTransaction.commit();

                } else if(bText.equals("Submit")) {
                    //start answer fragment
                    String answer = quizQuestion.getAnswer();
                    if(answer.equals(possibleAnswers[current][answers[current]])) {
                        questionsCorrect++;
                    }

                    Answer answerFragment = Answer.newInstance(questionsCorrect, questions.length,
                            answer, possibleAnswers[current][answers[current]]);
                    FragmentTransaction overviewTransaction = getFragmentManager().beginTransaction();
                    overviewTransaction.replace(R.id.fragment_placeholder, answerFragment);
                    overviewTransaction.commit();
                    current++;
                    
                    if(current == questions.length) {
                        button.setText("Finish");
                    } else {
                        button.setText("Next");
                    }
                } else {
                    //go to main activity
                    //bText should equal Finish
                    Intent intent = new Intent(Overview.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
