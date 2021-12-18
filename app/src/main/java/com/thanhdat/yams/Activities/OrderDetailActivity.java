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
import com.squareup.picasso.Picasso;
import com.thanhdat.yams.Databases.OrderDatabase;
import com.thanhdat.yams.Fragments.PreviousOrderFragment;
import com.thanhdat.yams.Models.PendingOrder;
import com.thanhdat.yams.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OrderDetailActivity extends AppCompatActivity {
    TextView txtOrderDetailName,txtOrderDetailPrice, txtOrderDetailCode,txtTime1, txtTime2, txtDeliTime;
    ImageView imvOrderDetailThumb;

    int orderId;
    OrderDatabase database;
    Button btnCancelOrder, btnBackToHome, btnCancelConfirm, btnBackHome, btnConfirmSuccess;

    BottomSheetDialog sheetDialogCancelOrder, sheetDialogCancelSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        database = new OrderDatabase(this);

        linkViews();
        addEventToolbar();
        createCancelOrderDialog();
        createCanCelSuccessDialog();
        addEvents();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            PendingOrder pendingOrder = (PendingOrder) bundle.get("object_pending");
            orderId = pendingOrder.getId();
            txtOrderDetailName.setText(pendingOrder.getOrderName());
            txtOrderDetailPrice.setText(String.format("%g", pendingOrder.getOrderPrice())+"đ");
            txtOrderDetailCode.setText("#" + pendingOrder.getOrderCode());
            Picasso.get().load(pendingOrder.getOrderThumb()).into(imvOrderDetailThumb);
        }

    }

    private void linkViews() {
        txtTime1 = findViewById(R.id.txtTime1);
        txtTime2 = findViewById(R.id.txtTime2);
        txtDeliTime = findViewById(R.id.txtTime3);
        txtOrderDetailName = findViewById(R.id.txtOrderDetailName);
        txtOrderDetailPrice = findViewById(R.id.txtOrderDetailPrice);
        txtOrderDetailCode = findViewById(R.id.txtOrderDetailCode);

        imvOrderDetailThumb = findViewById(R.id.imvOrderDetailThumb);

        btnCancelOrder = findViewById(R.id.btnCancelOrder);
        btnBackToHome = findViewById(R.id.btnBackToHome);
    }

    private void addEvents() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm a");
        Date d = new Date();

        txtTime2.setText(formatter.format(d));
        txtTime1.setText(formatter.format(d));
        txtDeliTime.setText(formatter.format(d));

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
//                    Delete Item in Database
                    database.execSQL("DELETE FROM " + database.TABLE_NAME + " WHERE "+ database.COL_ID + " = "+ orderId);
                    sheetDialogCancelOrder.dismiss();
                    sheetDialogCancelSuccess.show();
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
                    startActivity(new Intent(OrderDetailActivity.this, MainActivity.class));

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