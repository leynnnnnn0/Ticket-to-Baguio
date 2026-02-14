package com.example.codefest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefest.R;
import com.example.codefest.model.Order;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    Context context;
    ArrayList<Order> orderArrayList;

    public OrderAdapter(Context context, ArrayList<Order> orderArrayList) {
        this.context = context;
        this.orderArrayList = orderArrayList;
    }

    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.order_list, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {
        Order order = orderArrayList.get(position);
        holder.orderNumber.setText(order.orderNumber);
        String created = "Created At: " + order.createdAt;
        holder.createdAt.setText(created);
        holder.status.setText(order.status);
        holder.price.setText(order.price);
    }

    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderNumber, createdAt, price;
        Button status;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderNumber = itemView.findViewById(R.id.orderNumber);
            createdAt =  itemView.findViewById(R.id.createdAt);
            status = itemView.findViewById(R.id.status);
            price = itemView.findViewById(R.id.price);
        }
    }
}
