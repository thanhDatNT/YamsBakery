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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        linkViews();
        addEvents();
    }

    private void linkViews() {
        btnOpenChooseTime = findViewById(R.id.btnOpenChooseTime);
    }

    private void addEvents() {
        btnOpenChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open Activity_choose_time
                Intent intent = new Intent(Payment.this, ChooseTime.class );
                startActivity(intent);
            }
        });

    }
}