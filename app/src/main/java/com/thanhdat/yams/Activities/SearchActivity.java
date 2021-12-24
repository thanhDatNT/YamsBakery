package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.thanhdat.yams.Adapters.CategoryProductAdapter;
import com.thanhdat.yams.Adapters.SearchAdapter;
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.Models.TextThumbView;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapters.SimpleViewGroupAdapter;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.SelectedProduct{
    private GridView gvSuggest, gvNearly;
    private Toolbar toolbar;
    private SearchView searchView;
    ArrayList<TextThumbView> dataList;
    SimpleViewGroupAdapter adapter;

    private LinearLayout lnRecent;
    private RecyclerView rcvSearch;
    ArrayList<Product> products, productList;
    SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        linkViews();
        initData();
        addAdapter();
        addEvents();
        searchProduct();

        searchView.requestFocus();
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
    }

    private void addAdapter() {
        adapter= new SimpleViewGroupAdapter(this, R.layout.item_search, dataList);
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

        lnRecent = findViewById(R.id.lnRecent);
        rcvSearch = findViewById(R.id.rcvSearch);
    }


    private void searchProduct() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false);
        rcvSearch.setLayoutManager(layoutManager);
        productList = MainActivity.productList;
        products = new ArrayList<>();

        products = productList;

        searchAdapter = new SearchAdapter(SearchActivity.this, R.layout.item_favorite, products, this);
        rcvSearch.setAdapter(searchAdapter);

        searchView.setFocusable(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!s.equals("")){
                    lnRecent.setVisibility(View.GONE);
                    rcvSearch.setVisibility(View.VISIBLE);
                    searchAdapter.getFilter().filter(s);
                }else {
                    lnRecent.setVisibility(View.VISIBLE);
                    rcvSearch.setVisibility(View.GONE);
                }
                return false;
            }
        });


    }

    @Override
    public void selectedProduct(Product product) {
        Intent intent = new Intent(SearchActivity.this, ProductDetailsActivity.class);
        intent.putExtra("isSearch", true);
        intent.putExtra(Constant.ID_PRODUCT, product);
        startActivity(intent);
    }
}