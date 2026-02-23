package com.example.codefest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.codefest.database.DatabaseHelper;
import  com.example.codefest.databinding.ActivitySigninBinding;
import com.example.codefest.helper.NavHelper;
import com.example.codefest.helper.SessionHelper;


public class SigninActivity extends AppCompatActivity {

    ActivitySigninBinding binding;
    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.signup.setOnClickListener(v -> NavHelper.navigate(this, RegisterActivity.class));

        binding.loginButton.setOnClickListener(v -> {
            String email = binding.emailInput.getText().toString().trim();
            String password = binding.passwordInput.getText().toString().trim();
            int checkCredentials = databaseHelper.checkEmailPassword(email, password);

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                Toast.makeText(this, "All Fields are required!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (checkCredentials == -1){
                Toast.makeText(this, "Wrong Credentials!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Log.d("CHECKID", "USER_ID" + checkCredentials);
                SessionHelper.setUserId(this, checkCredentials);
                Toast.makeText(this, "Sign in Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        });
    }
}