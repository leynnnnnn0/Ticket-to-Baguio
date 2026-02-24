package com.example.codefest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.codefest.adapter.MenuModelAdapter;
import com.example.codefest.helper.NavHelper;
import com.example.codefest.model.MenuModel;
import com.example.codefest.model.MenuModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminMenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminMenuFragment newInstance(String param1, String param2) {
        AdminMenuFragment fragment = new AdminMenuFragment();
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
      View view =  inflater.inflate(R.layout.fragment_admin_menu, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        ArrayList<MenuModel> orders = new ArrayList<>();
        orders.add(new MenuModel("Chicken Adobo", "150.40"));
        orders.add(new MenuModel("Pork Sisig", "300.00"));
        MenuModelAdapter MenuModelAdapter = new MenuModelAdapter(getContext(), orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(MenuModelAdapter);

        ImageButton imageButton = view.findViewById(R.id.goToAddMenu);

        imageButton.setOnClickListener(v -> NavHelper.navigate(view.getContext(), AdminCreateMenuActivity.class));

        return view;
    }
}