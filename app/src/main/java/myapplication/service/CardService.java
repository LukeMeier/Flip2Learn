package myapplication.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CardService extends SQLiteOpenHelper {

    private Context context;
    private static final String dbName = "Cards.db";
    private static final int dbVersion = 1;

    public CardService(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE card (id INTEGER PRIMARY KEY AUTOINCREMENT, germanName TEXT, forgeinName TEXT, learned BOOLEAN)";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS card");
        onCreate(db);
    }


    public void addCard(String germanName, String forgeinName, Boolean learned) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("germanName", germanName);
        contentValues.put("forgeinName", forgeinName);
        contentValues.put("learned", learned);

        long result = db.insert("card", null, contentValues);

        if (result == -1) {
            Toast.makeText(context, "Cardset insertion failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Cardset inserted successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readCards(){
        String query = "SELECT * FROM card";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readCardsNotLearned(){
        String query = "SELECT * FROM card WHERE learned IS false";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    public void setLearnedToTrueById(int cardId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("learned", true);

        db.update("card", values, "id" + "=?", new String[]{String.valueOf(cardId)});
        db.close();
    }

    public void setLearnedToFalse() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("learned", false);

        db.update("card", values, null, null);
        db.close();
    }
    }
}
