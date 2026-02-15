package com.example.codefest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String dbName = "ticket-to-baguio.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(username TEXT, email TEXT PRIMARY KEY, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

//    CREATE QUERIES
    public Boolean insertUser(String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);

        long result = db.insert("users", null, contentValues);

        return result != -1;
    }

//    READ QUERIES
    public Boolean checkEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery(
                "SELECT 1 FROM users WHERE email = ?",
                new String[]{email})) {

            return cursor.moveToFirst(); // if true email has been used.
        }
        catch (Exception exception){
            System.out.println("Error:" + exception);
            return false;
        }
    }

    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery(
                "SELECT 1 FROM users WHERE email = ? and password = ?",
                new String[]{email, password})) {

            return cursor.moveToFirst(); //If true there credentials are correct
        }
        catch (Exception exception){
            System.out.println("Error:" + exception);
            return false;
        }
    }

//    UPDATE QUERIES
//    DELETE QUERIES

}
