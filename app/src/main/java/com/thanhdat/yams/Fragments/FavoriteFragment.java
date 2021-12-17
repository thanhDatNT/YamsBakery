package com.thanhdat.yams.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.thanhdat.yams.Models.Favorite;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapters.FavoriteAdapter;

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
        adapter = new FavoriteAdapter((Activity) getContext(),R.layout.item_favorite,initData());
        lvFavorite.setAdapter(adapter);
        return view;
    }


    private ArrayList<Favorite> initData(){
        favorites = new ArrayList<Favorite>();
        favorites.add(new Favorite(R.drawable.img_bdcake, "Dumplings", 35000, 5, 15));
        favorites.add(new Favorite(R.drawable.img_cake, "Tarks trứng", 150000, 4.5, 25));
        favorites.add(new Favorite(R.drawable.img_mango_cake, "Gato", 100000, 5, 15));
        favorites.add(new Favorite(R.drawable.img_bdcake, "Cup cake", 200000, 4.5, 10));
        favorites.add(new Favorite(R.drawable.img_summer_pudding, "strawberry cake", 350000, 5,5));
        favorites.add(new Favorite(R.drawable.img_bdcake, "Dumplings", 35000, 5, 15));
        favorites.add(new Favorite(R.drawable.img_mango_cake, "Gato", 100000, 5, 15));
        return favorites;

    }
}