package com.example.codefest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codefest.AdminShowStaffActivity;
import com.example.codefest.R;
import com.example.codefest.helper.NavHelper;
import com.example.codefest.model.UserModel;

import java.util.ArrayList;

public class UserModelAdapter extends RecyclerView.Adapter<UserModelAdapter.UserModelViewHolder> {

    Context context;
    ArrayList<UserModel> arrayList;

    public UserModelAdapter(Context context, ArrayList<UserModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public UserModelAdapter.UserModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.user_list_layout, parent, false);
        return new UserModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserModelAdapter.UserModelViewHolder holder, int position) {
        UserModel UserModel = arrayList.get(position);
        holder.firstName.setText(UserModel.firstName);
        holder.email.setText(UserModel.email);
        holder.details.setOnClickListener(v -> {
            NavHelper.navigate(context, AdminShowStaffActivity.class);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class UserModelViewHolder extends RecyclerView.ViewHolder {
        TextView firstName, email, details;
        public UserModelViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.firstName);
            email = itemView.findViewById(R.id.email);
            details = itemView.findViewById(R.id.details);
        }
    }
}
