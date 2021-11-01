package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.thanhdat.yams.Models.SimpleViewGroup;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.SimpleViewGroupAdapter;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    private ListView lvNotify;
    private ImageView imvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        initViews();
        addDataListView();
        addEvents();
    }

    private void addEvents() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NotificationActivity.this, MainActivity.class));
            }
        });
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
        imvBack= findViewById(R.id.imvBackHome);
    }
}