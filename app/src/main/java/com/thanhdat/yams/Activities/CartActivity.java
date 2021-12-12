package com.thanhdat.yams.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;

import android.widget.LinearLayout;


import com.google.android.material.snackbar.Snackbar;
import com.thanhdat.yams.Interfaces.ItemtouchHelperListener;
import com.thanhdat.yams.Models.Cart;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapter.CartAdapter;
import com.thanhdat.yams.TouchHelper.RecycleviewCartTouchHelper;
import com.thanhdat.yams.TouchHelper.RecycleviewCartTouchHelper;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements ItemtouchHelperListener {
    RecyclerView rcvCart;
    //    ArrayList<Cart> carts;
    CartAdapter adapter;
    ArrayList<Cart>carts = new ArrayList<>();
    LinearLayout layoutItemCart;
    Toolbar toolbarCart;
    Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        LinkView();
        initData();
        addEventTouch();
        configRecycleView();

    }



    private void LinkView() {
        rcvCart = findViewById(R.id.rcvCart);
        layoutItemCart = findViewById(R.id.layoutItemCart);
        toolbarCart=findViewById(R.id.toolbarCart);
        btnOrder = findViewById(R.id.btnOrder);

    }
    private void configRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcvCart.setLayoutManager(manager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcvCart.addItemDecoration(itemDecoration);

    }
    private void initData() {

        carts.add(new Cart(1,R.drawable.img_mango_cake,"Birthday Cake","M",70000,1,10));
        carts.add(new Cart(2,R.drawable.img_summer_pudding,"Summer Pudding","M",100000,1,10));
        carts.add(new Cart(3,R.drawable.img_matcha_maracon,"Mango Cake","M",30000,1,10));
        carts.add(new Cart(4,R.drawable.img_matcha_maracon,"Pink Cake","M",60000,1,10));
        carts.add(new Cart(5,R.drawable.img_mango_cake,"Mango Cake","M",20000,1,10));
        carts.add(new Cart(6,R.drawable.img_cake,"Mango Cake","M",10000,1,10));
        carts.add(new Cart(7,R.drawable.img_mango_cake,"Mango Cake","M",90000,1,10));
        carts.add(new Cart(8,R.drawable.img_mango_cake,"Mango Cake","M",150000,1,10));
        adapter = new CartAdapter(getApplicationContext(),carts);
        rcvCart.setAdapter(adapter);
    }
    private void addEventTouch() {
        ItemTouchHelper.SimpleCallback simpleCallback = new RecycleviewCartTouchHelper(0,ItemTouchHelper.LEFT,this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(rcvCart);
    }

    private  ArrayList<Cart> getMoreData(){
        ArrayList<Cart>arrayList= new ArrayList<>();

        return arrayList;

    }
    public class ThreadGetMoreData extends  Thread{}

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof CartAdapter.CartViewHolder){
            String nameDelete=carts.get(viewHolder.getAdapterPosition()).getCartName();
            Cart cartItemDelete=carts.get(viewHolder.getAdapterPosition());
            int indexDelete = viewHolder.getAdapterPosition();

            //remove item
            adapter.removeItem(indexDelete);
            Snackbar snackbar = Snackbar.make(layoutItemCart,nameDelete +" "+"removed", Snackbar.LENGTH_LONG);
            snackbar.setAction("Undo", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.undoItem(cartItemDelete,indexDelete);
                }
            });
            snackbar.setActionTextColor(Color.BLUE).show();
        }
    }

    private void AddToPayment() {
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this,PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void backTab() {
        setSupportActionBar(toolbarCart);
        getSupportActionBar().setTitle(null);
        toolbarCart.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


}