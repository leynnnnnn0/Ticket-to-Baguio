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
import com.example.codefest.helper.ImageHelper;
import com.example.codefest.helper.NavHelper;
import com.example.codefest.model.Menu;
import com.example.codefest.model.Review;
import com.example.codefest.databinding.ActivityShowMenuDetailsBinding;
import com.example.codefest.database.DatabaseHelper;

import java.util.ArrayList;

public class ShowMenuDetailsActivity extends AppCompatActivity {

    ActivityShowMenuDetailsBinding binding;
    DatabaseHelper databaseHelper;
    private int menuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding= ActivityShowMenuDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);

        menuId = getIntent().getIntExtra("MENU_ID", -1);

        Menu menu = databaseHelper.viewMenuDetails(menuId);


        binding.menuText.setText(menu.name);
        binding.priceText.setText("₱" + menu.price);
        binding.descriptionText.setText(menu.description);
        binding.imagePreview.setImageBitmap(ImageHelper.stringToBitmap(menu.image));

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

        binding.backButton.setOnClickListener(v -> {
            NavHelper.route(this, CartActivity.class);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}