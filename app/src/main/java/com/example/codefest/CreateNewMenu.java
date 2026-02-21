package com.example.codefest;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageView;
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

        binding.saveButton.setOnClickListener(v ->{
            String menuName = binding.nameInput.getText().toString().trim();
            String menuDesc = binding.descriptionInput.getText().toString().trim();
            String menuPriceStr = binding.priceInput.getText().toString().trim();

            if (TextUtils.isEmpty(menuName) || TextUtils.isEmpty(menuDesc) || TextUtils.isEmpty(menuPriceStr)){
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();

            }

            int menuPrice = Integer.parseInt(menuPriceStr);

            if (menuPrice <= 0){
                Toast.makeText(this, "Invalid Price", Toast.LENGTH_SHORT).show();

            }

            if (binding.imagePreview.getDrawable() == null){
                Toast.makeText(this, "Pls Select an Image", Toast.LENGTH_SHORT).show();

            }

            Bitmap bitmap = ((BitmapDrawable) binding.imagePreview.getDrawable()).getBitmap();
            byte[] imageBytes = ImageHelper.bitmapToByteArray(bitmap);

            if (imageBytes == null || imageBytes.length == 0){
                Toast.makeText(this, "Image processing failed!", Toast.LENGTH_SHORT).show();

            }

            boolean inserted = databaseHelper.insertNewMenu(menuName, menuDesc, menuPrice, imageBytes);

            if (inserted) {
                Toast.makeText(this, "New Menu Added and is now Available!", Toast.LENGTH_SHORT).show();
                clearForm();
            }
            else {
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
        binding.imagePreview.setImageDrawable(null);
    }

}