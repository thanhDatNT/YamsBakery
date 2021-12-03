package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

<<<<<<< Updated upstream
import android.graphics.drawable.Drawable;
=======
>>>>>>> Stashed changes
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.thanhdat.yams.R;
<<<<<<< Updated upstream
import com.thanhdat.yams.Adapter.ViewPagerOrderStatusAdapter;
=======
import com.thanhdat.yams.Adapters.ViewPagerOrderStatusAdapter;
>>>>>>> Stashed changes

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
        Toolbar toolbarOrderStatus = findViewById(R.id.toolbarOrderStatus);
        setSupportActionBar(toolbarOrderStatus);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(null);
            toolbarOrderStatus.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }

    }
}
