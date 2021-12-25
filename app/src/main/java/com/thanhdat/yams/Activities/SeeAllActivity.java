package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.thanhdat.yams.Adapters.CategoryProductAdapter;
import com.thanhdat.yams.Adapters.ProductAdapter;
import com.thanhdat.yams.Adapters.SeeAllAdapter;
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class SeeAllActivity extends AppCompatActivity {

    Toolbar toolbarSeeAll;
    TextView txtSeeAllTitle;
    Spinner spinnerSeeAll;
    String[] price = {"Tất cả","Dưới 50.000","Từ 50.000 đến 99.000","Từ 100.000 đến 149.000","Từ 150.000 trở lên"};
    ArrayList<Product> productList, products;
    RecyclerView rcvSeeAllProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);

        linkViews();
        addEventBack();
        addEventProductList();
        addEventSpinner();
    }

    private void linkViews() {
        toolbarSeeAll = findViewById(R.id.toolbarSeeAll);
        txtSeeAllTitle = findViewById(R.id.txtTitle);
        rcvSeeAllProducts = findViewById(R.id.rcvSeeAllProducts);
        spinnerSeeAll = findViewById(R.id.spinnerSeeAll);
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
        getData();
        rcvSeeAllProducts.setAdapter(new SeeAllAdapter(SeeAllActivity.this, products));

    }

    private void getData(){
        productList = MainActivity.productList;
        products = new ArrayList<>();
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
                txtSeeAllTitle.setText("Mới ra lò");
            } else if(text.equals("promo")){
                for (Product p : productList){
                    if(p.getTag().equals("Promo")){
                        products.add(p);
                    }
                }
                //set Title
                txtSeeAllTitle.setText("Hot deals");
            } else {
                for (Product p : productList){
                    if(p.getTag().equals("Top")){
                        products.add(p);
                    }
                }
                //set Title
                txtSeeAllTitle.setText("Best sellers");
            }

        }
    }

    private void addEventSpinner() {
        //creating array adapter
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.item_spinner, price);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeeAll.setAdapter(adapter);

        //Filter product
        spinnerSeeAll.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getData();
                ArrayList<Product> productsFilter = new ArrayList<>();
                switch (i){
                    case 0:
                        productsFilter = products;
                        break;
                    case 1:
                        for (Product p2 : products){
                            if(p2.getPrice() < 50000){
                                productsFilter.add(p2);
                            }
                        }
                        break;
                    case 2:
                        for (Product p3 : products){
                            if(p3.getPrice() >= 50000 && p3.getPrice() <100000){
                                productsFilter.add(p3);
                            }
                        }
                        break;
                    case 3:
                        for (Product p4 : products){
                            if(p4.getPrice() >= 100000 && p4.getPrice() <150000){
                                productsFilter.add(p4);
                            }
                        }
                        break;
                    case 4:
                        for (Product p5 : products){
                            if(p5.getPrice() >= 150000){
                                productsFilter.add(p5);
                            }
                        }
                        break;
                }
                rcvSeeAllProducts.setAdapter(new SeeAllAdapter(SeeAllActivity.this, productsFilter));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}