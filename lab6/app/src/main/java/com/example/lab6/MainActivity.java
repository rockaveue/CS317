package com.example.lab6;

import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class MainActivity extends Activity {

    /*private Context context = this;
    private MyService serviceBinder;
    private ServiceConnection mConnection = new ServiceConnection(){
        public void onServiceConnected(ComponentName className, IBinder service){
            serviceBinder = ((MyService.MyBinder)service).getService();
        }
        public void onServiceDisconnected(ComponentName className){
            serviceBinder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // use this to start and trigger a service
                //Intent i= new Intent(context, MyService.class);
                // potentially add data to the intent
                //i.putExtra("KEY1", "Value to be used by the service");
                //context.startService(i);

                Intent bindIntent = new Intent(MainActivity.this, MyService.class);
                bindService(bindIntent, mConnection, Context.BIND_AUTO_CREATE);




            	Context context = getApplicationContext();
            	String msg = "Cheers!";
            	int duration = Toast.LENGTH_LONG;
            	Toast toast = Toast.makeText(context, msg, duration);
            	toast.setGravity(Gravity.BOTTOM, 0, 0);
            	LinearLayout ll = new LinearLayout(context);
            	ll.setOrientation(LinearLayout.VERTICAL);
            	TextView myTextView = new TextView(context);
            	TextView myTextView1 = new TextView(context);
            	myTextView.setText(msg);
            	myTextView1.setText("Toast New Line");
            	int lHeight = LinearLayout.LayoutParams.FILL_PARENT;
            	int lWidth = LinearLayout.LayoutParams.WRAP_CONTENT;
            	ll.addView(myTextView, new LinearLayout.LayoutParams(lHeight, lWidth));
            	ll.addView(myTextView1, new LinearLayout.LayoutParams(lHeight, lWidth));
            	ll.setPadding(40, 50, 0, 50);
            	toast.setView(ll);
            	toast.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/
    private static final String TAG = "MainActivity";
    private EditText editTextInput;
    private Button buttonStartThread, buttonStopThread;
    private Handler mainHandler = new Handler();
    private volatile boolean stopThread = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextInput = findViewById(R.id.edit_text_input);
        buttonStartThread = findViewById(R.id.startT);
        buttonStopThread = findViewById(R.id.stopT);
    }
    public void startService(View v) {
        String input = editTextInput.getText().toString();
        Intent serviceIntent = new Intent(this, MyService.class);
        serviceIntent.putExtra("inputExtra", input);
        ContextCompat.startForegroundService(this, serviceIntent);
//        startService(serviceIntent);
    }
    public void stopService(View v) {
        Intent serviceIntent = new Intent(this, MyService.class);
        stopService(serviceIntent);
    }
    public void startThread(View v){
        stopThread = false;
        ExampleThread runnable = new ExampleThread(10);
        /*new Thread(new Runnable(){
            @Override
            public void run(){
                //work
            }
        }).start();*/
        new Thread(runnable).start();
    }
    public void stopThread(View v){
        buttonStartThread.setText("Start Thread!");
        stopThread = true;
    }
    class ExampleThread extends Thread{
        int seconds;
        ExampleThread(int seconds){
            this.seconds = seconds;
        }
        @Override
        public void run(){
            for(int i = 0; i < seconds; i++){
                if(stopThread){
                    return;
                }
                /*if(i == 8){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            buttonStartThread.setText("duuslaa");
                        }
                    });
                }*/
                Log.d(TAG,"Thread : " + (i+1));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "10n second ungurluu", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}