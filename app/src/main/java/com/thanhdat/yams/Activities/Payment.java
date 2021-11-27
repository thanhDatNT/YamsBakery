package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.thanhdat.yams.Fragments.ChoosePaymentMethodFragment;
import com.thanhdat.yams.Fragments.VoucherFragment;
import com.thanhdat.yams.R;

public class Payment extends AppCompatActivity {

    ImageButton btnOpenChooseTime;
    ImageButton btnOpenChoosePaymentMethod, btnOpenVoucher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        linkViews();
        addEvents();
    }

    private void linkViews() {
        btnOpenChooseTime = findViewById(R.id.btnOpenChooseTime);
        btnOpenChoosePaymentMethod = findViewById(R.id.btnOpenChoosePaymentMethod);
        btnOpenVoucher = findViewById(R.id.btnOpenVoucher);
    }

    private void addEvents() {
            //Open ChooseTimeFragment
        btnOpenChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOpenBottomSheetDialog();

            }
        });

        //Open ChoosePaymentMethodFragment
        btnOpenChoosePaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                ChoosePaymentMethodFragment fragment= new ChoosePaymentMethodFragment();
                fm.beginTransaction().replace(R.id.layoutContainerPayment, fragment).commit();

                    btnOpenChoosePaymentMethod.setVisibility(View.GONE);

            }
        });

        //Open voucher
        btnOpenVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                VoucherFragment fragment= new VoucherFragment();
                fm.beginTransaction().replace(R.id.layoutContainerPayment, fragment).commit();

                btnOpenVoucher.setVisibility(View.GONE);
            }
        });

    }

    private void clickOpenBottomSheetDialog() {

        View viewDialog = getLayoutInflater().inflate(R.layout.fragment_choose_time, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewDialog);
        bottomSheetDialog.show();

//Ngăn người dùng bấm ra ngoài dialog để thoát
        bottomSheetDialog.setCancelable(false);
//Tắt dialog
        ImageButton imvCancel = viewDialog.findViewById(R.id.imvCancel);
        imvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
    }
}