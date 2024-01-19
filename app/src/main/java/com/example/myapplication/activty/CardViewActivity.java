package com.example.myapplication.activty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;

import myapplication.service.CardService;

public class CardViewActivity extends AppCompatActivity {

    LinearLayout cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        cards = findViewById(R.id.cards);
        load();
    }


    public void goBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void add(View view) {
        Intent intent = new Intent(this, CardAddViewActivity.class);
        startActivity(intent);
    }

    private void load() {
        CardService db = new CardService(CardViewActivity.this);

        Cursor cardCursor = db.readCards();

        if (cardCursor != null && cardCursor.moveToFirst()) {
            do {
                String germanName = cardCursor.getString(cardCursor.getColumnIndex("germanName"));
                String forgeinName = cardCursor.getString(cardCursor.getColumnIndex("forgeinName"));
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        50
                );

                layoutParams2.setMargins(0, 5, 0, 5);
                layoutParams.setMargins(0, 30, 0, 30);
                linearLayout.setLayoutParams(layoutParams);
                TextView textView1 = new TextView(this);
                textView1.setText(germanName);
                textView1.setLayoutParams(layoutParams2);
                textView1.setTextColor(0xFF000000);
                textView1.setBackgroundColor(0xFFFF0000);
                TextView textView2 = new TextView(this);
                textView2.setText(forgeinName);
                textView2.setLayoutParams(layoutParams2);
                textView2.setTextColor(0xFF000000);
                textView2.setBackgroundColor(0xFFFF0000);

                linearLayout.addView(textView1);
                linearLayout.addView(textView2);

                cards.addView(linearLayout);

                System.out.println("Card ID: " + germanName + ", Card Name: " + forgeinName);

            } while (cardCursor.moveToNext());
        }

        if (cardCursor != null) {
            cardCursor.close();
        }
    }

    public  void learn(View view) {
        CardService db = new CardService(CardViewActivity.this);
        db.setLearnedToFalse();
        Cursor cardCursor = db.readCards();
        Intent intent = new Intent(this, LearnViewActivity.class);
        startActivity(intent);
    }
}