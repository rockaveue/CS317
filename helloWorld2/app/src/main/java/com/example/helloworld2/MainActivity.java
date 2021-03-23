package com.example.helloworld2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TextView textView = (TextView) findViewById(R.id.textView);
        //textView.setText("B180910062"); //set text for text view
        setContentView(R.layout.activity_main);
    }
}