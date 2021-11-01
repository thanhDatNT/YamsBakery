package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.thanhdat.yams.R;

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

        btnOpenChooseTime = findViewById(R.id.btnOpenChooseTime);
        btnOpenChoosePaymentMethod = findViewById(R.id.btnOpenChoosePaymentMethod);
    }

    private void addEvents() {
        //Open Activity_choose_time
        btnOpenChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Payment.this, ChooseTime.class );
                startActivity(intent);
            }
        });

        //Open PaymentMethod
        btnOpenChoosePaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Payment.this, PaymentMethod.class);
               startActivity(intent);
            }
        });

    }
}