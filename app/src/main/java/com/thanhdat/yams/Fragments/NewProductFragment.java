package com.thanhdat.yams.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.thanhdat.yams.Models.Favorite;
import com.thanhdat.yams.Models.NewProduct;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.FavoriteAdapter;
import com.thanhdat.yams.adapter.NewProductAdapter;

import java.util.ArrayList;

public class NewProductFragment extends Fragment {
    ListView lvNewProduct;
    ArrayList<NewProduct> newProducts;
    NewProductAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_new_product, container, false);

        return view;
    }

}