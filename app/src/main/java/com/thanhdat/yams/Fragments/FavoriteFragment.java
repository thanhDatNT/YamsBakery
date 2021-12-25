package com.thanhdat.yams.Fragments;

import static com.thanhdat.yams.Activities.MainActivity.productList;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thanhdat.yams.Activities.CartActivity;
import com.thanhdat.yams.Activities.CategoryActivity;
import com.thanhdat.yams.Activities.MainActivity;

import com.thanhdat.yams.Adapters.CategoryProductAdapter;

import com.thanhdat.yams.Adapters.FavoriteAdapter;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {
    Toolbar toolbarFavorite;
    RecyclerView rcvFavorite;
    OnClickInterface onClickInterface;
    CheckBox chkLike;
    ArrayList<Product> productList, products;
    Spinner spinner;
    String[] price = {"Tất cả","Dưới 50.000","Từ 50.000 đến 99.000","Từ 100.000 đến 149.000","Từ 150.000 trở lên"};
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_favorite, container, false);
        rcvFavorite = view.findViewById(R.id.rcvFavorite);
        chkLike = view.findViewById(R.id.chkLike);
        toolbarFavorite = view.findViewById(R.id.toolbarFavorite);
        spinner = view.findViewById(R.id.spinnerCategory);

        addEvent();
        addEventToCart();
        addEventSpinner();
        return view;
    }

    private void addEvent() {
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);;
        rcvFavorite.setLayoutManager(layoutManager);

        getData();

        rcvFavorite.setAdapter(new FavoriteAdapter(getContext(),R.layout.item_favorite, products, onClickInterface));
    }

    private void getData() {

        productList = MainActivity.productList;
        products = new ArrayList<>();
        for (Product p : productList){
            if(p.isFavorite()){
                products.add(p);
            }
        }
    }

    private void addEventToCart() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if(activity != null){
            activity.setSupportActionBar(toolbarFavorite);
            if(activity.getSupportActionBar() != null){
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                activity.getSupportActionBar().setTitle(null);
            }
        }
        toolbarFavorite.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.mnuCartDiet){
                    startActivity(new Intent(getContext(), CartActivity.class));
                }
                return false;
            }
        });
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.diet_heading, menu);
    }

    private void addEventSpinner() {
        //creating array adapter
        ArrayAdapter adapter = new ArrayAdapter(getContext(), R.layout.item_spinner, price);
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
                rcvFavorite.setAdapter(new FavoriteAdapter(getContext(), R.layout.item_favorite, productsFilter, onClickInterface));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}