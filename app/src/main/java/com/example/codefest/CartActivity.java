package com.example.codefest;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefest.databinding.ActivityCartBinding;
import com.example.codefest.adapter.CartAdapter;
import com.example.codefest.helper.NavHelper;
import com.example.codefest.model.Cart;
import com.example.codefest.database.DatabaseHelper;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    ActivityCartBinding binding;
    DatabaseHelper databaseHelper;
    ArrayList<Cart> cartArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);
        cartArrayList = databaseHelper.getUserCartItem();

        RecyclerView cartRecyclerView = binding.cartRecyclerView;

        CartAdapter cartAdapter = new CartAdapter(this, cartArrayList, () -> {
            binding.totalPriceText.setText("₱" + getGrandTotal());
        });

        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartRecyclerView.setAdapter(cartAdapter);

        binding.totalPriceText.setText("₱" + getGrandTotal());

        binding.backButton.setOnClickListener(v -> {
            NavHelper.toMainDashboard(this);
        });
    }

    public int getGrandTotal() {
        int total = 0;

        for (Cart cart : cartArrayList) {
            total += cart.getTotalPrice();
        }

        return total;
    }
}