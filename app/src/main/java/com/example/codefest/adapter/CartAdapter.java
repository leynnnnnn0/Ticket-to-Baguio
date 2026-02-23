package com.example.codefest.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefest.helper.ImageHelper;
import com.example.codefest.model.Cart;
import com.example.codefest.model.Menu;
import com.example.codefest.databinding.CartListBinding;
import com.example.codefest.database.DatabaseHelper;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CardViewHolder> {
    Context context;
    ArrayList<Cart> cartArrayList;
    DatabaseHelper databaseHelper;

    public CartAdapter(Context context, ArrayList<Cart> cartArrayList) {
        this.context = context;
        this.cartArrayList = cartArrayList;
        this.databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public CartAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CartListBinding binding = CartListBinding.inflate(inflater, parent, false);
        return new CardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CardViewHolder holder, int position) {
        Cart cart = cartArrayList.get(position);
        Bitmap image_bitmap = ImageHelper.stringToBitmap(cart.image);

        holder.binding.imageView.setImageBitmap(image_bitmap);
        holder.binding.name.setText(String.valueOf(cart.name));
        holder.binding.price.setText(String.valueOf(cart.price));
        holder.binding.quantityText.setText(String.valueOf(cart.quantity));

        holder.binding.increaseButton.setOnClickListener(v -> {
            cart.quantity++;
            int quantity = cart.quantity;

            int updatedPrice = cart.price * quantity;

            holder.binding.quantityText.setText(String.valueOf(quantity));
            holder.binding.price.setText(String.valueOf(updatedPrice));
        });

        holder.binding.decreaseButton.setOnClickListener(v -> {
            if (cart.quantity > 1) {
                cart.quantity--;
                int quantity = cart.quantity;

                int updatedPrice = cart.price * quantity;

                holder.binding.quantityText.setText(String.valueOf(quantity));
                holder.binding.price.setText(String.valueOf(updatedPrice));

            }
        });
        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Clicked: " + cart.name, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }


    public class CardViewHolder extends RecyclerView.ViewHolder {
        CartListBinding binding;
        public CardViewHolder(CartListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
