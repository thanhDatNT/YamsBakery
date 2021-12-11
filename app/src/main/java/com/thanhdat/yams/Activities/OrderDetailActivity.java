package com.thanhdat.yams.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.thanhdat.yams.Fragments.PreviousOrderFragment;
import com.thanhdat.yams.Models.PendingOrder;
import com.thanhdat.yams.R;

public class OrderDetailActivity extends AppCompatActivity {
    TextView txtOrderDetailName,txtOrderDetailPrice, txtOrderDetailCode;
    ImageView imvOrderDetailThumb;
    RadioGroup radGroupCancel;

    Button btnCancelOrder, btnBackToHome, btnCancelConfirm, btnBackHome, btnConfirmSuccess;

    BottomSheetDialog sheetDialogCancelOrder, sheetDialogCancelSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        linkViews();
        addEventToolbar();
        createCancelOrderDialog();
        createCanCelSuccessDialog();
        addEvents();

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

        btnCancelOrder = findViewById(R.id.btnCancelOrder);
        btnBackToHome = findViewById(R.id.btnBackToHome);
    }

    private void addEvents() {
        btnCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetDialogCancelOrder.show();
            }
        });
        btnBackToHome.setOnClickListener(v -> startActivity(new Intent(OrderDetailActivity.this, MainActivity.class)));
    }

    private void createCancelOrderDialog() {
        if(sheetDialogCancelOrder == null){
            View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_cancel_order,null);
            btnCancelConfirm = view.findViewById(R.id.btnCancelConfirm);
            btnCancelConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //open cancel success
                    radGroupCancel = view.findViewById(R.id.radGroupCancel);
//                    if(radGroupCancel == null){
//                        Toast.makeText(OrderDetailActivity.this, "Vui lòng chọn lý do hủy đơn", Toast.LENGTH_SHORT).show();
//                    }else {
                        sheetDialogCancelSuccess.show();
//                    }
                }
            });
            sheetDialogCancelOrder = new BottomSheetDialog(this);
            sheetDialogCancelOrder.setContentView(view);
        }
    }

    private void createCanCelSuccessDialog() {
        if(sheetDialogCancelSuccess == null){
            View view1 = LayoutInflater.from(OrderDetailActivity.this).inflate(R.layout.bottom_sheet_cancel_success, null);
            btnConfirmSuccess = view1.findViewById(R.id.btnConfirmSuccess);
            btnConfirmSuccess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            btnBackHome = view1.findViewById(R.id.btnBackHome);
            btnBackHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(OrderDetailActivity.this,MainActivity.class));
                }
            });
            sheetDialogCancelSuccess = new BottomSheetDialog(OrderDetailActivity.this);
            sheetDialogCancelSuccess.setContentView(view1);
        }
    }

    private void addEventToolbar() {
      Toolbar toolbarOrderDetail = findViewById(R.id.toolbarOrderDetail);
        setSupportActionBar(toolbarOrderDetail);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(null);
            toolbarOrderDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }

    }
}