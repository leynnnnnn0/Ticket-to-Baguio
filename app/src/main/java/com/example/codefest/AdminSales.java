package com.example.codefest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.codefest.adapter.OrderItemModelAdapter;
import com.example.codefest.adapter.PerItemModelAdapter;
import com.example.codefest.adapter.TopItemModelAdapter;
import com.example.codefest.model.OrderItemModel;
import com.example.codefest.model.PerItemModel;
import com.example.codefest.model.TopItemModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminSales#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminSales extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminSales() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminSales.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminSales newInstance(String param1, String param2) {
        AdminSales fragment = new AdminSales();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_sales, container, false);
        RecyclerView topRecyclerView = view.findViewById(R.id.topRecyclerView);

        ArrayList<TopItemModel> orders = new ArrayList<>();
        orders.add(new TopItemModel("x2", "Chicken Adobo", "$500.50"));
        orders.add(new TopItemModel("x1", "Pork Sisig", "$100.00"));
        TopItemModelAdapter topAdapter = new TopItemModelAdapter(view.getContext(), orders);
        topRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        topRecyclerView.setAdapter(topAdapter);

        RecyclerView perItemRecyclerView = view.findViewById(R.id.perItemRecyclerView);

        ArrayList<PerItemModel> orders1 = new ArrayList<>();
        orders1.add(new PerItemModel("x2", "Chicken Adobo", "$500.50"));
        orders1.add(new PerItemModel("x1", "Pork Sisig", "$100.00"));
        orders1.add(new PerItemModel("x1", "Pork Sisig", "$100.00"));
        PerItemModelAdapter perItemAdapter = new PerItemModelAdapter(view.getContext(), orders1);
        perItemRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        perItemRecyclerView.setAdapter(perItemAdapter);


        return view;
    }
}