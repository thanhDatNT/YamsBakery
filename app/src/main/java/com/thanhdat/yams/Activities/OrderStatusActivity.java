package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.tabs.TabLayout;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.ViewPagerAdapter;

public class OrderStatusActivity extends AppCompatActivity {
    private TabLayout yamsTabLayout;
    private ViewPager yamsViewPager;
    private ImageButton imbBackToProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_status);

        linkViews();
        addEventImageButton();
    }

    private void linkViews() {
        yamsTabLayout = findViewById(R.id.tab_layout);
        yamsViewPager = findViewById(R.id.view_pager);
        imbBackToProfile = findViewById(R.id.imbBackToProfile);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        yamsViewPager.setAdapter(viewPagerAdapter);
        yamsTabLayout.setupWithViewPager(yamsViewPager);

    }

    private void addEventImageButton() {
        imbBackToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderStatusActivity.this,ProfileActivity.class));
            }
        });
    }
}