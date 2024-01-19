package Flip2Learn.activty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import Flip2Learn.service.CardService;

public class CardAddViewActivity extends AppCompatActivity {

    EditText germanName, foreignName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_add_view);
        foreignName = findViewById(R.id.foreignName);
        germanName = findViewById(R.id.germanName);
    }
    public void add(View view) {
        CardService db = new CardService(CardAddViewActivity.this);
        db.addCard(germanName.getText().toString(), foreignName.getText().toString(), false);
        Intent intent = new Intent(this, CardViewActivity.class);
        startActivity(intent);
    }
}
