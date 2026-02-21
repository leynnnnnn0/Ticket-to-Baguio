package com.example.codefest;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.codefest.database.DatabaseHelper;
import com.example.codefest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

//    DatabaseHelper databaseHelper;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.shopButton.setOnClickListener(v -> {
            startActivity(new Intent(this, CreateOrderActivity.class));
        });

        binding.cartButton.setOnClickListener( v -> {
            startActivity(new Intent(this, CartActivity.class));
        });

        binding.menuButton.setOnClickListener(v -> {
            startActivity(new Intent(this, CreateNewMenu.class));
        });
    }
}