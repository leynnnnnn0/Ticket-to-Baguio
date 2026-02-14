package com.example.codefest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefest.R;
import com.example.codefest.model.OrderItem;

import java.util.ArrayList;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder> {
    Context context;
    ArrayList<OrderItem> orderItemArrayList;

    public OrderItemAdapter(Context context, ArrayList<OrderItem> orderItemArrayList) {
        this.context = context;
        this.orderItemArrayList = orderItemArrayList;
    }

    @NonNull
    @Override
    public OrderItemAdapter.OrderItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.order_details_item, parent, false);
        return new OrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemAdapter.OrderItemViewHolder holder, int position) {
        OrderItem orderItem = orderItemArrayList.get(position);
        holder.quantity.setText(orderItem.quantity);
        holder.name.setText(orderItem.name);
        holder.price.setText(orderItem.price);
    }

    @Override
    public int getItemCount() {
        return orderItemArrayList.size();
    }

    public class OrderItemViewHolder extends RecyclerView.ViewHolder {
        TextView quantity, name, price;
        public OrderItemViewHolder(@NonNull View itemView) {
            super(itemView);
            quantity = itemView.findViewById(R.id.quantity);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);

        }
    }
}
