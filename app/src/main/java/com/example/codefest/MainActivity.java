package com.example.codefest;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.codefest.database.DatabaseHelper;
import com.example.codefest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    DatabaseHelper databaseHelper;
    ActivityMainBinding binding;
    ImageButton btnHome, btnSearch, btnProfile;

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


        btnHome    = findViewById(R.id.btn_home);
        btnSearch  = findViewById(R.id.btn_search);
        btnProfile = findViewById(R.id.btn_profile);

        btnHome.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnProfile.setOnClickListener(this);

        loadFragment(new CustomerHomeFragment());
    }


    public void onClick(View v) {
        if (v.getId() == R.id.btn_home) {
            loadFragment(new CustomerHomeFragment());
        }
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


}