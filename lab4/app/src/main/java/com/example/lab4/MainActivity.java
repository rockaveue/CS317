package com.example.lab4;
//import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.loader.content.CursorLoader;

import com.example.lab4.BuildConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity {
    public src3 srr = new src3();
    Handler hdlr = new Handler();
    private static final int PICK_IMAGE=100;
    private String selectedImagePath;
    ImageView im;
    Uri selectedImage;
    MediaPlayer med1;
    SeekBar seekbar;
    int eTime = 0, oTime = 0;
    TextView songTime;
    TextView startTime;
    MediaPlayer mp;
    TextView text;

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
    private Runnable UpdateSongTime = new Runnable() {
        @Override
        public void run() {
            oTime = mp.getCurrentPosition();
            startTime.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes(oTime),
                    TimeUnit.MILLISECONDS.toSeconds(oTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(oTime))) );
            seekbar.setProgress(oTime);
            hdlr.postDelayed(this, 1000);
        }
    };
    private void play(Context context, Uri uri) {

        try {
            String pathFromUri = getRealPathFromURI(this, uri);
            seekbar.setVisibility(View.VISIBLE);
            startTime.setVisibility(View.VISIBLE);
            songTime.setVisibility(View.VISIBLE);
            mp = new MediaPlayer();
            mp.setDataSource(context, Uri.parse(pathFromUri));
            mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.prepareAsync();
                    mp.start();
                }
            });
            //mp.start();
            eTime = mp.getDuration();
            oTime = mp.getCurrentPosition();
            if(oTime == 0){
                seekbar.setMax(eTime);
                oTime =1;
            }
            songTime.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes(eTime),
                    TimeUnit.MILLISECONDS.toSeconds(eTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS. toMinutes(eTime))) );
            startTime.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes(oTime),
                    TimeUnit.MILLISECONDS.toSeconds(oTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS. toMinutes(oTime))) );
            seekbar.setProgress(oTime);
            hdlr.postDelayed(UpdateSongTime, 1000);
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String getRealPathFromURI(Context context, Uri contentUri) {
        String[] projection = { MediaStore.Audio.Media.DATA };
        CursorLoader loader = new CursorLoader(context, contentUri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        im = (ImageView) findViewById(R.id.imageView1);
        //med1 = (MediaPlayer) findViewById(R.id.)
        Button button = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        //text = (TextView) findViewById(R.id.imageView3);
        button8.setVisibility(View.GONE);
        Button button9 = (Button) findViewById(R.id.button9);
        seekbar = (SeekBar) findViewById(R.id.sBar);
        seekbar.setClickable(false);
        seekbar.setVisibility(View.GONE);
        songTime = (TextView) findViewById(R.id.txtSongTime);
        startTime = (TextView) findViewById(R.id.txtStartTime) ;
        songTime.setVisibility(View.GONE);
        startTime.setVisibility((View.GONE));
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                    Intent intent = new Intent(MainActivity.this, Src2.class);
                startActivityForResult(intent, 1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent=new Intent(Intent.ACTION_DIAL, Uri.parse("tel:211"));
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent=new Intent(Intent.ACTION_EDIT, Uri.parse("content://contacts/people/1"));
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/1"));
                startActivity(intent);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent intent = new Intent(Intent.ACTION_SET_WALLPAPER);
                startActivity(Intent.createChooser(intent, "Select Wallpaper"));
                startActivity(intent);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Нэг хоёр гурав");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //Intent pickAudioIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                //startActivity(pickAudioIntent);
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 8);

            }
        });
        button9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //openGallery();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                //Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                //startActivityForResult(gallery, PICK_IMAGE);

            }
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            /*if (requestCode == 1 && resultCode == RESULT_OK) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
            }*/
            if(requestCode == 1 && resultCode == RESULT_OK){
                String returnString = data.getStringExtra("keyName");

                // Set text view with string
                TextView textView = (TextView) findViewById(R.id.imageView3);
                textView.setText(returnString);
            }
        if(resultCode == RESULT_OK && requestCode == 10){
            Uri uriSound=data.getData();
            play(this, uriSound);
        }
        if(requestCode == PICK_IMAGE && resultCode==RESULT_OK){
                selectedImage = data.getData();
                im.setImageURI(selectedImage);
        }
        if (resultCode == RESULT_OK) {
            if (requestCode == 8){
                Uri selectedMusicUri = data.getData();
                if (selectedMusicUri != null){
                    //String pathFromUri = getRealPathFromURI(this, selectedMusicUri);
                    play(this, selectedMusicUri);
                }
            }
        }
        if(requestCode==2){

            if (resultCode == Activity.RESULT_OK) {
                Uri selectedMusic = data.getData();

                String[] filePathColumn= {MediaStore.Audio.Media.DATA};

                Cursor cursor=getContentResolver().query(selectedMusic,
                        filePathColumn,null,null,null);
                cursor.moveToFirst();

                int columnIndex=cursor.getColumnIndex(filePathColumn[0]);
                String musicPath=cursor.getString(columnIndex);
                cursor.close();

                //appelProcedureWL_String(GalleryProcedureRetour,musicPath);
            }

        }

        if(requestCode == 3){

            if(resultCode == RESULT_OK){
                Uri uriSound=data.getData();
                srr.setMp(this, uriSound);
                Intent intent = new Intent(MainActivity.this, src3.class);
                startActivityForResult(intent, 1);
            }

        }
        int inputCorrect = data.getIntExtra("correct", 5);

        Toast.makeText(getApplicationContext(), "val: "+inputCorrect, Toast.LENGTH_SHORT).show();
        //System.out.println("val: "+inputCorrect);
    }



    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(0x7f070000, menu);
        return true;
    }
    private static String getGalleryPath() {
        return Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DCIM + "/";
    }
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        // this is our fallback here
        return uri.getPath();
    }
}