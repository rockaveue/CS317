package com.example.lab5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    BroadcastReceiver receiver;
    IntentFilter filter;
    Button sendbroadcast;
    EditText ed3;
    Button b1;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Email = "textKey";

    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        receiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context,"Broadcast Received in MainActivity",Toast.LENGTH_SHORT).show();
            }
        };
        // to register local receiver
        filter = new IntentFilter();
        // specify the action to which receiver will listen
        filter.addAction("com.local.receiver");
        registerReceiver(receiver,filter);
        sendbroadcast=(Button)findViewById(R.id.sendBroadcast);
        sendbroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent("com.local.receiver");
                sendBroadcast(intent);
            }
        });

        ed3=(EditText)findViewById(R.id.editText3);
        b1=(Button)findViewById(R.id.button);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e  = ed3.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Email, e);
                editor.commit();
                Toast.makeText(MainActivity.this,"Мэдээлэл орлоо",Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(receiver!=null)
        {
            unregisterReceiver(receiver);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}