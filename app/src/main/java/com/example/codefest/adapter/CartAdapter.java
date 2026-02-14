package com.example.codefest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefest.R;
import com.example.codefest.model.Menu;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CardViewHolder> {
    Context context;
    ArrayList<Menu> menuArrayList;

    public CartAdapter(Context context, ArrayList<Menu> menuArrayList) {
        this.context = context;
        this.menuArrayList = menuArrayList;
    }

    @NonNull
    @Override
    public CartAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cart_list, parent, false);


        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CardViewHolder holder, int position) {
        Menu menu = menuArrayList.get(position);
        holder.name.setText(menu.name);
        holder.price.setText(menu.price);


        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Clicked: " + menu.name, Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return menuArrayList.size();
    }


    public class CardViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }
}
