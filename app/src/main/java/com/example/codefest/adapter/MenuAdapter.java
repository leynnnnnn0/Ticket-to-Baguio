package com.example.codefest.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefest.databinding.MenuListBinding;
import com.example.codefest.helper.ImageHelper;
import com.example.codefest.model.Menu;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private Context context;
    private ArrayList<Menu> menuArrayList;

    public MenuAdapter(Context context, ArrayList<Menu> menuArrayList) {
        this.context = context;
        this.menuArrayList = menuArrayList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MenuListBinding binding = MenuListBinding.inflate(inflater, parent, false);
        return new MenuViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Menu menu = menuArrayList.get(position);

        Bitmap bitmap = ImageHelper.stringToBitmap(menu.image);

        holder.binding.image.setImageBitmap(bitmap);
        holder.binding.name.setText(menu.name);
        holder.binding.price.setText("₱ " + menu.price);

        Log.d("MENU", "ID " + menu.id);

        holder.binding.addToCartButton.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return menuArrayList.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {

        MenuListBinding binding;

        public MenuViewHolder(MenuListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}