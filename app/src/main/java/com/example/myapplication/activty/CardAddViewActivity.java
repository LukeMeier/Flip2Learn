package com.example.myapplication.activty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import myapplication.service.CardService;

public class CardAddViewActivity extends AppCompatActivity {

    EditText germanName, forgeinName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_add_view);
        forgeinName = findViewById(R.id.forgeinName);
        germanName = findViewById(R.id.germanName);
    }
    public void add(View view) {
        CardService db = new CardService(CardAddViewActivity.this);
        db.addCard(germanName.getText().toString(), forgeinName.getText().toString(), false);
        Intent intent = new Intent(this, CardViewActivity.class);
        startActivity(intent);
    }
}
