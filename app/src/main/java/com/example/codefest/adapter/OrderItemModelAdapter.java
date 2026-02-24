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
import com.example.codefest.R;
import com.example.codefest.helper.NavHelper;
import com.example.codefest.model.OrderItemModel;

import java.util.ArrayList;

public class OrderItemModelAdapter extends RecyclerView.Adapter<OrderItemModelAdapter.OrderItemModelViewHolder> {

    Context context;
    ArrayList<OrderItemModel> arrayList;

    public OrderItemModelAdapter(Context context, ArrayList<OrderItemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public OrderItemModelAdapter.OrderItemModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.order_item_layout, parent, false);
        return new OrderItemModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemModelAdapter.OrderItemModelViewHolder holder, int position) {
        OrderItemModel OrderItemModel = arrayList.get(position);
        holder.name.setText(OrderItemModel.name);
        holder.count.setText(OrderItemModel.count);
        holder.total.setText(OrderItemModel.total);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class OrderItemModelViewHolder extends RecyclerView.ViewHolder {
        TextView name, count, total;
        CardView box;
        public OrderItemModelViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            count = itemView.findViewById(R.id.count);
            total = itemView.findViewById(R.id.total);
            box= itemView.findViewById(R.id. box);
        }
    }
}
