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
import com.example.codefest.model.MenuModel;

import java.util.ArrayList;

public class MenuModelAdapter extends RecyclerView.Adapter<MenuModelAdapter.MenuModelViewHolder> {

    Context context;
    ArrayList<MenuModel> arrayList;

    public MenuModelAdapter(Context context, ArrayList<MenuModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MenuModelAdapter.MenuModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.menu_list_layout, parent, false);
        return new MenuModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuModelAdapter.MenuModelViewHolder holder, int position) {
        MenuModel MenuModel = arrayList.get(position);
        holder.name.setText(MenuModel.name);
        holder.price.setText(MenuModel.price);
        holder.menuListContainer.setOnClickListener(v -> {
            NavHelper.navigate(context, AdminShowMenuActivity.class);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MenuModelViewHolder extends RecyclerView.ViewHolder {
        TextView name, price;
        CardView menuListContainer;
        public MenuModelViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            menuListContainer = itemView.findViewById(R.id.menuListContainer);
        }
    }
}
