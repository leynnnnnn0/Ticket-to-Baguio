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
            db.execSQL("CREATE TABLE users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT, " +
                    "email TEXT UNIQUE, " +
                    "password TEXT, " +
                    "role TEXT)");

            db.execSQL("CREATE TABLE menu (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, " +
                    "description TEXT, " +
                    "price INTEGER, " +
                    "image BLOB)");

            db.execSQL("CREATE TABLE cart (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "userId INTEGER, " +
                    "menuId INTEGER, " +
                    "quantity INTEGER, " +
                    "createdAt DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (userId) REFERENCES users (id), " +
                    "FOREIGN KEY (menuId) REFERENCES menu (id))");

            ContentValues cvAdmin = new ContentValues();
            cvAdmin.put("username", "Admin");
            cvAdmin.put("email", "admin@gmail.com");
            cvAdmin.put("password", "admin123");
            cvAdmin.put("role", "admin");

            db.insert("users", null, cvAdmin);

            ContentValues cvCustomer = new ContentValues();
            cvCustomer.put("username", "Customer");
            cvCustomer.put("email", "customer@gmail.com");
            cvCustomer.put("password", "customer123");
            cvCustomer.put("role", "customer");

            db.insert("users", null, cvCustomer);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS users");
            db.execSQL("DROP TABLE IF EXISTS menu");
            db.execSQL("DROP TABLE IF EXISTS cart");

            onCreate(db);
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

        return result != -1;
    }
    public Boolean insertStaffUser(String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("role", "staff");

        long result = db.insert("users", null, contentValues);

        return result != -1;
    }

    public boolean insertNewMenu(String name, String description, int price, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("description", description);
        cv.put("price", price);
        cv.put("image", image);

        long result = db.insert("menu", null, cv);
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
