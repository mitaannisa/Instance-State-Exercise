package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView scoreTv;
    Button buttonCount;
    EditText nameEt;
    int score = 0;
    String savedText = "savedText";
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTv = findViewById(R.id.score);
        buttonCount = findViewById(R.id.button_count);
        scoreTv.setText(String.valueOf(score));

        nameEt = findViewById(R.id.text);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        nameEt.setText(sharedPref.getString(savedText,""));

        buttonCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("HIT BUTTON COUNT");
                score++;
                scoreTv.setText(String.valueOf(score));
            }
        });
        if (savedInstanceState != null){
            score = savedInstanceState.getInt("count");
            scoreTv.setText(String.valueOf(score));
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(savedText, nameEt.getText().toString());
        editor.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(savedText, nameEt.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        nameEt.setText(savedInstanceState.getString(savedText));
    }
}