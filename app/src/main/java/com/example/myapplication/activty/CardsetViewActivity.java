package com.example.myapplication.activty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.myapplication.R;

public class CardsetViewActivity extends AppCompatActivity {

    LinearLayout cardsets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardset_view);
        cardsets = findViewById(R.id.cards);
    }

    public void add(View view) {
        Intent intent = new Intent(this, CardsetEditViewActivity.class);
        startActivity(intent);
    }
    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}