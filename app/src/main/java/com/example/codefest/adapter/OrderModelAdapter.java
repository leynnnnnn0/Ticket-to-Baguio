package com.example.codefest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefest.AdminShowMenuActivity;
import com.example.codefest.AdminShowOrderActivity;
import com.example.codefest.R;
import com.example.codefest.helper.NavHelper;
import com.example.codefest.model.OrderModel;

import java.util.ArrayList;

public class OrderModelAdapter extends RecyclerView.Adapter<OrderModelAdapter.OrderModelViewHolder> {

    Context context;
    ArrayList<OrderModel> arrayList;

    public OrderModelAdapter(Context context, ArrayList<OrderModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public OrderModelAdapter.OrderModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.orders_list_layout, parent, false);
        return new OrderModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderModelAdapter.OrderModelViewHolder holder, int position) {
        OrderModel orderModel = arrayList.get(position);
        holder.orderNumber.setText(orderModel.orderNumber);
        holder.orderStatus.setText(orderModel.orderStatus);
        holder.ordersListContainer.setOnClickListener(v -> NavHelper.navigate(context, AdminShowOrderActivity.class));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class OrderModelViewHolder extends RecyclerView.ViewHolder {
        TextView orderNumber, orderStatus;
        CardView ordersListContainer;
        public OrderModelViewHolder(@NonNull View itemView) {
            super(itemView);

            orderNumber = itemView.findViewById(R.id.orderNumber);
            orderStatus = itemView.findViewById(R.id.orderStatus);
            ordersListContainer = itemView.findViewById(R.id.orderListContainer);
        }
    }
}
