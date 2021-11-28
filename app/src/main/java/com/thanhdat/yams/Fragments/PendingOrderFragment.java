package com.thanhdat.yams.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.thanhdat.yams.Activities.OrderDetailActivity;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.PendingOrder;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.PendingAdapter;

import java.util.ArrayList;


public class PendingOrderFragment extends Fragment{
    public PendingOrderFragment() {
        // Required empty public constructor
    }
    OnClickInterface clickInterface;
    ListView lvPending;
    ArrayList<PendingOrder> pendingOrders;
    PendingAdapter pendingAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending_order, container, false);
        lvPending = view.findViewById(R.id.lvPending);
        pendingAdapter = new PendingAdapter(getContext(),R.layout.pending_order_item,initData(), clickInterface);
        lvPending.setAdapter(pendingAdapter);
        clickInterface= abc -> startActivity(new Intent(getContext(), OrderDetailActivity.class));
        return view;
    }

    private ArrayList<PendingOrder> initData(){
        pendingOrders = new ArrayList<>();
        pendingOrders.add(new PendingOrder("Mango Cream Cake",R.drawable.img_mango_cake,"#000122",150000));
        pendingOrders.add(new PendingOrder("Chocolate Cream Cake",R.drawable.img_cake,"#000121",150000));
        pendingOrders.add(new PendingOrder("Cheese Tart",R.drawable.img_mango_cake,"#000124",150000));
        pendingOrders.add(new PendingOrder("Vanila CupCake",R.drawable.img_cake,"#000125",150000));
        pendingOrders.add(new PendingOrder("Fruit Cake",R.drawable.img_mango_cake,"#000126",100000));
        pendingOrders.add(new PendingOrder("StrawBerry Cake",R.drawable.img_cake,"#000127",150000));
        return pendingOrders;

    }

}