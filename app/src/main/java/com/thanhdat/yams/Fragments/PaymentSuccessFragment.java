package com.thanhdat.yams.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.thanhdat.yams.Activities.OrderStatusActivity;
import com.thanhdat.yams.Activities.PaymentActivity;
import com.thanhdat.yams.R;

public class PaymentSuccessFragment extends Fragment {
    AppCompatButton btnSeeOrder;
    Toolbar toolbarPaymentSuccess;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_success, container, false);

        //link views
        btnSeeOrder = view.findViewById(R.id.btnSeeOrder);
        toolbarPaymentSuccess = view.findViewById(R.id.toolbarPaymentSuccess);
        //add events
        addEvents();

return view;
    }

    private void addEvents() {
        btnSeeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btnSeeOrder) {
                    startActivity(new Intent(getContext(), OrderStatusActivity.class));
                }
            }
        });

        toolbarPaymentSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.toolbarPaymentMethod) {
                    startActivity(new Intent(getContext(), PaymentActivity.class));
                }
            }
        });
    }
}
