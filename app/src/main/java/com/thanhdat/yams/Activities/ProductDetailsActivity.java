package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.thanhdat.yams.R;


public class ProductDetailsActivity extends AppCompatActivity {
    AppCompatButton btnPayment;
    TextView txtSeeReview;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        linkViews();
        addEvent();
    }

    private void linkViews() {
        txtSeeReview = findViewById(R.id.txtSeeReview);
        btnPayment= findViewById(R.id.btnAddToPayment);
        toolbar = findViewById(R.id.toolbarProduct);
    }

    private void addEvent() {
        txtSeeReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductDetailsActivity.this,SeeReviewActivity.class));
            }
        });
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductDetailsActivity.this, Payment.class));
            }
        });

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductDetailsActivity.this,MainActivity.class));
            }
        });
    }
}