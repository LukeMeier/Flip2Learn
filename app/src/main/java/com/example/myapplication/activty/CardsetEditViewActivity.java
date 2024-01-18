package com.example.myapplication.activty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class CardsetEditViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardset_edit_view);
    }

    public void add(View view) {
    }
    public void goBack(View view) {
        Intent intent = new Intent(this, CardsetViewActivity.class);
        startActivity(intent);
    }
}
