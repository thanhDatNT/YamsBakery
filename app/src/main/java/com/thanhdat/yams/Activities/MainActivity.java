package com.thanhdat.yams.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.thanhdat.yams.Fragments.HomeFragment;
import com.thanhdat.yams.Fragments.PreviousOrderFragment;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.ViewPagerMainAdapter;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setUpViewPager();
        navigateTabs();
    }

    private void setUpViewPager() {
        ViewPagerMainAdapter viewPagerMainAdapter= new ViewPagerMainAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerMainAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigationView.getMenu().findItem(R.id.homeNav).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.favoriteNav).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.dietNav).setChecked(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.feedNav).setChecked(true);
                        break;
                    case 4:
                        navigationView.getMenu().findItem(R.id.accountNav).setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void navigateTabs() {
//      Handle when click navigation buttons
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeNav:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.favoriteNav:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.dietNav:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.feedNav:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.accountNav:
                        viewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }

    private void linkViews() {
        navigationView= findViewById(R.id.navigationMain);
        viewPager= findViewById(R.id.layoutContainerMain);
    }
}