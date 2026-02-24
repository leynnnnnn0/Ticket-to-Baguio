package com.example.codefest;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.codefest.helper.NavHelper;

public class AdminShowMenuActivity extends AppCompatActivity {
    TextView goToEdit, backToList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_show_menu);

        goToEdit = findViewById(R.id.goToEdit);
        backToList = findViewById(R.id.backToList);

        goToEdit.setOnClickListener(v -> {
            NavHelper.navigate(this, AdminEditMenuActivity.class);
        });

        backToList.setOnClickListener(v -> finish());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}