package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.thanhdat.yams.Models.Post;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.PostAdapter;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    RecyclerView rcvFeed;
    List<Post> posts;
    PostAdapter adapter;
    private LinearLayout homepageTab, favoriteTab,  dietTab, profileTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_activity);
        linkViews();
        navigateTabs();
        configRecyclerView();
        initData();
    }

    private void linkViews() {

        rcvFeed = findViewById(R.id.rcvFeed);
        homepageTab= findViewById(R.id.feedNav);
        favoriteTab= findViewById(R.id.favoriteNav);
        dietTab= findViewById(R.id.dietNav);
        profileTab= findViewById(R.id.profileNav);
    }

    private void configRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        rcvFeed.setLayoutManager(manager);

        rcvFeed.setHasFixedSize(true);
    }

    private void initData() {
        posts = new ArrayList<>();
        posts.add(new Post(R.drawable.img_logo_pink,R.drawable.img_cake,R.drawable.ic_more,R.drawable.ic_favorite_border,R.drawable.img_comment,"Yams Store","Ho Chi Minh","minhxuan và 8389 người khác","Yams Store","Chủ nhật cùng những em bánh vàng tươi","Xem tất cả 94 bình luận"));

        posts.add(new Post(R.drawable.img_logo_pink,R.drawable.img_bdcake,R.drawable.ic_more,R.drawable.ic_favorite_border,R.drawable.img_comment,"Yams Store","Ho Chi Minh","minhxuan và 6189 người khác","Yams Store","It's your birthday party","Xem tất cả 60 bình luận"));
        adapter= new PostAdapter(getApplicationContext(),posts);
        posts.add(new Post(R.drawable.img_logo_pink,R.drawable.img_mango_cake,R.drawable.ic_more,R.drawable.ic_favorite_border,R.drawable.img_comment,"Yams Store","Ho Chi Minh","minhxuan và 9789 người khác","Yams Store","Happy Birthday to you","Xem tất cả 67 bình luận"));
        rcvFeed.setAdapter(adapter);
    }

    private void navigateTabs() {
        favoriteTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FeedActivity.this, FavoriteActivity.class));
            }
        });
        homepageTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FeedActivity.this, MainActivity.class));
            }
        });
        dietTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FeedActivity.this, DietActivity.class));
            }
        });
        profileTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FeedActivity.this, ProfileActivity.class));
            }
        });



    }
}