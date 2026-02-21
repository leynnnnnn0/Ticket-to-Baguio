package com.example.codefest;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.codefest.database.DatabaseHelper;
import com.example.codefest.databinding.ActivityRegisterBinding;


public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        //  Proper ViewBinding setup
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //  Initialize database
        databaseHelper = new DatabaseHelper(this);

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.registerButton.setOnClickListener(v -> {

            String username = binding.usernameInput.getText().toString().trim();
            String email = binding.emailInput.getText().toString().trim();
            String password = binding.passwordInput.getText().toString().trim();
            String confirm_password = binding.confirmPasswordInput.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                Toast.makeText(this, "All Fields are Required!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirm_password)) {
                Toast.makeText(this, "Password didn't match", Toast.LENGTH_SHORT).show();
                return;
            }

            Boolean checkEmail = databaseHelper.checkEmail(email);

            if (checkEmail){
                Toast.makeText(this, "Email is already taken", Toast.LENGTH_SHORT).show();
                return;
            }

            Boolean insertUser = databaseHelper.insertCustomerUser(username, email, password);

            if (insertUser){
                Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SigninActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Registration Failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
