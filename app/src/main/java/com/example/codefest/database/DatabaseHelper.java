package com.example.codefest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.codefest.helper.SessionHelper;
import com.example.codefest.model.Cart;
import com.example.codefest.model.Menu;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String dbName = "ticket-to-baguio.db";
    private  Context context;

    public DatabaseHelper(@Nullable Context context)
    {
        super(context, dbName, null, 5);
        this.context = context;
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
                    "stock INTEGER, " +
                    "image_path TEXT, " +
                    "created_at DATETIME DEFAULT CURRENT_TIMESTAMP )");

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
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        cv.put("role", "customer");

        long result = db.insert("users", null, cv);

        return result != -1;
    }
    public Boolean insertStaffUser(String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        cv.put("role", "staff");

        long result = db.insert("users", null, cv);

        return result != -1;
    }

    public boolean insertNewMenu(String name, String description, int price, int stock, String image_path) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("description", description);
        cv.put("price", price);
        cv.put("stock", stock );
        cv.put("image_path", image_path);

        long result = db.insert("menu", null, cv);
        return result != -1;
    }

    public boolean insertToCart(int userId, int menuId, int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("userId", userId);
        cv.put("menuId", menuId);
        cv.put("quantity", quantity);

        long result = db.insert("cart", null, cv);

        return result != 1;
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

    public int checkEmailPassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor cursor = db.rawQuery(
                "SELECT id FROM users WHERE email = ? and password = ?",
                new String[]{email, password})) {

            if (cursor.moveToFirst()){
                int userId = cursor.getInt(0);
                cursor.close();
                return userId;
            }
            return -1;
        }
        catch (Exception exception){
            System.out.println("Error:" + exception);
            return -1;
        }
    }


    public ArrayList<Menu> getAllAvailableMenu() {
        ArrayList<Menu> menuList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM menu", null);

        try {
            if (cursor != null && cursor.moveToFirst()) {
                int idInx = cursor.getColumnIndex("id");
                int nameIdx = cursor.getColumnIndex("name");
                int descIdx = cursor.getColumnIndex("description");
                int priceIdx = cursor.getColumnIndex("price");
                int stockIdx = cursor.getColumnIndex("stock");
                int imageIdx = cursor.getColumnIndex("image_path");

                do {
                    Menu menu = new Menu(
                            cursor.getInt(idInx),
                            cursor.getString(nameIdx),
                            cursor.getString(descIdx),
                            cursor.getInt(priceIdx),
                            cursor.getInt(stockIdx),
                            cursor.getString(imageIdx)
                            );
                    menuList.add(menu);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
        }
        return menuList;
    }


    public ArrayList<Cart> getUserCartItem(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Cart> cartList = new ArrayList<>();
        int userId = SessionHelper.getUserId(context);

        Cursor cursor = db.rawQuery(
                "SELECT m.id, m.name, m.price, c.quantity, m.image_path " +
                        "FROM cart c " +
                        "INNER JOIN menu m ON c.menuId = m.id " +
                        "WHERE c.userId = ?",
                new String[]{String.valueOf(userId)}
        );

        if (cursor != null && cursor.moveToFirst()){
            do {
                Cart cart = new Cart(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getString(4)
                ); cartList.add(cart);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cartList;
    }

//    UPDATE QUERIES
//    DELETE QUERIES

}
