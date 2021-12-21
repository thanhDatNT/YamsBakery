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
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity{

    Toolbar toolbarCategory;
    TextView txtCategory;
    Spinner spinner;
    String[] price = {"Tất cả","Dưới 50.000","Từ 50.000 đến 99.000","Từ 100.000 đến 149.000","Từ 150.000 trở lên"};
    ArrayList<Product> productList, products;
    RecyclerView rcvProductCategory;
    OnClickInterface onClickInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        linkViews();
        addEventBack();
        addEventProductList();
        addEventSpinner();
    }

    private void linkViews() {
        toolbarCategory = findViewById(R.id.toolbarCategory);
        txtCategory = findViewById(R.id.txtCategory);
        spinner = findViewById(R.id.spinnerCategory);
        rcvProductCategory = findViewById(R.id.rcvProductCategory);
    }

    private void addEventBack() {
        setSupportActionBar(toolbarCategory);
        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(null);
        toolbarCategory.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void addEventProductList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(CategoryActivity.this, LinearLayoutManager.VERTICAL, false);
        rcvProductCategory.setLayoutManager(layoutManager);

        getData();

        rcvProductCategory.setAdapter(new CategoryProductAdapter(CategoryActivity.this, R.layout.item_favorite, products, onClickInterface));
    }
    private void getData(){
        productList = MainActivity.productList;
        products = new ArrayList<>();
        Intent intent = getIntent();
        if (intent != null) {
            String number = String.valueOf(intent.getExtras().getInt("id"));
            for (Product p : productList) {
                if (p.getCategory().equals(number)) {
                    products.add(p);
                }
            }
            //set Title
            txtCategory.setText(intent.getExtras().getString("category"));
        }
    }

    private void addEventSpinner() {
        //creating array adapter
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.item_spinner, price);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Filter product
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                rcvProductCategory.setAdapter(new CategoryProductAdapter(CategoryActivity.this, R.layout.item_favorite, productsFilter, onClickInterface));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}