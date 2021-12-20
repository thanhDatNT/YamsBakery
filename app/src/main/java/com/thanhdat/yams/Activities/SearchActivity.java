package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.SearchView;

import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Models.TextThumbView;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapters.SimpleViewGroupAdapter;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private GridView gvSuggest, gvNearly;
    private Toolbar toolbar;
    private SearchView searchView;
    ArrayList<TextThumbView> dataList;
    SimpleViewGroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        linkViews();
        initData();
        addAdapter();
        addEvents();
    }

    private void addEvents() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        Get Intent from Main Activity
        Intent intent= getIntent();
        String query= intent.getStringExtra(Constant.STRING_INTENT);
        searchView.setQueryHint(query);
    }

    private void addAdapter() {
        adapter= new SimpleViewGroupAdapter(this, R.layout.viewholder_search, dataList);
        gvNearly.setAdapter(adapter);
        gvSuggest.setAdapter(adapter);
    }

    private void initData() {
        dataList= new ArrayList<>();
        dataList.add(new TextThumbView(R.drawable.img_bdcake, "Cream Birthday cake"));
        dataList.add(new TextThumbView(R.drawable.img_mango_cake, "Salted Egg Sponge Cake"));
        dataList.add(new TextThumbView(R.drawable.img_matcha_maracon, "Green tea Macaron"));
        dataList.add(new TextThumbView(R.drawable.img_cake, "Lemon Blueberry Cheesecake"));
    }

    private void linkViews() {
        gvNearly= findViewById(R.id.gvNearlySearch);
        gvSuggest= findViewById(R.id.gvSuggestSearch);
        toolbar= findViewById(R.id.toolbarSearch);
        searchView= findViewById(R.id.svSearchActivity);
    }
}