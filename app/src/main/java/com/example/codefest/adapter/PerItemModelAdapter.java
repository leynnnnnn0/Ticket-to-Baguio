package com.example.codefest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefest.R;
import com.example.codefest.model.PerItemModel;

import java.util.ArrayList;

public class PerItemModelAdapter extends RecyclerView.Adapter<PerItemModelAdapter.PerItemModelViewHolder> {

    Context context;
    ArrayList<PerItemModel> arrayList;

    public PerItemModelAdapter(Context context, ArrayList<PerItemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public PerItemModelAdapter.PerItemModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.sales_list_container, parent, false);
        return new PerItemModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PerItemModelAdapter.PerItemModelViewHolder holder, int position) {
        PerItemModel PerItemModel = arrayList.get(position);
        holder.name.setText(PerItemModel.name);
        holder.count.setText(PerItemModel.count);
        holder.total.setText(PerItemModel.total);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class PerItemModelViewHolder extends RecyclerView.ViewHolder {
        TextView name, count,  total;
        public PerItemModelViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            count = itemView.findViewById(R.id.count);
            total = itemView.findViewById(R.id.total);
        }
    }
}
