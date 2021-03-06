package com.thanhdat.yams.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thanhdat.yams.Models.PreviousOrder;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapters.PreviousAdapter;

import java.util.ArrayList;
import java.util.List;


public class PreviousOrderFragment extends Fragment {

    RecyclerView rcvPrevious;
    PreviousAdapter previousAdapter;
    public PreviousOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_previous_order, container, false);
        rcvPrevious = view.findViewById(R.id.rcvPrevious);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcvPrevious.setLayoutManager(manager);
        rcvPrevious.setHasFixedSize(true);
        rcvPrevious.setItemAnimator(new DefaultItemAnimator());

        previousAdapter = new PreviousAdapter(getContext(),initData());
        rcvPrevious.setAdapter(previousAdapter);
        return view;
    }

    private List<PreviousOrder> initData() {
        List<PreviousOrder> previousOrders = new ArrayList<>();
        previousOrders.add(new PreviousOrder("Mango Cream cake", R.drawable.img_mango_cake, "M-20cm", 180000,2));
        previousOrders.add(new PreviousOrder("Cream cake", R.drawable.img_cake, "S-16cm", 170000,1));
        previousOrders.add(new PreviousOrder("Birthday cake", R.drawable.img_bdcake, "L-24cm", 160000,3));
        previousOrders.add(new PreviousOrder("Matcha macaroon", R.drawable.img_matcha_maracon, "M-20cm", 170000,2));
        return previousOrders;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(previousAdapter !=null){
            previousAdapter.release();
        }
    }
}