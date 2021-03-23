package com.example.lab7;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensorAccel, sensorLight;
    private boolean color = false;
    private boolean color2 = false;
    private View view ;
    private TextView view2,view3;
    private long lastUpdate;
    private float befLight = 0;

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.textView);
        view.setBackgroundColor(Color.GREEN);
        view3 = findViewById(R.id.textView2);

        view2 = findViewById(R.id.textView1);
        view2.setBackgroundColor(Color.CYAN);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();
        sensorAccel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onStart(){
        super.onStart();
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        } else if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            if (event.values[0] != befLight)
                getLight(event);
        }

    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        long actualTime = System.currentTimeMillis();
        if (accelationSquareRoot >= 2) //
        {
            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;
            Toast.makeText(this, "Utsiig segserlee", Toast.LENGTH_SHORT)
                    .show();
            if (color) {
                view.setBackgroundColor(Color.CYAN);

            } else {
                view.setBackgroundColor(Color.RED);
            }
            color = !color;
        }
    }

    public void getLight(SensorEvent event){
        befLight = event.values[0];
        view2.setText(String.valueOf(befLight));
//        view3.setText(event.values[0] + "");
        int grayShade = (int) event.values[0];
//            grayShade = 255;
            Toast.makeText(this, "Utsand tod gerel tuslaa", Toast.LENGTH_SHORT).show();
            if (color2){
                view2.setBackgroundColor(Color.CYAN);
            } else {
                view2.setBackgroundColor(Color.RED);
            }
            color2 = !color2;
        // http://www.android-examples.com/set-change-screen-brightness-in-android-programmatically/
        // Settings.System.putInt(MainActivity.this.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, grayShade);

//        view2.setTextColor(Color.rgb(255 - grayShade, 255 - grayShade, 255 - grayShade));
//        view2.setBackgroundColor(Color.rgb(grayShade, grayShade, grayShade));
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,
                sensorAccel,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,
                sensorLight,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}