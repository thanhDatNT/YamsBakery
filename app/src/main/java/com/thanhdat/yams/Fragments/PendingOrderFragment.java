package com.thanhdat.yams.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thanhdat.yams.Models.PendingOrder;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.PendingAdapter;

import java.util.ArrayList;


public class PendingOrderFragment extends Fragment {



    public PendingOrderFragment() {
        // Required empty public constructor
    }
    ListView lvPending;
    ArrayList<PendingOrder> pendingOrders;
    PendingAdapter pendingAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending_order, container, false);
        lvPending = view.findViewById(R.id.lvPending);
        pendingAdapter = new PendingAdapter(getContext(),R.layout.pending_order_item,initData());
        lvPending.setAdapter(pendingAdapter);
        return view;
    }
    private ArrayList<PendingOrder> initData(){
        pendingOrders = new ArrayList<>();
        pendingOrders.add(new PendingOrder("Mango Cream Cake",R.drawable.cake,"#000122",150000));
        pendingOrders.add(new PendingOrder("Chocolate Cream Cake",R.drawable.cake1,"#000121",80000));
        pendingOrders.add(new PendingOrder("Cheese Tart",R.drawable.cake2,"#000124",50000));
        pendingOrders.add(new PendingOrder("Vanila CupCake",R.drawable.cake3,"#000125",20000));
        pendingOrders.add(new PendingOrder("Fruit Cake",R.drawable.cake4,"#000126",100000));
        pendingOrders.add(new PendingOrder("StrawBerry Cake",R.drawable.cake5,"#000127",50000));
        return pendingOrders;

    }
}