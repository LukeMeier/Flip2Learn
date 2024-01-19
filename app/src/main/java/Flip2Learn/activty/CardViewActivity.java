package Flip2Learn.activty;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;

import Flip2Learn.service.CardService;

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
                @SuppressLint("Range") String germanName = cardCursor.getString(cardCursor.getColumnIndex("germanName"));
                @SuppressLint("Range") String foreignName = cardCursor.getString(cardCursor.getColumnIndex("foreignName"));
                LinearLayout linearLayout = new LinearLayout(this);
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                layoutParams.setMargins(0, 30, 0, 30);
                linearLayout.setLayoutParams(layoutParams);
                linearLayout.addView(addTextView(germanName));
                linearLayout.addView(addTextView(foreignName));
                cards.addView(linearLayout);
            } while (cardCursor.moveToNext());
        }
        if (cardCursor != null) {
            cardCursor.close();
        }
    }

    private TextView addTextView(String text) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                50
        );
        layoutParams.setMargins(0, 5, 0, 5);
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setLayoutParams(layoutParams);
        textView.setTextColor(0xFF000000);
        textView.setBackgroundColor(0xFFFF0000);

        return textView;
    }

    public  void learn(View view) {
        CardService db = new CardService(CardViewActivity.this);
        db.setLearnedToFalse();
        Intent intent = new Intent(this, LearnViewActivity.class);
        startActivity(intent);
    }
}