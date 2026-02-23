package com.example.codefest;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.codefest.database.DatabaseHelper;
import com.example.codefest.databinding.ActivityCreateNewMenuBinding;
import com.example.codefest.helper.ImageHelper;
import com.example.codefest.helper.NavHelper;


public class CreateNewMenu extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ActivityCreateNewMenuBinding binding;
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

        binding = ActivityCreateNewMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.backButton.setOnClickListener(v -> {
            NavHelper.toMainDashboard(this);
        });

        binding.uploadButton.setOnClickListener(v -> openGallery());

        binding.saveButton.setOnClickListener(v -> {
            String menuName = binding.nameInput.getText().toString().trim();
            String menuDesc = binding.descriptionInput.getText().toString().trim();
            String menuPriceStr = binding.priceInput.getText().toString().trim();
            String menuStockStr = binding.stockInput.getText().toString().trim();

            // Validations
            if (TextUtils.isEmpty(menuName) || TextUtils.isEmpty(menuDesc) || TextUtils.isEmpty(menuPriceStr) || TextUtils.isEmpty(menuStockStr)) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
                return;
            }

            int menuPrice, menuStock;
            try {
                menuPrice = Integer.parseInt(menuPriceStr);
                menuStock = Integer.parseInt(menuStockStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Price and Stock must be numbers!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (menuPrice <= 0){
                Toast.makeText(this, "Invalid Price", Toast.LENGTH_SHORT).show();
                return;
            }

            if (binding.imagePreview.getDrawable() == null){
                Toast.makeText(this, "Please select an Image", Toast.LENGTH_SHORT).show();
                return;
            }

            Bitmap bitmap = ((BitmapDrawable) binding.imagePreview.getDrawable()).getBitmap();
            String image_base64 = ImageHelper.bitmapToString(bitmap);

            if (image_base64 == null || image_base64.isEmpty()) {
                Toast.makeText(this, "Image processing failed!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean inserted = databaseHelper.insertNewMenu(menuName, menuDesc, menuPrice, menuStock, image_base64);

            if (inserted) {
                Toast.makeText(this, "New Menu Added and is now Available!", Toast.LENGTH_SHORT).show();
                clearForm();
            } else {
                Toast.makeText(this, "Failed adding new menu!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void openGallery() {
        galleryLauncher.launch("image/*");
    }

    private void clearForm() {
        binding.nameInput.setText("");
        binding.descriptionInput.setText("");
        binding.priceInput.setText("");
        binding.stockInput.setText("");
        binding.imagePreview.setImageDrawable(null);
    }

}