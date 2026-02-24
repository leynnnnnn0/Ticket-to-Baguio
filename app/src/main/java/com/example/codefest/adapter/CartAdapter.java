package com.example.codefest.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefest.ShowMenuDetailsActivity;
import com.example.codefest.helper.ImageHelper;
import com.example.codefest.model.Cart;
import com.example.codefest.databinding.CartListBinding;
import com.example.codefest.database.DatabaseHelper;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CardViewHolder> {

    Context context;
    ArrayList<Cart> cartArrayList;
    DatabaseHelper databaseHelper;
    OnQuantityChangeListener listener;

    // Interface
    public interface OnQuantityChangeListener {
        void onQuantityChanged();
    }

    public CartAdapter(Context context,
                       ArrayList<Cart> cartArrayList,
                       OnQuantityChangeListener listener) {
        this.context = context;
        this.cartArrayList = cartArrayList;
        this.listener = listener;
        this.databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CartListBinding binding = CartListBinding.inflate(inflater, parent, false);
        return new CardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {

        Cart cart = cartArrayList.get(holder.getBindingAdapterPosition());

        Bitmap imageBitmap = ImageHelper.stringToBitmap(cart.image);

        holder.binding.imageView.setImageBitmap(imageBitmap);
        holder.binding.name.setText(cart.name);
        holder.binding.quantityText.setText(String.valueOf(cart.quantity));

        int totalItemPrice = cart.price * cart.quantity;
        holder.binding.price.setText(String.valueOf(totalItemPrice));

        holder.binding.increaseButton.setOnClickListener(v -> {
            cart.quantity++;
            notifyItemChanged(position);
            listener.onQuantityChanged();
        });

        holder.binding.decreaseButton.setOnClickListener(v -> {
            if (cart.quantity > 1) {
                cart.quantity--;
                notifyItemChanged(position);
                listener.onQuantityChanged();
            }
        });

        holder.itemView.setOnClickListener(v ->{
            Intent intent = new Intent(context, ShowMenuDetailsActivity.class);
            intent.putExtra("MENU_ID", cart.id);
            intent.putExtra("TYPE", "cart");
            context.startActivity(intent);

            Toast.makeText(context, "Clicked: " + cart.name, Toast.LENGTH_SHORT).show();

        });

        holder.binding.removeButton.setOnClickListener(v -> {

            databaseHelper.removeItemInCart(cart.id);

            cartArrayList.remove(holder.getAdapterPosition());
            notifyItemRemoved(holder.getAdapterPosition());
            listener.onQuantityChanged();

            Toast.makeText(context,
                    cart.name + " has been removed from Cart",
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CartListBinding binding;

        public CardViewHolder(CartListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}