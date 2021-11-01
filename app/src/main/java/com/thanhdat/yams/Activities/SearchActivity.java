package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.thanhdat.yams.Models.SimpleViewGroup;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.SimpleViewGroupAdapter;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private GridView gvSuggest, gvNearly;
    private ImageView imvBackToHome;
    private EditText edtSearch;
    ArrayList<SimpleViewGroup> dataList;
    SimpleViewGroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        linkViews();
        edtSearch.clearFocus();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initData();
        addAdapter();
        addEvents();
    }

    private void addEvents() {
        imvBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, MainActivity.class));
            }
        });
    }

    private void addAdapter() {
        adapter= new SimpleViewGroupAdapter(this, R.layout.viewholder_search, dataList);
        gvNearly.setAdapter(adapter);
        gvSuggest.setAdapter(adapter);
    }

    private void initData() {
        dataList= new ArrayList<>();
        dataList.add(new SimpleViewGroup(R.drawable.img_bdcake, "New York Cheesecake"));
        dataList.add(new SimpleViewGroup(R.drawable.img_cate4, "Salted Egg Sponge Cake"));
        dataList.add(new SimpleViewGroup(R.drawable.img_mango_cake, "Green tea Mochi"));
        dataList.add(new SimpleViewGroup(R.drawable.img_cake, "Lemon Blueberry Cheesecake"));
    }

    private void linkViews() {
        gvNearly= findViewById(R.id.gvNearlySearch);
        gvSuggest= findViewById(R.id.gvSuggestSearch);
        imvBackToHome= findViewById(R.id.imvBackHome);
        edtSearch= findViewById(R.id.edtSearch);
    }
}