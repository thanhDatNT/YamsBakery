package com.thanhdat.yams.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thanhdat.yams.Models.PendingOrder;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapter.PendingAdapter2;

import java.util.ArrayList;
import java.util.List;

public class PendingFragment2 extends Fragment {

    RecyclerView rcvPending;
    PendingAdapter2 pendingAdapter2;


    public PendingFragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending_order2, container, false);
        rcvPending = view.findViewById(R.id.rcvPending);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcvPending.setLayoutManager(manager);
        rcvPending.setHasFixedSize(true);
        rcvPending.setItemAnimator(new DefaultItemAnimator());

        pendingAdapter2 = new PendingAdapter2(getContext(),initData());
        rcvPending.setAdapter(pendingAdapter2);

        return view;
    }

    private List<PendingOrder> initData() {
        List<PendingOrder> pendingOrders = new ArrayList<>();

        pendingOrders.add(new PendingOrder("Mango Cream Cake",R.drawable.img_mango_cake,"#000122",150000));
        pendingOrders.add(new PendingOrder("Chocolate Cream Cake",R.drawable.img_cake,"#000121",150000));
        pendingOrders.add(new PendingOrder("Cheese Tart",R.drawable.img_mango_cake,"#000124",150000));
        pendingOrders.add(new PendingOrder("Vanila CupCake",R.drawable.img_cake,"#000125",150000));
        pendingOrders.add(new PendingOrder("Fruit Cake",R.drawable.img_mango_cake,"#000126",100000));
        pendingOrders.add(new PendingOrder("StrawBerry Cake",R.drawable.img_cake,"#000127",150000));

        return pendingOrders;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(pendingAdapter2 != null){
            pendingAdapter2.release();
        }
    }
}