package com.example.lab6thread;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainProcessing();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Initialize a handler on the main thread.
    private Handler handler = new Handler();
    private void mainProcessing() {
        Thread thread = new Thread(null, doBackgroundThreadProcessing, "Background");
        thread.start();
    }
    private Runnable doBackgroundThreadProcessing = new Runnable() {
        public void run() {
            backgroundThreadProcessing();
        }
    };
    // Method which does some processing in the background.
    private void backgroundThreadProcessing() {
        //[ ... Time consuming operations ... ]
        handler.post(doUpdateGUI);
    }
    // Runnable that executes the update GUI method.
    private Runnable doUpdateGUI = new Runnable() {
        public void run() {
            updateGUI();
        }
    };
    private void updateGUI() {
        //[ ... Open a dialog or modify a GUI element ... ]
        Context context = getApplicationContext();
        String msg = "To open mobile development!";
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, msg, duration).show();
    }

}