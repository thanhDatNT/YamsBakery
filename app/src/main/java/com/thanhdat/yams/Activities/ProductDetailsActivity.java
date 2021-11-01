package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.thanhdat.yams.R;


public class ProductDetailsActivity extends AppCompatActivity {

    TextView txtSeeReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        linkViews();
        addEvent();
    }

    private void linkViews() {
        txtSeeReview = findViewById(R.id.txtSeeReview);
    }

    private void addEvent() {
        txtSeeReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductDetailsActivity.this,SeeReviewActivity.class));
            }
        });
    }
}