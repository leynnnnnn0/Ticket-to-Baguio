package com.example.codefest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.codefest.database.DatabaseHelper;
import  com.example.codefest.databinding.ActivitySigninBinding;


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

        binding.loginButton.setOnClickListener(v -> {
            String email = binding.emailInput.getText().toString().trim();
            String password = binding.passwordInput.getText().toString().trim();
            Boolean checkCredentials = databaseHelper.checkEmailPassword(email, password);

            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                Toast.makeText(this, "All Fields are required!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (checkCredentials){
                Toast.makeText(this, "Sign in Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, AdminDashboard.class));
                finish();
            } else {
                Toast.makeText(this, "Wrong Credentials!", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }
}