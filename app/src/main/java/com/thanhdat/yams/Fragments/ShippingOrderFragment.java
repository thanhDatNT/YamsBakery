package com.thanhdat.yams.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.thanhdat.yams.Activities.OrderDetailActivity;
import com.thanhdat.yams.R;


public class ShippingOrderFragment extends Fragment {

    public ShippingOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shipping_order, container, false);

        return view;
    }
}