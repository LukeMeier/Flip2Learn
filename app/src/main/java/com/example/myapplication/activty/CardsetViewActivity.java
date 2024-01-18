package com.example.myapplication.activty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;

public class CardsetViewActivity extends AppCompatActivity {

    LinearLayout cardsets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardset_view);
        cardsets = findViewById(R.id.cardsets);
    }

    public void add(View view) {
        TextView newTextView = new TextView(this);

        newTextView.setHeight(36);
        newTextView.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        newTextView.setBackgroundColor(0xFFFF0000);
        newTextView.setText("Hallo");


        cardsets.addView(newTextView);
    }
    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}