package com.thanhdat.yams.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.thanhdat.yams.Fragments.ProfileFragment;
import com.thanhdat.yams.Models.Favorite;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.FavoriteAdapter;

import java.util.ArrayList;

public class FavoriteActivity extends AppCompatActivity {
    EditText edtFavorite;
    ListView lvFavorite;
    ArrayList<Favorite> favorites;
    FavoriteAdapter adapter;
    LinearLayout dietTab, feedTab, profileTab,homeTab;

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
        edtFavorite= findViewById(R.id.edtSearchFavorite);
        lvFavorite = findViewById(R.id.lvFavorite);
        homeTab=findViewById(R.id.homeNav);
        dietTab= findViewById(R.id.dietNav);
        feedTab= findViewById(R.id.feedNav);
        profileTab= findViewById(R.id.profileNav);
    }

    private void initData() {

        favorites = new ArrayList<Favorite>();
        favorites.add(new Favorite(R.drawable.img_bdcake, "Dumplings", 35000, 30000, 5, 15));
        favorites.add(new Favorite(R.drawable.img_cake, "Tarks trứng", 15000, 20000, 4.5, 25));
        favorites.add(new Favorite(R.drawable.img_mango_cake, "Gato", 100000, 110000, 5, 15));
        favorites.add(new Favorite(R.drawable.img_pink_cake, "Cup cake", 20000, 22000, 4.5, 10));
        favorites.add(new Favorite(R.drawable.img_summer_pudding, "strawberry cake", 35000, 3000, 5, 5));
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
//        dietTab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(FavoriteActivity.this, DietActivity.class));
//            }
//        });
//
//
//        feedTab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(FavoriteActivity.this, FeedActivity.class));
//            }
//        });

        profileTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FavoriteActivity.this, ProfileFragment.class));
            }
        });
    }
}