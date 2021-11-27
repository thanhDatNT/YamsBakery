package com.thanhdat.yams.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.thanhdat.yams.Activities.CartActivity;
import com.thanhdat.yams.Activities.MainActivity;
import com.thanhdat.yams.Models.Cart;
import com.thanhdat.yams.Models.Favorite;
import com.thanhdat.yams.Models.Post;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.CartAdapter;
import com.thanhdat.yams.adapter.FavoriteAdapter;
import com.thanhdat.yams.adapter.PostAdapter;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {
    ListView lvFavorite;
    ArrayList<Favorite> favorites;
    FavoriteAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_favorite, container, false);

        lvFavorite = view.findViewById(R.id.lvFavorite);
        adapter = new FavoriteAdapter((Activity) getContext(),R.layout.favorite_item,initData());
        lvFavorite.setAdapter(adapter);
        return view;
    }

    private ArrayList<Favorite> initData(){
        favorites = new ArrayList<Favorite>();
        favorites.add(new Favorite(R.drawable.img_bdcake, "Dumplings", 35000, 5, 15));
        favorites.add(new Favorite(R.drawable.img_cake, "Tarks trứng", 150000, 4.5, 25));
        favorites.add(new Favorite(R.drawable.img_mango_cake, "Gato", 100000, 5, 15));
        favorites.add(new Favorite(R.drawable.img_pink_cake, "Cup cake", 200000, 4.5, 10));
        favorites.add(new Favorite(R.drawable.img_summer_pudding, "strawberry cake", 350000, 5,5));
        favorites.add(new Favorite(R.drawable.img_bdcake, "Dumplings", 35000, 5, 15));
        favorites.add(new Favorite(R.drawable.img_mango_cake, "Gato", 100000, 5, 15));
        return favorites;

    }
}