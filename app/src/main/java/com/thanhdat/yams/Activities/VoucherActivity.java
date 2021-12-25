package com.thanhdat.yams.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.thanhdat.yams.Adapters.VoucherAdapter;
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Voucher;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class VoucherActivity extends AppCompatActivity {

    ListView lvVoucher;
    ArrayList<Voucher> vouchers;
    VoucherAdapter adapter;
    Toolbar toolbarVoucher;
    OnClickInterface onClickInterface;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);

        linkViews();
        addEventListView();
        addEventBack();
    }

    private void addEventListView() {
        adapter = new VoucherAdapter(VoucherActivity.this,R.layout.item_voucher,initData());
        lvVoucher.setAdapter(adapter);
    }

    private ArrayList<Voucher> initData() {
        vouchers = new ArrayList<Voucher>();
        vouchers.add(new Voucher(true, R.drawable.ic_shipping, "Giảm 20K phí vận chuyển dành cho khách hàng mới", "31/01/2022", 20000));
        vouchers.add(new Voucher(true, R.drawable.ic_shipping, "Giảm 15K phí vận chuyển, tất cả hình thức thanh toán", "31/01/2022", 15000));
        vouchers.add(new Voucher(false, R.drawable.ic_voucher, "Giảm 10K cho đơn hàng từ 0đ", "10/01/2022", 10000));
        vouchers.add(new Voucher(false, R.drawable.ic_voucher, "Giảm 40K cho đơn hàng từ 100k", "10/01/2022", 40000));
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
        lvVoucher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Voucher voucher = vouchers.get(position);
                Intent intent = new Intent(VoucherActivity.this, OrderActivity.class);
                intent.putExtra(Constant.VOUCHER_INTENT, voucher);
                setResult(Constant.RESULT_INTENT, intent);
                VoucherActivity.super.onBackPressed();
            }
        });
    }

    private void linkViews() {
        toolbarVoucher = findViewById(R.id.toolbarVoucher);
        lvVoucher = findViewById(R.id.lvVoucher);

    }
}
