package com.thanhdat.yams.Fragments;

import static com.thanhdat.yams.Activities.MainActivity.productList;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thanhdat.yams.Activities.CartActivity;
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
    CategoryProductAdapter adapter;
    OnClickInterface onClickInterface;
    CheckBox chkLike;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_favorite, container, false);
        rcvFavorite = view.findViewById(R.id.rcvFavorite);
        chkLike = view.findViewById(R.id.chkLike);
        toolbarFavorite = view.findViewById(R.id.toolbarFavorite);

        addEvent();
        addEventToCart();
        return view;
    }

    private void addEvent() {
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);;
        rcvFavorite.setLayoutManager(layoutManager);

        productList = MainActivity.productList;
        ArrayList<Product> favoriteProducts = new ArrayList<>();
        for (Product p : productList){
            if(p.isFavorite()){
                favoriteProducts.add(p);
            }
        }
        rcvFavorite.setAdapter(new FavoriteAdapter(getContext(),R.layout.item_favorite, favoriteProducts, onClickInterface));
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
                Intent intent = new Intent(getContext(), CartActivity.class);
                startActivity(intent);
                return false;
            }
        });
    }


}