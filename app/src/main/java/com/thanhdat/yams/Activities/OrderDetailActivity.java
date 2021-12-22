package com.thanhdat.yams.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.os.Build;
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
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Databases.OrderDatabase;
import com.thanhdat.yams.Fragments.PreviousOrderFragment;
import com.thanhdat.yams.Models.PendingOrder;
import com.thanhdat.yams.Models.PreviousOrder;
import com.thanhdat.yams.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {
    TextView txtOrderDetailName,txtOrderDetailPrice, txtOrderDetailCode,txtTime1, txtTime2, txtDeliTime;
    ImageView imvOrderDetailThumb;
    Toolbar toolbarOrderDetail, toolbarCancelOrder;

    int orderId;
    OrderDatabase database;
    Button btnCancelOrder, btnBackToHome, btnCancelConfirm, btnBackHome, btnConfirmSuccess;

    RadioGroup radGroupCancel;

    List<PendingOrder> pendingOrderList;

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
            txtOrderDetailPrice.setText(String.format("%.0f", pendingOrder.getOrderPrice())+" đ");
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

        toolbarOrderDetail = findViewById(R.id.toolbarOrderDetail);

        imvOrderDetailThumb = findViewById(R.id.imvOrderDetailThumb);

        toolbarOrderDetail = findViewById(R.id.toolbarOrderDetail);

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
            toolbarCancelOrder = view.findViewById(R.id.toolbarCancelOrder);
            radGroupCancel = view.findViewById(R.id.radGroupCancel);
            toolbarCancelOrder.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if(item.getItemId() == R.id.mnuCancel){
                        sheetDialogCancelOrder.dismiss();
                    }
                    return false;
                }
            });

            btnCancelConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Delete Item in Database
                    database.execSQL("DELETE FROM " + database.TABLE_NAME + " WHERE "+ database.COL_ID + " = "+ orderId);
                    int radId = radGroupCancel.getCheckedRadioButtonId();
                    if(radId==-1){
                        Toast.makeText(getApplicationContext(), "Vui lòng chọn lý do hủy đơn", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    sheetDialogCancelOrder.dismiss();
                    sheetDialogCancelSuccess.show();
                }
            });
            sheetDialogCancelOrder = new BottomSheetDialog(this);
            sheetDialogCancelOrder.setCancelable(false);
            sheetDialogCancelOrder.setContentView(view);
        }
    }

    private void createCanCelSuccessDialog() {
        if(sheetDialogCancelSuccess == null){
            View view1 = LayoutInflater.from(OrderDetailActivity.this).inflate(R.layout.bottom_sheet_cancel_success, null);
            btnConfirmSuccess = view1.findViewById(R.id.btnConfirmSuccess);
            btnConfirmSuccess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {notifyDeletion();
                    Intent intent = new Intent(OrderDetailActivity.this, OrderStatusActivity.class);
                    int flag = -1;
                    intent.setFlags(flag);
                    startActivity(intent);
                }
            });
            btnBackHome = view1.findViewById(R.id.btnBackHome);
            btnBackHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(OrderDetailActivity.this,MainActivity.class));
                }
            });
            sheetDialogCancelSuccess = new BottomSheetDialog(this);
            sheetDialogCancelOrder.setCancelable(false);
            sheetDialogCancelSuccess.setCanceledOnTouchOutside(false);
            sheetDialogCancelSuccess.setContentView(view1);
        }
    }

    private void notifyDeletion() {
        String CHANNEL_ID = "123";
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel= new NotificationChannel(CHANNEL_ID,"My channel", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("This is my channel");
            NotificationManager manager= getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        Notification notification= new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Hủy đơn thành công")
                .setContentText("Đơn hàng " + orderId + " đã được hủy")
                .setColor(Color.MAGENTA)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager!=null){
            notificationManager.notify(1, notification);
        }
    }

    private void addEventToolbar() {
        setSupportActionBar(toolbarOrderDetail);
        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(null);
        toolbarOrderDetail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

}