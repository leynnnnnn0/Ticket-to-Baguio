package com.example.codefest;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefest.databinding.ActivityCartBinding;
import com.example.codefest.adapter.CartAdapter;
import com.example.codefest.helper.NavHelper;
import com.example.codefest.model.Menu;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView cartRecyclerView = findViewById(R.id.cartRecyclerView);
        ArrayList<Menu> menuArrayList = new ArrayList<>();

        CartAdapter cartAdapter = new CartAdapter(this, menuArrayList);

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        cartRecyclerView.setAdapter(cartAdapter);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.backButton.setOnClickListener(v -> {
            NavHelper.toMainDashboard(this);
        });
    }
}