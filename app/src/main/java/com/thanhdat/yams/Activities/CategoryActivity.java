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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.thanhdat.yams.Adapters.CategoryProductAdapter;
import com.thanhdat.yams.Adapters.ProductAdapter;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Favorite;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapters.FavoriteAdapter;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Toolbar toolbarCategory;
    TextView txtCategory;
    Spinner spinner;
    String[] price = {"Tất cả","Dưới 50.000","Từ 50.000 đến 99.000","Từ 100.000 đến 149.000","Từ 150.000 trở lên"};
    ArrayList<Product> productList;
    RecyclerView rcvProductCategory;
    ProductAdapter adapter;
    OnClickInterface onClickInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        linkViews();
        addEventBack();
        addEventSpinner();
        addEventProductList();
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
                startActivity(new Intent(CategoryActivity.this, MainActivity.class));
            }
        });
    }

    private void addEventSpinner() {
        spinner.setOnItemSelectedListener(this);
        //creating array adapter
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.item_spinner, price);
        adapter.setDropDownViewResource(R.layout.item_spinner_dropdown);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(CategoryActivity.this, price[i], Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void addEventProductList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(CategoryActivity.this, LinearLayoutManager.VERTICAL, false);
        ;
        rcvProductCategory.setLayoutManager(layoutManager);

        productList = MainActivity.productList;
        ArrayList<Product> products = new ArrayList<>();

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

        rcvProductCategory.setAdapter(new CategoryProductAdapter(CategoryActivity.this, products, onClickInterface));
    }
}