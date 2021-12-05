package com.thanhdat.yams.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.thanhdat.yams.Adapter.VoucherAdapter;
import com.thanhdat.yams.Models.Voucher;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class VoucherActivity extends AppCompatActivity {

    ListView lvVoucher;
    ArrayList<Voucher> vouchers;
    VoucherAdapter adapter;
    Toolbar toolbarVoucher;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);
        linkViews();
        addEventListView();
        addEventBack();



    }

    private void linkViews() {
        toolbarVoucher = findViewById(R.id.toolbarVoucher);
        lvVoucher = findViewById(R.id.lvVoucher);

    }

    private void addEventListView() {
        adapter = new VoucherAdapter(VoucherActivity.this,R.layout.item_voucher,initData());
        lvVoucher.setAdapter(adapter);
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
    private void addEventBack() {
        setSupportActionBar(toolbarVoucher);
        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(null);
        toolbarVoucher.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
