package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;

import com.thanhdat.yams.Contants.Constant;
import com.thanhdat.yams.Models.SimpleViewGroup;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.SimpleViewGroupAdapter;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private GridView gvSuggest, gvNearly;
    private Toolbar toolbar;
    private SearchView searchView;
    ArrayList<SimpleViewGroup> dataList;
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
        dataList.add(new SimpleViewGroup(R.drawable.img_bdcake, "New York Cheesecake"));
        dataList.add(new SimpleViewGroup(R.drawable.img_pink_cake, "Salted Egg Sponge Cake"));
        dataList.add(new SimpleViewGroup(R.drawable.img_mango_cake, "Green tea Mochi"));
        dataList.add(new SimpleViewGroup(R.drawable.img_cake, "Lemon Blueberry Cheesecake"));
    }

    private void linkViews() {
        gvNearly= findViewById(R.id.gvNearlySearch);
        gvSuggest= findViewById(R.id.gvSuggestSearch);
        toolbar= findViewById(R.id.toolbarSearch);
        searchView= findViewById(R.id.svSearchActivity);
    }
}