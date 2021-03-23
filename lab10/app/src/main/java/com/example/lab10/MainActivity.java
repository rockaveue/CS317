package com.example.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

//    Button start;
    ImageView img;
    AnimationDrawable pepe;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        start = (Button)findViewById(R.id.button);
        img = (ImageView)findViewById(R.id.imageView);
        img.setImageResource(R.drawable.pepepls);
        pepe = (AnimationDrawable)img.getDrawable();
        pepe.start();
        /*start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pepe.start();
            }
        });*/
    }

    public boolean onTouchEvent(MotionEvent e) {

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                i = getCurFrameFromAnimationDrawable(pepe);
                pepe.stop();
                break;
            case MotionEvent.ACTION_UP:
                setCurFrameToAnimationDrawable(pepe, i);
                break;
        }

        return super.onTouchEvent(e);
    }
    private int getCurFrameFromAnimationDrawable(AnimationDrawable drawable){
        try {
            Field mCurFrame = drawable.getClass().getDeclaredField("mCurFrame");
            mCurFrame.setAccessible(true);
            return  mCurFrame.getInt(drawable);
        }catch (Exception ex){
            Log.e("error", "getCurFrameFromAnimationDrawable: Exception");
            return 0;
        }
    }

    private void setCurFrameToAnimationDrawable(AnimationDrawable drawable, int curFrame){
        try {
            Field mCurFrame = drawable.getClass().getDeclaredField("mCurFrame");
            mCurFrame.setAccessible(true);
            mCurFrame.setInt(drawable, curFrame);
            Class[] param = new Class[]{Runnable.class, long.class};
            Method method = Drawable.class.getDeclaredMethod("scheduleSelf", param);
            method.setAccessible(true);
            method.invoke(drawable, drawable, 30L);
        }catch (Exception ex){
            Log.e("error", "setCurFrameToAnimationDrawable: Exception");
        }
    }
}