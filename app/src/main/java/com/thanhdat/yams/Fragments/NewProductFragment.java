package com.thanhdat.yams.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import com.thanhdat.yams.Models.NewProduct;
import com.thanhdat.yams.R;
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

        lvNewProduct = view.findViewById(R.id.lvNewProduct);
        adapter = new NewProductAdapter((Activity) getContext(),R.layout.item_new_product,initData());
        lvNewProduct.setAdapter(adapter);
        return view;
    }

    private ArrayList<NewProduct> initData(){
        newProducts = new ArrayList<NewProduct>();
        newProducts.add(new NewProduct(R.drawable.img_bdcake, "Dumplings", "Lớp bánh mềm cùng bột phủ matcha tạo hương vị độc đáo", "New", 35000, 4.5));
        newProducts.add(new NewProduct(R.drawable.img_mango_cake, "Mango Cake", "Lớp bánh mềm cùng bột phủ matcha tạo hương vị độc đáo", "New", 35000, 4.5));
        newProducts.add(new NewProduct(R.drawable.img_bdcake, "Dumplings", "Lớp bánh mềm cùng bột phủ matcha tạo hương vị độc đáo", "New", 35000, 5));
        newProducts.add(new NewProduct(R.drawable.img_bdcake, "Dumplings", "Lớp bánh mềm cùng bột phủ matcha tạo hương vị độc đáo", "New", 35000, 5));
        newProducts.add(new NewProduct(R.drawable.img_bdcake, "Dumplings", "Lớp bánh mềm cùng bột phủ matcha tạo hương vị độc đáo", "New", 35000, 5));
        newProducts.add(new NewProduct(R.drawable.img_bdcake, "Dumplings", "Lớp bánh mềm cùng bột phủ matcha tạo hương vị độc đáo", "New", 45000, 4.5));
        newProducts.add(new NewProduct(R.drawable.img_bdcake, "Dumplings", "Lớp bánh mềm cùng bột phủ matcha tạo hương vị độc đáo", "New", 45000, 4.5));

        return newProducts;

    }
}