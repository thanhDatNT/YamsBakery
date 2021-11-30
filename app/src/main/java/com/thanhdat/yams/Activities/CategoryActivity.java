package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.thanhdat.yams.Models.Favorite;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.FavoriteAdapter;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Toolbar toolbarCategory;
    Spinner spinner;
    String[] price = {"Tất cả","Dưới 50.000","Từ 50.000 đến 99.000","Từ 100.000 đến 149.000","Từ 150.000 trở lên"};

    ListView lvProductCategory;
    ArrayList<Favorite> products;
    FavoriteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        linkViews();
        addEventBack();
        addEventSpinner();
        addEventListView();
    }

    private void linkViews() {
        toolbarCategory = findViewById(R.id.toolbarCategory);
        spinner = findViewById(R.id.spinnerCategory);
        lvProductCategory = findViewById(R.id.lvProductCategory);
    }

    private void addEventBack() {
        setSupportActionBar(toolbarCategory);
        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(null);
        toolbarCategory.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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

    private void addEventListView() {

        adapter = new FavoriteAdapter(CategoryActivity.this,R.layout.item_favorite,initData());
        lvProductCategory.setAdapter(adapter);

    }

    private List<Favorite> initData() {
        products = new ArrayList<Favorite>();
        products.add(new Favorite(R.drawable.img_bdcake, "Dumplings", 35000, 5, 15));
        products.add(new Favorite(R.drawable.img_cake, "Tarks trứng", 150000, 4.5, 25));
        products.add(new Favorite(R.drawable.img_mango_cake, "Gato", 100000, 5, 15));
        products.add(new Favorite(R.drawable.img_pink_cake, "Cup cake", 200000, 4.5, 10));
        products.add(new Favorite(R.drawable.img_summer_pudding, "strawberry cake", 350000, 5,5));

        return products;
    }

}