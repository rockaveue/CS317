package com.example.lab4;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Src2 extends Activity {
    private static final int pic_id = 123;
    private static final int PICK_IMAGE = 100;
    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_src2);


        //click_image_id = (ImageView)findViewById(R.id.click_image);
        Button button = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button9 = (Button) findViewById(R.id.button9);
        text = (EditText) findViewById(R.id.imageView1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Uri data = Uri.parse("content://horses/" + 1);
                Intent result = new Intent(null, data);
                result.putExtra("correct", 9);
                setResult(RESULT_OK, result);
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                // Start the activity with camera_intent,
                // and request pic id
                startActivityForResult(camera_intent, pic_id);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_MAIN);
                i.addCategory(Intent.CATEGORY_HOME);
                startActivity(i);
            }
        });
        button9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                EditText editText = (EditText) findViewById(R.id.imageView1);
                String stringToPassBack = editText.getText().toString();

                // Put the String to pass back into an Intent and close this activity
                Intent intent = new Intent();
                intent.putExtra("keyName", stringToPassBack);
                setResult(Activity.RESULT_OK, intent);
                finish();


            }
        });
    }

    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                String returnString = data.getStringExtra("keyName");

                // Set text view with string
                TextView textView = (TextView) findViewById(R.id.text);
                textView.setText(returnString);
            }
        }

    }
}
