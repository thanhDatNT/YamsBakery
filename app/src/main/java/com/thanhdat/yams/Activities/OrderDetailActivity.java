package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thanhdat.yams.Models.PendingOrder;
import com.thanhdat.yams.R;

public class OrderDetailActivity extends AppCompatActivity {
    TextView txtOrderDetailName,txtOrderDetailPrice, txtOrderDetailCode;
    ImageView imvOrderDetailThumb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        addEventToolbar();
        linkViews();

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        PendingOrder pendingOrder = (PendingOrder) bundle.get("object_pending");
        txtOrderDetailName.setText(pendingOrder.getOrderName());
        txtOrderDetailPrice.setText(String.format("%g", pendingOrder.getOrderPrice())+"đ");
        txtOrderDetailCode.setText(pendingOrder.getOrderCode());
    }

    private void linkViews() {

        txtOrderDetailName = findViewById(R.id.txtOrderDetailName);
        txtOrderDetailPrice = findViewById(R.id.txtOrderDetailPrice);
        txtOrderDetailCode = findViewById(R.id.txtOrderDetailCode);
        imvOrderDetailThumb = findViewById(R.id.imvOrderDetailThumb);
    }

    private void addEventToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarOrderDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        Drawable drawable = getResources().getDrawable(R.drawable.ic_back_pink);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}