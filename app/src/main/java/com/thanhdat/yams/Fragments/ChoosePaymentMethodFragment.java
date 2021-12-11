package com.thanhdat.yams.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.thanhdat.yams.Activities.PaymentActivity;
import com.thanhdat.yams.R;

public class ChoosePaymentMethodFragment extends Fragment {
    RadioButton radMomo, radZaloPay, radCod;
    AppCompatButton btnConfirm;
    Toolbar toolbarPaymentMethod;
    TextView txtSeeMore;


    public ChoosePaymentMethodFragment(int contentLayoutId, RadioButton radMomo, RadioButton radZaloPay, RadioButton radCod, AppCompatButton btnConfirm, Toolbar toolbarPaymentMethod, TextView txtSeeMore) {
        super(contentLayoutId);
        this.radMomo = radMomo;
        this.radZaloPay = radZaloPay;
        this.radCod = radCod;
        this.btnConfirm = btnConfirm;
        this.toolbarPaymentMethod = toolbarPaymentMethod;
        this.txtSeeMore = txtSeeMore;
    }

    public ChoosePaymentMethodFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_method, container, false);

        //link views
        btnConfirm = view.findViewById(R.id.btnConfirm);
        txtSeeMore = view.findViewById(R.id.txtSeeMore);
        toolbarPaymentMethod = view.findViewById(R.id.toolbarPaymentMethod);
        radCod = view.findViewById(R.id.radCod);
        radMomo = view.findViewById(R.id.radMomo);
        radZaloPay = view.findViewById(R.id.radZaloPay);

        //add events function
        addEvents();

        return view;
    }

    private void addEvents() {
        toolbarPaymentMethod.setOnClickListener(myClick);
        txtSeeMore.setOnClickListener(myClick);
        btnConfirm.setOnClickListener(myClick);
        radCod.setOnClickListener(myClick);
        radMomo.setOnClickListener(myClick);
        radZaloPay.setOnClickListener(myClick);

    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.toolbarPaymentMethod||view.getId() == R.id.btnConfirm ) {
                startActivity(new Intent(getContext(), PaymentActivity.class));
            }

            //fragment to fragment
            if (view.getId() == R.id.txtSeeMore) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.layoutContainerPaymentMethod, new ChooseBankFragment());
                fragmentTransaction.commit();


            }
        }

    };

}

