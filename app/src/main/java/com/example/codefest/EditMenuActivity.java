package com.example.codefest;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
    private final ActivityResultLauncher<String> galleryLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(),
                    uri -> {
                        if (uri != null) {
                            binding.imagePreview.setImageURI(uri);
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityEditMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this);

        menuId = getIntent().getIntExtra("MENU_ID", 1);

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
        if (menu.image != null) {
            Bitmap bitmap = ImageHelper.stringToBitmap(menu.image);
            binding.imagePreview.setImageBitmap(bitmap);
        }

        binding.menuText.setText(menu.name);
        binding.priceInput.setText(String.valueOf(menu.price));
        binding.descriptionText.setText(menu.description);
        binding.stockInput.setText(String.valueOf(menu.stock));

        binding.uploadButton.setOnClickListener(v -> openGallery());

        binding.saveButton.setOnClickListener(v -> {

            Bitmap bitmap = ((BitmapDrawable) binding.imagePreview.getDrawable()).getBitmap();
            String image_string = ImageHelper.bitmapToString(bitmap);

            String name = binding.menuText.getText().toString().trim();
            String description = binding.descriptionText.getText().toString().trim();
            String priceStr = binding.priceInput.getText().toString().trim();
            String stockStr = binding.stockInput.getText().toString().trim();

            if (name.isEmpty() || description.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty() || image_string.isEmpty()) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
                return;
            }

            int price = Integer.parseInt(priceStr);
            int stock = Integer.parseInt(stockStr);

            boolean updateMenu = databaseHelper.updateMenu(menuId, name, description, price, stock,image_string);

            if (updateMenu){
                Toast.makeText(this, "Menu updated successfully !", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(this, "Menu failed to update!", Toast.LENGTH_SHORT).show();
                return;
            }


        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void openGallery() {
        galleryLauncher.launch("image/*");
    }

}