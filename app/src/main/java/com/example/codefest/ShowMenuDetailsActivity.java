package com.example.codefest;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefest.adapter.ReviewAdapter;
import com.example.codefest.model.Review;

import java.util.ArrayList;

public class ShowMenuDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_menu_details);

        ArrayList<Review> reviewArrayList = new ArrayList<>();
        reviewArrayList.add(new Review("John Doe", "This taste awful. I will never buy this again!", 1));
        reviewArrayList.add(new Review("Sarah Duterte", "Putcha ka BBM!", 5));
        reviewArrayList.add(new Review("John Doe", "This taste awful. I will never buy this again!", 1));
        reviewArrayList.add(new Review("Sarah Duterte", "Putcha ka BBM!", 5));
        reviewArrayList.add(new Review("John Doe", "This taste awful. I will never buy this again!", 1));
        reviewArrayList.add(new Review("Sarah Duterte", "Putcha ka BBM!", 5));

        RecyclerView recyclerView = findViewById(R.id.reviewsRecyclerView);
        ReviewAdapter reviewAdapter = new ReviewAdapter(this, reviewArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(reviewAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}