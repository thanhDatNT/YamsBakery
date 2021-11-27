package com.thanhdat.yams.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.thanhdat.yams.Activities.FunctionProfileActivity;
import com.thanhdat.yams.Activities.LoginActivity;
import com.thanhdat.yams.Activities.OrderStatusActivity;
import com.thanhdat.yams.Activities.Payment;
import com.thanhdat.yams.Activities.PaymentMethod;
import com.thanhdat.yams.Activities.RegisterActivity;
import com.thanhdat.yams.R;

public class ChoosePaymentMethodFragment extends Fragment {
    RadioButton radMomo, radZaloPay, radCod;
    ImageButton btnOpenChooseBank, btnBackToPayment;
    Button btnConfirm;


    public ChoosePaymentMethodFragment(int contentLayoutId, RadioButton radMomo, RadioButton radZaloPay, RadioButton radCod, ImageButton btnOpenChooseBank, ImageButton btnBackToPayment, Button btnConfirm) {
        super(contentLayoutId);
        this.radMomo = radMomo;
        this.radZaloPay = radZaloPay;
        this.radCod = radCod;
        this.btnOpenChooseBank = btnOpenChooseBank;
        this.btnBackToPayment = btnBackToPayment;
        this.btnConfirm = btnConfirm;
    }

    public ChoosePaymentMethodFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_method, container, false);

        //link views
        btnBackToPayment = view.findViewById(R.id.btnBackToPayment);
        btnOpenChooseBank = view.findViewById(R.id.btnOpenChooseBank);
        btnConfirm = view.findViewById(R.id.btnConfirm);
        radCod = view.findViewById(R.id.radCod);
        radMomo = view.findViewById(R.id.radMomo);
        radZaloPay = view.findViewById(R.id.radZaloPay);

        //add events function
        addEvents();

        return view;
    }

    private void addEvents() {
        btnBackToPayment.setOnClickListener(myClick);
        btnOpenChooseBank.setOnClickListener(myClick);
        btnConfirm.setOnClickListener(myClick);
        radCod.setOnClickListener(myClick);
        radMomo.setOnClickListener(myClick);
        radZaloPay.setOnClickListener(myClick);

    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btnBackToPayment) {
                startActivity(new Intent(getContext(), Payment.class));
            }

            //start function activity
            if (view.getId() == R.id.btnOpenChooseBank) {

                FragmentManager fm = getFragmentManager();
                ChooseBankFragment fragment= new ChooseBankFragment();
                fm.beginTransaction().replace(R.id.layoutContainerPaymentMethod, fragment).commit();

                btnOpenChooseBank.setVisibility(View.GONE);


            }
        }

    };

}

