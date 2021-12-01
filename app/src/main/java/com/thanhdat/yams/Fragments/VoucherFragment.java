package com.thanhdat.yams.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.thanhdat.yams.Activities.PaymentActivity;
import com.thanhdat.yams.Models.Voucher;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.VoucherAdapter;

import java.util.ArrayList;

public class VoucherFragment extends Fragment {
    ListView lvVoucher;
    ArrayList<Voucher> vouchers;
    VoucherAdapter adapter;
    ImageButton btnBackToPayment;


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupons, container, false);

        lvVoucher = view.findViewById(R.id.lvVoucher);
        adapter = new VoucherAdapter((Activity) getContext(), R.layout.item_voucher, initData());
        lvVoucher.setAdapter(adapter);

        btnBackToPayment = view.findViewById(R.id.btnBackToPayment);

        addEvents();
        return view;
    }

    private void addEvents() {
        btnBackToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btnBackToPayment) {
                    startActivity(new Intent(getContext(), PaymentActivity.class));
                }
            }
        });
    }

    private ArrayList<Voucher> initData() {
        vouchers = new ArrayList<Voucher>();
        vouchers.add(new Voucher(R.drawable.ic_voucher, "Giảm 10k cho đơn hàng từ 200k", "1/12/2021"));
        vouchers.add(new Voucher(R.drawable.ic_voucher, "Giảm 20k cho đơn hàng từ 500k", "1/12/2021"));
        vouchers.add(new Voucher(R.drawable.ic_voucher, "Giảm 50k cho đơn hàng từ 500k", "1/12/2021"));
        vouchers.add(new Voucher(R.drawable.ic_shipping_active, "Miễn phí vận chuyển cho đơn hàng từ 200k", "01/01/2022"));
        vouchers.add(new Voucher(R.drawable.ic_shipping_active, "Miễn phí vận chuyển cho đơn hàng từ 200k", "01/01/2022"));
        vouchers.add(new Voucher(R.drawable.ic_shipping_active, "Miễn phí vận chuyển cho đơn hàng từ 500k", "01/01/2022"));

        return vouchers;
    }
}
