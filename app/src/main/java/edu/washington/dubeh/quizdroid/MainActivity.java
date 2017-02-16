package edu.washington.dubeh.quizdroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    public final static String EXTRA_CATEGORY = "edu.washington.dubeh.quizdroid.CATEGORY";
    private String[] categories;
    private String[] shortDescs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        QuizApp app = (QuizApp)this.getApplication();
        categories = app.getRepository().getCategories();
        shortDescs = app.getRepository().getShortDescs();

        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                categories);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
                String selected = adapter.getItemAtPosition(position).toString().trim();

                Intent intent = new Intent(MainActivity.this, Overview.class);
                intent.putExtra(EXTRA_CATEGORY, selected);
                startActivity(intent);
            }
        });
    }
}
