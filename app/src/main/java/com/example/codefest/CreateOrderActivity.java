package com.example.codefest;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefest.adapter.MenuAdapter;
import com.example.codefest.model.Menu;

import java.util.ArrayList;

public class CreateOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_order);

        RecyclerView recyclerView = findViewById(R.id.menuRecyclerView);
        ArrayList<Menu> menuArrayList = new ArrayList<>();
        menuArrayList.add(new Menu("test", "Adobo", "wala lang", "100", "yes"));
        menuArrayList.add(new Menu("test", "Galungong", "wala lang", "45", "yes"));
        menuArrayList.add(new Menu("test", "Menudo", "wala lang", "60.45", "yes"));
        menuArrayList.add(new Menu("test", "Siraulo", "wala lang", "100", "yes"));
        menuArrayList.add(new Menu("test", "Calderata", "wala lang", "100", "yes"));

        MenuAdapter menuAdapter = new MenuAdapter(this, menuArrayList);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(menuAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}