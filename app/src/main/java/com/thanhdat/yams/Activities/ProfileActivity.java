package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.thanhdat.yams.Models.Banner;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.SliderBannerAdapter;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private LinearLayout homeTab,favoriteTab, feedTab, dietTab;
    private ImageButton imbToOrderStatus;
    private SliderView sliderBannerProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        linkViews();
        navigateTabs();
        addEventImageButton();
        addEventSliderBanner();
    }

    private void linkViews() {
        homeTab = findViewById(R.id.homeNav);
        favoriteTab = findViewById(R.id.favoriteNav);
        feedTab = findViewById(R.id.feedNav);
        dietTab = findViewById(R.id.dietNav);

        imbToOrderStatus = findViewById(R.id.imbToOrderStatus);

        sliderBannerProfile = findViewById(R.id.imageSliderProfile);
    }

    private void navigateTabs() {
        homeTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });
        favoriteTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, FavoriteActivity.class));
            }
        });
        dietTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, DietActivity.class));
            }
        });
        feedTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, FeedActivity.class));
            }
        });
    }


    private void addEventImageButton() {

        //click to order status activity
        imbToOrderStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,OrderStatusActivity.class));
            }
        });

    }

    private void addEventSliderBanner() {
        ArrayList<Banner> banners= new ArrayList<>();
        banners.add(new Banner(R.drawable.img_banner_1));
        banners.add(new Banner(R.drawable.img_banner_2));
        banners.add(new Banner(R.drawable.img_banner_3));
        sliderBannerProfile.setSliderAdapter(new SliderBannerAdapter(banners, this));
//        Config Slider Banner profile
        sliderBannerProfile.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
        sliderBannerProfile.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderBannerProfile.startAutoCycle();
    }

}