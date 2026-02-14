package com.example.codefest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefest.R;
import com.example.codefest.model.Menu;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    public Context context;
    public ArrayList<Menu> menuArrayList;

    public MenuAdapter(Context context, ArrayList<Menu> menuArrayList) {
        this.context = context;
        this.menuArrayList = menuArrayList;
    }

    @NonNull
    @Override
    public MenuAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.menu_list, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MenuViewHolder holder, int position) {
        Menu menu = menuArrayList.get(position);
        holder.name.setText(menu.name);
        holder.price.setText(menu.price);
    }

    @Override
    public int getItemCount() {
        return menuArrayList.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price;
        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }
}
