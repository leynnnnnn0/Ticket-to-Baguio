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
    ImageButton btnHome, btn_menu, btn_order, btn_sales,btn_settings,btn_staff;

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
        btn_menu    = findViewById(R.id.btn_menu);
        btn_order   = findViewById(R.id.btn_order);
        btn_sales   = findViewById(R.id.btn_sales);
        btn_settings  = findViewById(R.id.btn_settings);
        btn_staff  = findViewById(R.id.btn_staff);


        btnHome.setOnClickListener(this);
        btn_menu.setOnClickListener(this);
        btn_order.setOnClickListener(this);
        btn_sales.setOnClickListener(this);
        btn_settings.setOnClickListener(this);
        btn_staff.setOnClickListener(this);

        loadFragment(new AdminDashboardFragment());
    }


    public void onClick(View v) {
        if (v.getId() == R.id.btn_home) {
            loadFragment(new AdminDashboardFragment());
        }
        if (v.getId() == R.id.btn_menu) {
            loadFragment(new AdminMenuFragment());
        }
        if (v.getId() == R.id.btn_order) {
            loadFragment(new AdminOrderFragment());
        }
        if (v.getId() == R.id.btn_sales) {
            loadFragment(new AdminSales());
        }
        if (v.getId() == R.id.btn_settings) {
            loadFragment(new SettingsFragment());
        }
        if (v.getId() == R.id.btn_staff) {
            loadFragment(new AdminStaffFragment());
        }
    }

    private void loadFragment(Fragment fragment)
    {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }




}