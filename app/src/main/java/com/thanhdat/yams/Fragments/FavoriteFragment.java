package com.thanhdat.yams.Fragments;

import static com.thanhdat.yams.Activities.MainActivity.productList;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.thanhdat.yams.Activities.MainActivity;
import com.thanhdat.yams.Activities.WriteReviewActivity;
import com.thanhdat.yams.Adapters.CategoryProductAdapter;

import com.thanhdat.yams.Adapters.FavoriteAdapter;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

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
        addEvent();
        addEventRemove();
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

    private void addEventRemove() {
//        chkLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                builder.setIcon(R.drawable.ic_warning);
//                builder.setTitle("Xác nhận");
//                builder.setMessage("Bạn chắc chắn muốn xóa khỏi danh sách yêu thích?");
//
//                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //delete item
//
//                    }
//                });
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //cancel delete
//                        dialogInterface.dismiss();
//                    }
//                });
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });
    }



}