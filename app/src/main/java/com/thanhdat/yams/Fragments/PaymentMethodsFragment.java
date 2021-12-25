package com.thanhdat.yams.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.thanhdat.yams.Activities.CartActivity;
import com.thanhdat.yams.Activities.ChatActivity;
import com.thanhdat.yams.Activities.OrderActivity;
import com.thanhdat.yams.Activities.PaymentActivity;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.R;

public class PaymentMethodsFragment extends Fragment {
    RadioButton radMomo, radZaloPay, radCod;
    AppCompatButton btnConfirm;
    TextView txtChooseBank;
    ImageButton imbBack;
    OnClickInterface clickInterface;
    public static String pMethod;

    public PaymentMethodsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_method, container, false);

        //link views
        btnConfirm = view.findViewById(R.id.btnConfirmPaymentMethod);
        txtChooseBank = view.findViewById(R.id.txtChooseBank);
        imbBack = view.findViewById(R.id.imbBack);


        radMomo = view.findViewById(R.id.radMomo);
        radZaloPay = view.findViewById(R.id.radZaloPay);
        radCod = view.findViewById(R.id.radCod);


        addEvents();
        addEventBack();
        return view;
    }

    private void addEventBack() {
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickInterface = (OnClickInterface) getActivity();
                if(view.getId() == R.id.imbBack){
                    clickInterface.setClick(2);
                }
            }
        });
    }


    private void addEvents() {
        txtChooseBank.setOnClickListener(myClick);
        btnConfirm.setOnClickListener(myClick);
        Bundle bundle = getArguments();
        if(bundle != null){
            pMethod = bundle.getString("bank");
            txtChooseBank.setText(pMethod);
        }
        radCod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pMethod = "Thanh toán khi nhận hàng";
                    radMomo.setChecked(false);
                    radZaloPay.setChecked(false);
                }
            }
        });
        radMomo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pMethod = "MOMO";
                    radCod.setChecked(false);
                    radZaloPay.setChecked(false);
                }
            }
        });
        radZaloPay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pMethod = "Zalopay";
                    radMomo.setChecked(false);
                    radCod.setChecked(false);
                }
            }
        });
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            clickInterface = (OnClickInterface) getActivity();
            if(view.getId() == R.id.btnConfirmPaymentMethod){
                clickInterface.setClick(2);
            }
            else if (view.getId() == R.id.txtChooseBank) {
                clickInterface.setClick(1);
            }
        }

    };



}

