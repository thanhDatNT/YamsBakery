package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.thanhdat.yams.Adapters.CategoryProductAdapter;
import com.thanhdat.yams.Adapters.ProductAdapter;
import com.thanhdat.yams.Adapters.SeeAllAdapter;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class SeeAllActivity extends AppCompatActivity {

    Toolbar toolbarSeeAll;
    TextView txtSeeAllTitle;

    ArrayList<Product> productList;
    RecyclerView rcvSeeAllProducts;
    OnClickInterface onClickInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);

        linkViews();
        addEventBack();
        addEventProductList();
    }

    private void linkViews() {
        toolbarSeeAll = findViewById(R.id.toolbarSeeAll);
        txtSeeAllTitle = findViewById(R.id.txtTitle);
        rcvSeeAllProducts = findViewById(R.id.rcvSeeAllProducts);
    }

    private void addEventBack() {
        setSupportActionBar(toolbarSeeAll);
        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(null);
        toolbarSeeAll.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addEventProductList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(SeeAllActivity.this, LinearLayoutManager.VERTICAL, false);

        rcvSeeAllProducts.setLayoutManager(layoutManager);

        productList = MainActivity.productList;
        ArrayList<Product> products = new ArrayList<>();

        Intent intent = getIntent();
        if (intent != null) {
            String text = intent.getExtras().getString("idAll");
            if(text.equals("new")){
                for (Product p : productList){
                    if(p.getTag().equals("New")){
                        products.add(p);
                    }
                }
                //set Title
                txtSeeAllTitle.setText("Sản phẩm mới");
            } else if(text.equals("promo")){
                for (Product p : productList){
                    if(p.getTag().equals("Promo")){
                        products.add(p);
                    }
                }
                //set Title
                txtSeeAllTitle.setText("Sản phẩm khuyến mãi");
            } else {
                for (Product p : productList){
                    if(p.getTag().equals("Popular")){
                        products.add(p);
                    }
                }
                //set Title
                txtSeeAllTitle.setText("Sản phẩm phổ biến");
            }

        }

        rcvSeeAllProducts.setAdapter(new SeeAllAdapter(SeeAllActivity.this, products, onClickInterface));
    }
}