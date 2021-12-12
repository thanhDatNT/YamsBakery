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
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Interfaces.ItemtouchHelperListener;
import com.thanhdat.yams.Models.Cart;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapter.CartAdapter;
import com.thanhdat.yams.TouchHelper.RecycleviewCartTouchHelper;
import com.thanhdat.yams.TouchHelper.RecycleviewCartTouchHelper;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements ItemtouchHelperListener {
    RecyclerView rcvCart;
    CartAdapter adapter;
    ArrayList<Cart> carts;
    LinearLayout layoutItemCart;
    Toolbar toolbarCart;
    Button btnOrder;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        LinkView();
        navigate();
        addEventTouch();
        configRecycleView();
        getIntentAndSaveDB();

    }

    private void getIntentAndSaveDB() {
        products= MainActivity.productList;
        Intent intent= getIntent();
        Bundle bundle= intent.getBundleExtra(Constant.STRING_INTENT);
        if(bundle != null){
            int productID= bundle.getInt(Constant.ID_PRODUCT, 1);
            int quantity= bundle.getInt(Constant.QUANTITY_PRODUCT, 1);
            String topping= bundle.getString(Constant.TOPPING_PRODUCT, "");
            String size= bundle.getString(Constant.SIZE_PRODUCT, "M");
            double price= bundle.getDouble(Constant.PRICE_PRODUCT, 10);
            Product product= products.get(productID);
            String thumb= product.getThumbnail();
            String name= product.getName();
            int stock= product.getAvailable();
            Toast.makeText(this, productID + name, Toast.LENGTH_LONG).show();
        }
    }

    private void configRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcvCart.setLayoutManager(manager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcvCart.addItemDecoration(itemDecoration);

    }

    private void addEventTouch() {
        ItemTouchHelper.SimpleCallback simpleCallback = new RecycleviewCartTouchHelper(0,ItemTouchHelper.LEFT,this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(rcvCart);
    }

    public class ThreadGetMoreData extends  Thread{}

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof CartAdapter.CartViewHolder){
            String nameDelete=carts.get(viewHolder.getAdapterPosition()).getProductName();
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


    private void navigate() {
        setSupportActionBar(toolbarCart);
        getSupportActionBar().setTitle(null);
        toolbarCart.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this,PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void LinkView() {
        rcvCart = findViewById(R.id.rcvCart);
        layoutItemCart = findViewById(R.id.layoutItemCart);
        toolbarCart=findViewById(R.id.toolbarCart);
        btnOrder = findViewById(R.id.btnOrder);

    }
}