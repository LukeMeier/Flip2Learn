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


    public void updateCardToTrue(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("learned", true);

        long result = db.update("cardset", cv, "id=?", new String[]{id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public void updateAllCardsToFalse(String setId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("learned", false);

        long result = db.update("cardset", cv, "cardsetId=?", new String[]{setId});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }


    public void deleteOneCard(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("card", "id=?", new String[]{id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
