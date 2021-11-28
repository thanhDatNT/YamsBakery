package com.thanhdat.yams.Fragments;

import android.content.Intent;
import android.os.Bundle;

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


public class PendingOrderFragment extends Fragment {

    OnClickInterface onClickInterface;

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

        goToOrderDetail();

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

    private void goToOrderDetail() {
        lvPending.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getContext(),OrderDetailActivity.class));
            }
        });
    }
}