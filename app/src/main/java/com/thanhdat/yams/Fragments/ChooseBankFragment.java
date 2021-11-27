package com.thanhdat.yams.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.thanhdat.yams.Activities.FunctionProfileActivity;
import com.thanhdat.yams.Activities.OrderStatusActivity;
import com.thanhdat.yams.R;

public class ChooseBankFragment extends Fragment {

    ImageButton btnBackToPaymentMethod;
    Button btnConfirm;
    private LinearLayout lnBIDV, lnOCB, lnVietcombank, lnVietinbank, lnSacombank;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_bank,container, false);

        //link views
        btnBackToPaymentMethod = view.findViewById(R.id.btnBackToPaymentMethod);
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
        btnBackToPaymentMethod.setOnClickListener(myClick);
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
            if(view.getId() == R.id.btnBackToPaymentMethod) {
                startActivity(new Intent(getContext(), ChoosePaymentMethodFragment.class));

            }
            //start function activity

        }
    };
}
