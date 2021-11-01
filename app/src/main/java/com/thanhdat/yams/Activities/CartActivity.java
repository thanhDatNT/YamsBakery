package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.thanhdat.yams.Models.Cart;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.CartAdapter;
import com.thanhdat.yams.adapter.DietAdapter;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    ListView lvCartProduct;
    ArrayList<Cart> carts;
    CartAdapter adapter;
    ImageView imvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        LinkView();
        initData();
        loadData();
        backTab();
    }


    private void LinkView() {
        lvCartProduct=findViewById(R.id.lvCartProduct);
        imvBack = findViewById(R.id.imvBack);
    }
    private void initData() {
        carts = new ArrayList<Cart>();
        carts.add(new Cart(R.drawable.fruitcake,"Fruit Cake","M",230000,1,10));
        carts.add(new Cart(R.drawable.cake_chocolate,"Chocolate Cake","S",200000,1,10));
        carts.add(new Cart(R.drawable.cake2,"Tart Egg","S",20000,2,20));
        carts.add(new Cart(R.drawable.fruitcake,"Fruit Cake","M",230000,1,10));
        carts.add(new Cart(R.drawable.fruitcake,"Fruit Cake","M",230000,1,10));
        carts.add(new Cart(R.drawable.fruitcake,"Fruit Cake","M",230000,1,10));
        carts.add(new Cart(R.drawable.fruitcake,"Fruit Cake","M",230000,1,10));
    }
    private void loadData() {
        adapter=new CartAdapter(CartActivity.this,R.layout.items_cart,carts);
        lvCartProduct.setAdapter(adapter);


    }
    private void backTab() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}