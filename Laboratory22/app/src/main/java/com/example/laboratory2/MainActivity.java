package com.example.laboratory2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Display display = getWindowManager().getDefaultDisplay();
        //imageView.getLayoutParams().height ((or width)) = display.getHeight()/2;
        setContentView(R.layout.activity_main);
    }
}