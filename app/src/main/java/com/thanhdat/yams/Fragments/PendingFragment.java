package com.thanhdat.yams.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thanhdat.yams.Databases.OrderDatabase;
import com.thanhdat.yams.Models.PendingOrder;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapters.PendingAdapter;

import java.util.ArrayList;
import java.util.List;

public class PendingFragment extends Fragment {
    TextView tvStatus;
    RecyclerView rcvPending;
    PendingAdapter pendingAdapter;
    OrderDatabase orderDatabase;

    public PendingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending_order2, container, false);
        rcvPending = view.findViewById(R.id.rcvPending);
        tvStatus = view.findViewById(R.id.tvEmptyPendingOrder);

        orderDatabase= new OrderDatabase(getContext());

        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rcvPending.setLayoutManager(manager);
        rcvPending.setHasFixedSize(true);
        rcvPending.setItemAnimator(new DefaultItemAnimator());

        loadData();
        return view;
    }

    private void loadData() {
        Cursor cursor= orderDatabase.getData("SELECT * FROM "+ orderDatabase.TABLE_NAME);
        int count = 0;
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                count = cursor.getCount();
            }
        }
        cursor.close();
        if(count > 0){
            tvStatus.setVisibility(View.GONE);
            pendingAdapter = new PendingAdapter(getContext(), initData());
            rcvPending.setAdapter(pendingAdapter);
        }
    }

    private List<PendingOrder> initData() {
        List<PendingOrder> pendingOrders = new ArrayList<>();
        Cursor cursor= orderDatabase.getData("SELECT * FROM "+ orderDatabase.TABLE_NAME);
        while (cursor.moveToNext()){
            pendingOrders.add(new PendingOrder(cursor.getInt(0) , cursor.getString(2), cursor.getString(4),
                    cursor.getString(1), cursor.getDouble(7)));
        }
        return pendingOrders;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(pendingAdapter != null){
            pendingAdapter.release();
        }
    }
}