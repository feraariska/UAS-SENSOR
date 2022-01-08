package com.example.uassensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.Service;
import android.view.Menu;
import android.widget.TextView;

public class uassensor extends Activity implements SensorEventListener{
    //SensorManager lets you access the device's sensors
    //declare Variables
    TextView textView,TVAirPressure;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.TextView);
        TVAirPressure=(TextView)findViewById(R.id.TVAirPressure);
        //create instance of sensor manager and get system service to interact with Sensor
        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the Pressure Sensor
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    // called when sensor value have changed
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            float[] values = event.values;
            TVAirPressure.setText("" + values[0]);
        }

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
