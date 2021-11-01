package com.thanhdat.yams.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.thanhdat.yams.Models.Favorite;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.FavoriteAdapter;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {

    ListView lvFavorite;
    ArrayList<Favorite> favorites;
    FavoriteAdapter adapter;
    LinearLayout favoriteTab, feedTab, profileTab,homeTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        linkViews();
        initData();
        loadData();
        navigateTabs();
    }

    private void linkViews() {

        lvFavorite = findViewById(R.id.lvFavorite);
        homeTab=findViewById(R.id.homeNav);
        favoriteTab= findViewById(R.id.favoriteNav);
        feedTab= findViewById(R.id.feedNav);
        profileTab= findViewById(R.id.profileNav);
    }

    private void initData() {
        favorites = new ArrayList<Favorite>();
        favorites.add(new Favorite(R.drawable.bdcake, "Dumplings", 35000, 30000, 5, 15));
        favorites.add(new Favorite(R.drawable.cake2, "Tarks trứng", 15000, 20000, 4.5, 25));
        favorites.add(new Favorite(R.drawable.cake3, "Gato", 100000, 110000, 5, 15));
        favorites.add(new Favorite(R.drawable.cake4, "Cup cake", 20000, 22000, 4.5, 10));
        favorites.add(new Favorite(R.drawable.cake5, "strawberry cake", 35000, 3000, 5, 5));
    }

    private void loadData() {
        adapter = new FavoriteAdapter(FavoriteActivity.this, R.layout.favorite_item, favorites);
        lvFavorite.setAdapter(adapter);
    }

    private void navigateTabs() {

        homeTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FavoriteActivity.this, MainActivity.class));
            }
        });
        favoriteTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FavoriteActivity.this, FavoriteActivity.class));
            }
        });

        feedTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FavoriteActivity.this, FeedActivity.class));
            }
        });
        profileTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FavoriteActivity.this, ProfileActivity.class));
            }
        });
    }
}