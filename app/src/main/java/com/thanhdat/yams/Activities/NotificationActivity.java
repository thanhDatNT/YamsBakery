package com.thanhdat.yams.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.thanhdat.yams.Models.SimpleViewGroup;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.SimpleViewGroupAdapter;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    private ListView lvNotify;
    private Toolbar toolbar;

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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notification_heading, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.mnuDelete){
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void addDataListView() {
        ArrayList<SimpleViewGroup> dataNotifications= new ArrayList<>();
        dataNotifications.add(new SimpleViewGroup(R.drawable.ic_new_releases_notify, "Bạn có #3 mã giảm giá chưa sử dụng tại kho voucher. Dùng ngay trước khi hết hạn nào!"));
        dataNotifications.add(new SimpleViewGroup(R.drawable.ic_confirmation_notify, "Đơn hàng #1000s9dv đã được giao thành công. Nhấn để xem thông tin chi tiết."));
        dataNotifications.add(new SimpleViewGroup(R.drawable.ic_confirmation_notify, "Đơn hàng #1000s9dv đã được giao thành công. Nhấn để xem thông tin chi tiết."));
        lvNotify.setAdapter(new SimpleViewGroupAdapter(this, R.layout.viewholder_notification, dataNotifications));

    }

    private void initViews() {
        lvNotify=findViewById(R.id.lvNotification);
        toolbar= findViewById(R.id.toolbarNotification);
    }
}