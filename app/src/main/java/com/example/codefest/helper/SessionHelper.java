package com.example.codefest.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionHelper {
    public static final String KEY_USER_ID = "USER_ID";

    public static void setUserId(Context context, int userId){
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_USER_ID, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(KEY_USER_ID, userId).apply();
    }

    public static int getUserId(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(KEY_USER_ID, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(KEY_USER_ID, -1);
    }

}
