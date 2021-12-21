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
import android.widget.AdapterView;
import android.widget.ListView;

import com.thanhdat.yams.Models.TextThumbView;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapters.SimpleViewGroupAdapter;

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
        //toolbar and turn back
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

        //delete an item
        lvNotify.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder= new AlertDialog.Builder(NotificationActivity.this);
                builder.setTitle("Xác nhận xóa!");
                builder.setMessage("Bạn chắc chắn xóa thông báo này?");
                builder.setIcon(R.mipmap.ic_logo_launcher);
                builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dataNotifications.remove(i);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setCancelable(false);
                builder.create().show();

                return false;
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
        builder.setMessage("Bạn chắc chắn xóa tất cả thông báo?");
        builder.setIcon(R.mipmap.ic_logo_launcher);
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dataNotifications.clear();
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    private void addDataListView() {
        dataNotifications= new ArrayList<>();
        dataNotifications.add(new TextThumbView(R.drawable.ic_new_releases_notify, "Bạn có #3 mã giảm giá chưa sử dụng tại kho voucher. Dùng ngay trước khi hết hạn nào!"));
        dataNotifications.add(new TextThumbView(R.drawable.ic_confirm, "Đơn hàng #10001 đã được giao thành công. Nhấn để xem thông tin chi tiết."));
        dataNotifications.add(new TextThumbView(R.drawable.ic_confirm, "Đơn hàng #10002 đã được giao thành công. Nhấn để xem thông tin chi tiết."));
        dataNotifications.add(new TextThumbView(R.drawable.ic_new_releases_notify, "Bạn nhận được #1 voucher freeship khi mua đơn trên 100.000. Dùng ngay thôi!"));
        adapter= new SimpleViewGroupAdapter(this, R.layout.item_notification, dataNotifications);
        lvNotify.setAdapter(adapter);

    }

    private void initViews() {
        lvNotify=findViewById(R.id.lvNotification);
        toolbar= findViewById(R.id.toolbarNotification);
    }
}