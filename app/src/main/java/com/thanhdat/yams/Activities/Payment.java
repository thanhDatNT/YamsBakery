package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.thanhdat.yams.Fragments.ChoosePaymentMethod;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.ViewPagerOrderStatusAdapter;

public class Payment extends AppCompatActivity {

    ImageButton btnOpenChooseTime;
    ImageButton btnOpenChoosePaymentMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        linkViews();
        addEvents();
    }

    private void linkViews() {
        ChoosePaymentMethod paymentMethod = new ChoosePaymentMethod(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        btnOpenChooseTime = findViewById(R.id.btnOpenChooseTime);
        btnOpenChoosePaymentMethod = findViewById(R.id.btnOpenChoosePaymentMethod);


    }

    private void addEvents() {
        //Open Activity_choose_time
        btnOpenChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                clickOpenBottomSheetDialog();

            }
        });

        //Open PaymentMethod
        btnOpenChoosePaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Payment.this, ChoosePaymentMethod.class));
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