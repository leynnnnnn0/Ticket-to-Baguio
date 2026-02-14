package com.example.codefest;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefest.adapter.OrderItemAdapter;
import com.example.codefest.model.OrderItem;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_details);

        RecyclerView orderDetailsRecyclerView = findViewById(R.id.orderDetailsRecyclerView);
        ArrayList<OrderItem> orderItemArrayList = new ArrayList<>();
        orderItemArrayList.add(new OrderItem("x3", "Adobo", "$542.00"));
        orderItemArrayList.add(new OrderItem("x1", "Siraulo", "$100.00"));

        orderDetailsRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        OrderItemAdapter orderItemAdapter = new OrderItemAdapter(this, orderItemArrayList);
        orderDetailsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderDetailsRecyclerView.setAdapter(orderItemAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}