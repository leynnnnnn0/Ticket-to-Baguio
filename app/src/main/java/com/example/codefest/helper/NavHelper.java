package com.example.codefest.helper;

import android.content.Context;
import android.content.Intent;

import com.example.codefest.MainActivity;

public class NavHelper {

    public static void navigate(Context context, Class<?> destination){
        context.startActivity(new Intent(context, destination));
    }
    public static void toMainDashboard(Context context){
        navigate(context, MainActivity.class);
    }
}
