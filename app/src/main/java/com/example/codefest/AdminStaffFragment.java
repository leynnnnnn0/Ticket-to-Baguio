package com.example.codefest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.codefest.adapter.UserModelAdapter;
import com.example.codefest.helper.NavHelper;
import com.example.codefest.model.UserModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminStaffFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminStaffFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminStaffFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminStaffFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminStaffFragment newInstance(String param1, String param2) {
        AdminStaffFragment fragment = new AdminStaffFragment();
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
        View view = inflater.inflate(R.layout.fragment_admin_staff, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        ArrayList<UserModel> orders = new ArrayList<>();
        orders.add(new UserModel( "johndoe@gmail.com", "John"));
        orders.add(new UserModel( "christineann@gmail.com", "Christine"));
        UserModelAdapter UserModelAdapter = new UserModelAdapter(getContext(), orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(UserModelAdapter);

        ImageButton addNew = view.findViewById(R.id.addNew);
        addNew.setOnClickListener(v -> NavHelper.navigate(view.getContext(), AdminCreateStaffActivity.class));
        
        return view;
    }
}