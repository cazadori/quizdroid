package edu.washington.dubeh.quizdroid;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class Overview extends Activity {

    private int current = 0;
    private Button button;
    private QuizQuestion quizQuestion;
    private int questionsCorrect = 0;
    private List<Question> questions;

    public final static String EXTRA_OVERVIEW = "edu.washington.dubeh.quizdroid.overview.count";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview);

        Intent intent = getIntent();
        String selected = intent.getStringExtra(MainActivity.EXTRA_CATEGORY);
        button = (Button) findViewById(R.id.begin);

        QuizApp app = (QuizApp)this.getApplication();
        app.getRepository().createTopic(selected);
        Topic topic = app.getRepository().getTopic();
        questions = topic.getQuestions();

        QuizOverview quizOverview = QuizOverview.newInstance(topic.getLongDesc(), questions.size(), topic.getTitle());
        FragmentTransaction overviewTransaction = getFragmentManager().beginTransaction();
        overviewTransaction.replace(R.id.fragment_placeholder, quizOverview);
        overviewTransaction.commit();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bText = button.getText().toString();
                if(current < questions.size()) {
                    Question currQuestion = questions.get(current);
                    if (bText.equals("Begin") || bText.equals("Next")) {
                        //start question fragment
                        button.setText("Submit");
                        button.setEnabled(false);

                        quizQuestion = QuizQuestion.newInstance(currQuestion.getQuestion(), currQuestion.getAnswers());
                        FragmentTransaction overviewTransaction = getFragmentManager().beginTransaction();
                        overviewTransaction.replace(R.id.fragment_placeholder, quizQuestion);
                        overviewTransaction.commit();

                    } else if (bText.equals("Submit")) {
                        //start answer fragment
                        String answer = quizQuestion.getAnswer();
                        if (answer.equals(currQuestion.getAnswer())) {
                            questionsCorrect++;
                        }

                        Answer answerFragment = Answer.newInstance(questionsCorrect, questions.size(),
                                answer, currQuestion.getAnswer());
                        FragmentTransaction overviewTransaction = getFragmentManager().beginTransaction();
                        overviewTransaction.replace(R.id.fragment_placeholder, answerFragment);
                        overviewTransaction.commit();
                            current++;

                        if (current == questions.size()) {
                            button.setText("Finish");
                        } else {
                            button.setText("Next");
                        }
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
