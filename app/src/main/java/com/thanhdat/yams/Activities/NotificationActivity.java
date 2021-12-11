package com.thanhdat.yams.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.thanhdat.yams.Models.TextThumbView;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapter.SimpleViewGroupAdapter;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    private ListView lvNotify;
    private Toolbar toolbar;
    ArrayList<TextThumbView> dataNotifications;
    SimpleViewGroupAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        initViews();
        addDataListView();
        addEvents();
    }

    private void addEvents() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                overridePendingTransition(R.anim.translate_slide_enter, R.anim.translate_slide_exit);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notification_heading, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.mnuDelete){
            confirmDelete();
        }
        return super.onOptionsItemSelected(item);
    }

    private void confirmDelete() {
        AlertDialog.Builder builder= new AlertDialog.Builder(NotificationActivity.this);
        builder.setTitle("Xác nhận xóa!");
        builder.setMessage("Xóa tất cả thông báo?");
        builder.setIcon(R.mipmap.ic_logo_launcher);
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dataNotifications.clear();
                adapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void addDataListView() {
        dataNotifications= new ArrayList<>();
        dataNotifications.add(new TextThumbView(R.drawable.ic_new_releases_notify, "Bạn có #3 mã giảm giá chưa sử dụng tại kho voucher. Dùng ngay trước khi hết hạn nào!"));
        dataNotifications.add(new TextThumbView(R.drawable.ic_confirm, "Đơn hàng #1000s9dv đã được giao thành công. Nhấn để xem thông tin chi tiết."));
        dataNotifications.add(new TextThumbView(R.drawable.ic_confirm, "Đơn hàng #1000s9dv đã được giao thành công. Nhấn để xem thông tin chi tiết."));
        adapter= new SimpleViewGroupAdapter(this, R.layout.viewholder_notification, dataNotifications);
        lvNotify.setAdapter(adapter);

    }

    private void initViews() {
        lvNotify=findViewById(R.id.lvNotification);
        toolbar= findViewById(R.id.toolbarNotification);
    }
}