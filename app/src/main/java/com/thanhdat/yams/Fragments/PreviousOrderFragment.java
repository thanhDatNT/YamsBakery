package com.thanhdat.yams.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.thanhdat.yams.Models.PreviousOrder;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.PreviousAdapter;

import java.util.ArrayList;


public class PreviousOrderFragment extends Fragment {



    public PreviousOrderFragment() {
        // Required empty public constructor
    }

    ListView lvPrevious;
    ArrayList<PreviousOrder> previousOrders;
    PreviousAdapter previousAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_previous_order, container, false);
        lvPrevious = view.findViewById(R.id.lvPrevious);
        previousAdapter = new PreviousAdapter(getContext(),R.layout.previous_order_item,initData());
        lvPrevious.setAdapter(previousAdapter);
        return view;
    }
    private ArrayList<PreviousOrder> initData() {
        previousOrders = new ArrayList<>();
        previousOrders.add(new PreviousOrder("Mango Cream cake", R.drawable.img_mango_cake, "Vị xoài và kem tươi", 150000));
        previousOrders.add(new PreviousOrder("Peach cake", R.drawable.img_cake, "Vị xoài và kem tươi", 150000));
        previousOrders.add(new PreviousOrder("Mango Cream cake", R.drawable.img_mango_cake, "Vị xoài và kem tươi", 150000));
        previousOrders.add(new PreviousOrder("Peach cake", R.drawable.img_cake, "Vị xoài và kem tươi", 150000));

        return previousOrders;
    }

}