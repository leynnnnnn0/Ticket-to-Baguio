package com.example.codefest;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefest.adapter.OrderAdapter;
import com.example.codefest.model.Order;

import java.util.ArrayList;

public class AdminDashboard extends AppCompatActivity {

    RecyclerView ordersRecyclerView;
    ArrayList<Order> orderArrayList;
    OrderAdapter orderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_dashboard);

        ordersRecyclerView = findViewById(R.id.ordersRecyclerView);

        orderArrayList = new ArrayList<>();
        orderArrayList.add(new Order("ORD-8434-4324", "100", "February 11, 2025 at 12:51 pm", "pending"));
        orderArrayList.add(new Order("ORD-8434-4324", "100", "February 11, 2025 at 12:51 pm", "pending"));
        orderArrayList.add(new Order("ORD-8434-4324", "100", "February 11, 2025 at 12:51 pm", "pending"));
        orderArrayList.add(new Order("ORD-8434-4324", "100", "February 11, 2025 at 12:51 pm", "pending"));
        orderArrayList.add(new Order("ORD-8434-4324", "100", "February 11, 2025 at 12:51 pm", "pending"));

        orderAdapter = new OrderAdapter(this, orderArrayList);

        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ordersRecyclerView.setAdapter(orderAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}