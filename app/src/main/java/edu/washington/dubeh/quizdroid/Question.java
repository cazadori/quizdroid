package edu.washington.dubeh.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Question extends Activity {

    private Button submit;
    private String answer = "";
    private int location;
    public static final String EXTRA_QUESTION_LOCATION = "edu.washington.dubeh.quizdroid.question.location";
    public static final String EXTRA_QUESTION_ANSWER = "edu.washington.dubeh.quizdroid.question.answer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);

        Intent intent = getIntent();
        location = intent.getIntExtra(Overview.EXTRA_OVERVIEW, -1);
        submit = (Button) findViewById(R.id.submit);
        submit.setEnabled(false);
        TextView questionText = (TextView) findViewById(R.id.questionText);
        questionText.setText(Overview.quiz.question(location));
        String[] possibleAnswers = Overview.quiz.possibleAnswers(location);
        RadioButton answer1 = (RadioButton) findViewById(R.id.answer1);
        answer1.setText(possibleAnswers[0]);
        RadioButton answer2 = (RadioButton) findViewById(R.id.answer2);
        answer2.setText(possibleAnswers[1]);
        RadioButton answer3 = (RadioButton) findViewById(R.id.answer3);
        answer3.setText(possibleAnswers[2]);
        RadioButton answer4 = (RadioButton) findViewById(R.id.answer4);
        answer4.setText(possibleAnswers[3]);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioAnswers);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int id) {
                if(id != -1) {
                    RadioButton checkedAnswer = (RadioButton) group.findViewById(id);
                    submit.setEnabled(true);
                    submit.setBackgroundColor(0xFF00FF00);
                    answer = Overview.quiz.possibleAnswers(location)[group.indexOfChild(checkedAnswer)];
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Question.this, Result.class);
                intent.putExtra(EXTRA_QUESTION_LOCATION, location);
                intent.putExtra(EXTRA_QUESTION_ANSWER, answer);
                startActivity(intent);
            }
        });

    }
}
