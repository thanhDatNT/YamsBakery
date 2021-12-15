package com.thanhdat.yams.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Database.CartDatabase;
import com.thanhdat.yams.Interfaces.ItemtouchHelperListener;
import com.thanhdat.yams.Interfaces.OnClickInterface;
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
    ConstraintLayout layoutItemCart;
    Toolbar toolbarCart;
    Button btnOrder;
    CheckBox chkChooseAll;
    TextView tvTotalCart;
    ArrayList<Product> products;
    ArrayList<Cart> purchasingItems;
    OnClickInterface onClickInterface;
    double totalCart;
    public static CartDatabase cartDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartDatabase = new CartDatabase(this);
        purchasingItems = new ArrayList<>();
        LinkView();
        getIntentAndSaveDB();
        navigate();
        addEvent();
        configRecycleView();
        loadCartData();
    }

    private void loadCartData() {
        carts= new ArrayList<>();
        Cursor cursor= cartDatabase.getData("SELECT * FROM "+ cartDatabase.TABLE_NAME);
        carts.clear();
        while (cursor.moveToNext()){
            carts.add(new Cart(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getDouble(7)));
        }
        cursor.close();
        adapter = new CartAdapter(this, carts, onClickInterface);
        rcvCart.setAdapter(adapter);
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

//          Save product from intent to Cart Database
            boolean isSaved = cartDatabase.insertData(name, thumb, size, topping, quantity, stock, price);
            if(isSaved)
                Log.i("SAVE DB", "This product has been added");
        }
    }

    private void configRecycleView() {
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcvCart.setLayoutManager(manager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcvCart.addItemDecoration(itemDecoration);
    }

    private void addEvent() {
//        Touch Event
        ItemTouchHelper.SimpleCallback simpleCallback = new RecycleviewCartTouchHelper(0,ItemTouchHelper.LEFT,this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(rcvCart);

//        Check All Items Event
        chkChooseAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
//                    Check All Item
                    purchasingItems.clear();
                    purchasingItems.addAll(carts);
                    totalCart = 0;
                    for (int i= 0; i<carts.size(); i++){
                        totalCart += carts.get(i).getPrice();
                        if (!carts.get(i).isChecked()){
                            carts.get(i).setChecked(true);
                            adapter.notifyItemChanged(i);
                        }
                    }
                    tvTotalCart.setText(String.format("%.0f", totalCart));
                }
                else {
//                    Uncheck All
                    totalCart = 0;
                    tvTotalCart.setText("0");
                    for (int i= 0; i<carts.size(); i++){
                        if (carts.get(i).isChecked()){
                            carts.get(i).setChecked(false);
                            adapter.notifyItemChanged(i);
                        }
                    }
                    purchasingItems.clear();
                    tvTotalCart.setText(String.format("%.0f", totalCart));
                }
            }
        });

        onClickInterface = number -> {
            if (carts.get(number).isChecked()){
                if (!purchasingItems.contains(carts.get(number))){
                    totalCart = 0;
                    purchasingItems.add(carts.get(number));
                    for (int i= 0; i<purchasingItems.size(); i++){
                        totalCart += purchasingItems.get(i).getPrice();
                    }
                }
            }
            else {
                if (purchasingItems.contains(carts.get(number))){
                    purchasingItems.remove(carts.get(number));
                    totalCart -= carts.get(number).getPrice();
                }
                if (chkChooseAll.isChecked()){
                    chkChooseAll.setChecked(false);
                }
            }
            tvTotalCart.setText(String.format("%.0f", totalCart));
        };
    }

    public class ThreadGetMoreData extends  Thread{}

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof CartAdapter.CartViewHolder){
            String nameDelete = carts.get(viewHolder.getAdapterPosition()).getProductName();
            Cart cartItemDelete = carts.get(viewHolder.getAdapterPosition());
            int indexDelete = viewHolder.getAdapterPosition();

            // Remove item when swiping
            cartDatabase.execSQL("DELETE FROM " + cartDatabase.TABLE_NAME + " WHERE "+ cartDatabase.COL_NAME + " = '"+ carts.get(indexDelete).getProductName() + "'");
            loadCartData();
            Snackbar snackbar = Snackbar.make(layoutItemCart,nameDelete +" was removed", Snackbar.LENGTH_LONG);
            snackbar.setAction("Undo", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     adapter.undoItem(cartItemDelete, indexDelete);

                    boolean isAdded = cartDatabase.insertData(carts.get(indexDelete).getProductName(), carts.get(indexDelete).getThumb(), carts.get(indexDelete).getProductSize(), carts.get(indexDelete).getTopping(), carts.get(indexDelete).getQuantity(), carts.get(indexDelete).getAvailable(), carts.get(indexDelete).getPrice());

                    if(isAdded)
                        loadCartData();
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
        toolbarCart = findViewById(R.id.toolbarCart);
        btnOrder = findViewById(R.id.btnOrder);
        chkChooseAll= findViewById(R.id.chkChooseAll);
        tvTotalCart= findViewById(R.id.tvTotalCart);
    }
}