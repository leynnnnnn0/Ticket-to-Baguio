package com.example.codefest;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.codefest.database.DatabaseHelper;
import com.example.codefest.databinding.ActivityEditMenuBinding;
import com.example.codefest.helper.ImageHelper;
import com.example.codefest.model.Menu;

public class EditMenuActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ActivityEditMenuBinding binding;
    Menu menu;
    private int menuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityEditMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);

        menuId = getIntent().getIntExtra("MENU_ID", -1);

        if (menuId == -1) {
            Toast.makeText(this, "Invalid menu ID", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        menu = databaseHelper.viewMenuDetails(menuId);

        if (menu == null) {
            Toast.makeText(this, "Menu not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Set data safely
        if (menu.image != null) {
            Bitmap bitmap = ImageHelper.stringToBitmap(menu.image);
            binding.imagePreview.setImageBitmap(bitmap);
        }

        binding.menuText.setText(menu.name);
        binding.priceInput.setText(String.valueOf(menu.price)); // NO peso sign here
        binding.descriptionText.setText(menu.description);
        binding.stockInput.setText(String.valueOf(menu.stock));

        binding.saveButton.setOnClickListener(v -> {

            if (binding.imagePreview.getDrawable() == null) {
                Toast.makeText(this, "Image missing", Toast.LENGTH_SHORT).show();
                return;
            }

            Bitmap bitmap = ((BitmapDrawable) binding.imagePreview.getDrawable()).getBitmap();
            String image_string = ImageHelper.bitmapToString(bitmap);

            String name = binding.menuText.getText().toString().trim();
            String description = binding.descriptionText.getText().toString().trim();
            String priceStr = binding.priceInput.getText().toString().trim();
            String stockStr = binding.stockInput.getText().toString().trim();

            if (priceStr.isEmpty() || stockStr.isEmpty()) {
                Toast.makeText(this, "Price and Stock cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            int price = Integer.parseInt(priceStr);
            int stock = Integer.parseInt(stockStr);

            if (menu.name.equals(name)
                    && menu.description.equals(description)
                    && price == menu.price
                    && stock == menu.stock
                    && menu.image.equals(image_string)) {

                Toast.makeText(this, "No Information changed!", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Information changed!", Toast.LENGTH_SHORT).show();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}