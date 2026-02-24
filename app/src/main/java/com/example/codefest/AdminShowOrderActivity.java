package com.example.codefest;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefest.adapter.OrderItemModelAdapter;
import com.example.codefest.model.OrderItemModel;

import java.util.ArrayList;

public class AdminShowOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_show_order);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        ArrayList<OrderItemModel> orders = new ArrayList<>();
        orders.add(new OrderItemModel("x2", "Chicken Adobo", "$500.50"));
        orders.add(new OrderItemModel("x1", "Pork Sisig", "$100.00"));
        OrderItemModelAdapter OrderItemModelAdapter = new OrderItemModelAdapter(this, orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(OrderItemModelAdapter);

        TextView back = findViewById(R.id.back);

        back.setOnClickListener(v -> finish());
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}