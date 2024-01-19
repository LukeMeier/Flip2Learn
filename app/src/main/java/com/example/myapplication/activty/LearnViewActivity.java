package com.example.myapplication.activty;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import myapplication.service.CardService;

public class LearnViewActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;

    TextView cardText;
    CardService db;
    Cursor cardCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_view);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        cardText = findViewById(R.id.textcard);
        load();
    }

    public void flip(View view) {
        String germanName = cardCursor.getString(cardCursor.getColumnIndex("germanName"));
        String forgeinName = cardCursor.getString(cardCursor.getColumnIndex("forgeinName"));

        if (cardText.getText().toString().equals(forgeinName)) {
            cardText.setText(germanName);
        } else if (cardText.getText().toString().equals(germanName)) {
            cardText.setText(forgeinName);
        }
    }

    private void load() {
        db = new CardService(LearnViewActivity.this);
        cardCursor = db.readCardsNotLearned();

        cardCursor.moveToFirst();
        String forgeinName = cardCursor.getString(cardCursor.getColumnIndex("forgeinName"));
        cardText.setText(forgeinName);
    }

    private void next() {
        if(cardCursor.isLast()) {
            cardCursor = db.readCardsNotLearned();
            cardCursor.moveToFirst();
        } else {
            cardCursor.moveToNext();
        }
        String forgeinName = cardCursor.getString(cardCursor.getColumnIndex("forgeinName"));


        cardText.setText(forgeinName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (gyroscopeSensor != null) {
            sensorManager.registerListener( this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (gyroscopeSensor != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float angularSpeedY = event.values[1];

            if (angularSpeedY > 5.5) {
                db.setLearnedToTrueById(cardCursor.getInt(cardCursor.getColumnIndex("id")));
                next();
            }

            if (angularSpeedY < -5.5) {
                next();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void goBack(View view) {
        Intent intent = new Intent(this, CardViewActivity.class);
        startActivity(intent);
    }
}
