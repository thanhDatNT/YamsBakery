package com.thanhdat.yams.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.thanhdat.yams.Activities.OrderActivity;
import com.thanhdat.yams.R;

public class ChooseBankFragment extends Fragment {


    Button btnConfirm;
    private LinearLayout lnBIDV, lnOCB, lnVietcombank, lnVietinbank, lnSacombank;
    Toolbar toolbarChooseBank;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_bank,container, false);

        //link views
        toolbarChooseBank = view.findViewById(R.id.toolbarChooseBank);
        btnConfirm = view.findViewById(R.id.btnConfirm);
        lnBIDV= view.findViewById(R.id.lnBIDV);
        lnSacombank= view.findViewById(R.id.lnSacombank);
        lnOCB = view.findViewById(R.id.lnOCB);
        lnVietcombank = view.findViewById(R.id.lnVietcombank);
        lnVietinbank = view.findViewById(R.id.lnVietinbank);

        addEventFunction();
        return view;
    }

    private void addEventFunction() {
        toolbarChooseBank.setOnClickListener(myClick);
        btnConfirm.setOnClickListener(myClick);
        lnBIDV.setOnClickListener(myClick);
        lnOCB.setOnClickListener(myClick);
        lnSacombank.setOnClickListener(myClick);
        lnVietinbank.setOnClickListener(myClick);
        lnVietcombank.setOnClickListener(myClick);
    }

        View.OnClickListener myClick = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.toolbarChooseBank) {

                }

                //start function activity

            }
    };
}
