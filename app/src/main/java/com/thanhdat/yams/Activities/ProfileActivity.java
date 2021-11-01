package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.thanhdat.yams.R;

public class ProfileActivity extends AppCompatActivity {

    private LinearLayout homeTab,favoriteTab, feedTab, dietTab;
    private ImageButton imbToOrderStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        linkViews();
        navigateTabs();
        addEventImageButton();
    }

    private void linkViews() {
        homeTab = findViewById(R.id.homeNav);
        favoriteTab = findViewById(R.id.favoriteNav);
        feedTab = findViewById(R.id.feedNav);
        dietTab = findViewById(R.id.dietNav);

        imbToOrderStatus = findViewById(R.id.imbToOrderStatus);
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
        imbToOrderStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,OrderStatusActivity.class));
            }
        });
    }

}