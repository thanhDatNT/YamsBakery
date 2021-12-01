package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;
import com.thanhdat.yams.Fragments.ProfileFragment;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.ViewPagerOrderStatusAdapter;

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
    }

    private void linkViews() {
        yamsTabLayout = findViewById(R.id.tab_layout);
        yamsViewPager = findViewById(R.id.view_pager);
        toolbarOrderStatus = findViewById(R.id.toolbarOrderStatus);

        ViewPagerOrderStatusAdapter viewPagerOrderStatusAdapter = new ViewPagerOrderStatusAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        yamsViewPager.setAdapter(viewPagerOrderStatusAdapter);
        yamsTabLayout.setupWithViewPager(yamsViewPager);

    }

    private void addEventToolbar() {
        setSupportActionBar(toolbarOrderStatus);
        getSupportActionBar().setTitle(null);

        Drawable drawable = getResources().getDrawable(R.drawable.ic_back_pink);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
        toolbarOrderStatus.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
