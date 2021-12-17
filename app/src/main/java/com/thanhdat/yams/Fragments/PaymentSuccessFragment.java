package com.thanhdat.yams.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.thanhdat.yams.Activities.MainActivity;
import com.thanhdat.yams.Activities.OrderActivity;
import com.thanhdat.yams.Activities.OrderStatusActivity;
import com.thanhdat.yams.R;

public class PaymentSuccessFragment extends Fragment {
    AppCompatButton btnSeeOrder;
    ImageView imvHome;
    TextView tvDetailStatus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_success, container, false);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //link views
        btnSeeOrder = view.findViewById(R.id.btnSeeOrder);
        imvHome= view.findViewById(R.id.imvGoHome);
        tvDetailStatus = view.findViewById(R.id.tvDetailOrderStatus);

        //add events
        addEvents();

        return view;
    }

    private void addEvents() {
        String orderCode = OrderActivity.orderCode;
        String s= "Mã đơn hàng của bạn là: #"+ orderCode +" \nYams sẽ sớm giao hàng đến bạn";
        tvDetailStatus.setText(s);
        btnSeeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btnSeeOrder) {
                    startActivity(new Intent(getContext(), OrderStatusActivity.class));
                }
            }
        });
        imvHome.setOnClickListener(v -> startActivity(new Intent(getContext(), MainActivity.class)));

    }
}
