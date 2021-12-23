package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.thanhdat.yams.Fragments.PendingFragment;
import com.thanhdat.yams.Fragments.PreviousOrderFragment;
import com.thanhdat.yams.Fragments.ShippingOrderFragment;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapters.ViewPagerOrderStatusAdapter;

public class OrderStatusActivity extends AppCompatActivity {
    private TabLayout yamsTabLayout;
    private ViewPager yamsViewPager;
    Toolbar toolbarOrderStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        linkViews();
        addEventToolbar();
        receiveFromOrderDetail();
    }

    private void linkViews() {
        yamsTabLayout = findViewById(R.id.tab_layout);
        yamsViewPager = findViewById(R.id.view_pager);
        toolbarOrderStatus = findViewById(R.id.toolbarOrderStatus);

        ViewPagerOrderStatusAdapter viewPagerOrderStatusAdapter = new ViewPagerOrderStatusAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        yamsViewPager.setAdapter(viewPagerOrderStatusAdapter);
        yamsTabLayout.setupWithViewPager(yamsViewPager);


    }

    private void receiveFromOrderDetail() {
        Intent intent = getIntent();
        if(intent != null){
            if (intent.getFlags() == -1) {
                yamsViewPager.setCurrentItem(2);
            }else {
                yamsViewPager.setCurrentItem(0);
            }
        }
    }

    private void addEventToolbar() {
        setSupportActionBar(toolbarOrderStatus);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(null);
            toolbarOrderStatus.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(OrderStatusActivity.this, MainActivity.class));
                }
            });
        }

    }
}
