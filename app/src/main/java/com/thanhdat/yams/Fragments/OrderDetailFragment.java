package com.thanhdat.yams.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.thanhdat.yams.Models.PendingOrder;
import com.thanhdat.yams.R;


public class OrderDetailFragment extends Fragment {

    public static final String TAG = OrderDetailFragment.class.getName();

    private TextView txtOrderDetailName, txtOrderDetailPrice;
    private ImageButton imbBackToOrderStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);

        txtOrderDetailName = view.findViewById(R.id.txtOrderDetailName);
        txtOrderDetailPrice = view.findViewById(R.id.txtOrderDetailPrice);
        imbBackToOrderStatus = view.findViewById(R.id.imbBackToOrderStatus);

        Bundle bundleReceive = getArguments();
        if(bundleReceive != null){
            PendingOrder pendingOrder = (PendingOrder) bundleReceive.get("item_detail");
            if(pendingOrder != null){
                txtOrderDetailName.setText(pendingOrder.getOrderName());
            }
        }

        imbBackToOrderStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getFragmentManager() != null){
                    getFragmentManager().popBackStack();
                }
            }
        });

        return view;
    }

}

