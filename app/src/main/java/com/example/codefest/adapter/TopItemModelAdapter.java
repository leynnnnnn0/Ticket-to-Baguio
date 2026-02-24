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
import com.example.codefest.model.TopItemModel;

import java.util.ArrayList;

public class TopItemModelAdapter extends RecyclerView.Adapter<TopItemModelAdapter.TopItemModelViewHolder> {

    Context context;
    ArrayList<TopItemModel> arrayList;

    public TopItemModelAdapter(Context context, ArrayList<TopItemModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public TopItemModelAdapter.TopItemModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.sales_list_container, parent, false);
        return new TopItemModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopItemModelAdapter.TopItemModelViewHolder holder, int position) {
        TopItemModel TopItemModel = arrayList.get(position);
        holder.name.setText(TopItemModel.name);
        holder.count.setText(TopItemModel.count);
        holder.total.setText(TopItemModel.total);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class TopItemModelViewHolder extends RecyclerView.ViewHolder {
        TextView name, count,  total;
        public TopItemModelViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            count = itemView.findViewById(R.id.count);
            total = itemView.findViewById(R.id.total);
        }
    }
}
