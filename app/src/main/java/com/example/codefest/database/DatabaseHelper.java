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
        super(context, dbName, null, 4);
    }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE users(username TEXT, email TEXT PRIMARY KEY, password TEXT, role TEXT)");

            db.execSQL("CREATE TABLE menu(id INT PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, price FLOAT, image BLOB )");

            ContentValues cv = new ContentValues();
            cv.put("username", "Admin");
            cv.put("email", "email@gmail.com");
            cv.put("password", "admin123");
            cv.put("role", "admin");

            db.insert("users", null, cv);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (oldVersion < 2) {
                db.execSQL("ALTER TABLE users ADD COLUMN role TEXT");
                db.execSQL("DELETE FROM users");

            }
            if (oldVersion < 3 ){
                ContentValues cv = new ContentValues();
                cv.put("username", "Admin");
                cv.put("email", "email@gmail.com");
                cv.put("password", "admin123");
                cv.put("role", "admin");

                db.insert("users", null, cv);
            }

            if (oldVersion < 4){
                db.execSQL("CREATE TABLE menu(id INT PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, price FLOAT, image BLOB )");
            }
        }

//    CREATE QUERIES
    public Boolean insertCustomerUser(String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("role", "customer");

        long result = db.insert("users", null, contentValues);

        return result == 1;
    }
    public Boolean insertStaffUser(String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("role", "staff");

        long result = db.insert("users", null, contentValues);

        return result == 1;
    }

    public Boolean insertNewMenu(int menuId, String imagePath, String name, String Description, int price ){
        return true;
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
