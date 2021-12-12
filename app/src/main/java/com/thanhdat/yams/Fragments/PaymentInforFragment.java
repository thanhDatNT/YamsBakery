package com.thanhdat.yams.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.thanhdat.yams.Activities.PaymentActivity;
import com.thanhdat.yams.R;

public class PaymentInforFragment extends Fragment {
    ImageButton edtName, edtPhone;
    Toolbar toolbarInfor;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_infor, container, false);

        toolbarInfor= view.findViewById(R.id.toolbarInfor);


        addEvents();
        return view;
    }


    private void addEvents() {
        toolbarInfor.setOnClickListener(myClick);
        }
    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.toolbarInfor) {
                startActivity(new Intent(getContext(), PaymentActivity.class));
            }
        }
    };



}
