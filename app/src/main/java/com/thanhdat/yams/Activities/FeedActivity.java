package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.thanhdat.yams.Models.Post;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.PostAdapter;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    RecyclerView rcvFeed;
    List<Post> posts;
    PostAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_activity);
        linkViews();
        configRecyclerView();
        initData();
    }

    private void linkViews() {
        rcvFeed = findViewById(R.id.rcvFeed);
    }

    private void configRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        rcvFeed.setLayoutManager(manager);

        rcvFeed.setHasFixedSize(true);
    }

    private void initData() {
        posts = new ArrayList<>();
        posts.add(new Post(R.drawable.img_logo_pink,R.drawable.cake,R.drawable.ic_more,R.drawable.heart,R.drawable.comment,"Yams Store","Ho Chi Minh","minhxuan và 8389 người khác","Yams Store","Chủ nhật cùng những em bánh vàng tươi","Xem tất cả 94 bình luận"));

        posts.add(new Post(R.drawable.img_logo_pink,R.drawable.bdcake,R.drawable.ic_more,R.drawable.heart,R.drawable.comment,"Yams Store","Ho Chi Minh","minhxuan và 8389 người khác","Yams Store","Chủ nhật cùng những em bánh vàng tươi","Xem tất cả 94 bình luận"));
        adapter= new PostAdapter(getApplicationContext(),posts);
        posts.add(new Post(R.drawable.img_logo_pink,R.drawable.mangocake,R.drawable.ic_more,R.drawable.heart,R.drawable.comment,"Yams Store","Ho Chi Minh","minhxuan và 8389 người khác","Yams Store","Chủ nhật cùng những em bánh vàng tươi","Xem tất cả 94 bình luận"));
        rcvFeed.setAdapter(adapter);
    }
}