package com.thanhdat.yams.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.thanhdat.yams.Activities.Payment;
import com.thanhdat.yams.R;

public class VoucherFragment extends Fragment {
    LinearLayout lnVoucher200k, lnVoucher500k, lnFreeShip200k, lnFreeShip500k;
    EditText edtVoucher;
    Button btnApply;
    ImageButton btnBackToPayment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupons, container, false);


        //link views

        btnBackToPayment = view.findViewById(R.id.btnBackToPayment);
        btnApply = view.findViewById(R.id.btnApply);

        lnVoucher200k = view.findViewById(R.id.lnVoucher200k);
        lnVoucher500k = view.findViewById(R.id.lnVoucher500k);
        lnFreeShip200k = view.findViewById(R.id.lnFreeShip200k);
        lnFreeShip500k = view.findViewById(R.id.lnFreeShip500k);

        edtVoucher = view.findViewById(R.id.edtVoucher);

        //add events function
        addEvents();
        return view;
    }

    private void addEvents() {

        btnBackToPayment.setOnClickListener(myClick);
        btnApply.setOnClickListener(myClick);
        lnVoucher200k.setOnClickListener(myClick);
        lnVoucher500k.setOnClickListener(myClick);
        lnFreeShip200k.setOnClickListener(myClick);
        lnFreeShip500k.setOnClickListener(myClick);
    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btnBackToPayment) {
                startActivity(new Intent(getContext(), Payment.class));
            }
        }
    };
}
