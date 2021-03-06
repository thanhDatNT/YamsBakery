package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
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
import android.util.Log;
import android.view.View;

import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Databases.CartDatabase;
import com.thanhdat.yams.Interfaces.ItemtouchHelperListener;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Cart;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapters.CartAdapter;
import com.thanhdat.yams.Utils.RecycleviewCartTouchHelper;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements ItemtouchHelperListener {
    ConstraintLayout layoutItemCart;
    Toolbar toolbarCart;
    AppCompatButton btnOrder;
    CheckBox chkChooseAll;
    TextView tvTotalCart, tvEmptyCart;

    ArrayList<Product> products;
    public static ArrayList<Cart> purchasingItems, carts;
    RecyclerView rcvCart;
    CartAdapter adapter;
    OnClickInterface onClickInterface;

    double totalCart, itemPrice;
    int quantity;
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
        loadCartData();
        configLayout();
    }

    private void loadCartData() {
        carts= new ArrayList<>();
        Cursor cursor= cartDatabase.getData("SELECT * FROM "+ cartDatabase.TABLE_NAME);
        carts.clear();
        while (cursor.moveToNext()){
            carts.add(new Cart(cursor.getInt(0), cursor.getInt(1),  cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getDouble(8)));
        }
        cursor.close();
        adapter = new CartAdapter(this, carts, onClickInterface);
    }

    private void getIntentAndSaveDB() {
        products= MainActivity.productList;
        Intent intent= getIntent();
        Bundle bundle= intent.getBundleExtra(Constant.STRING_INTENT);
        if(bundle != null){
            int productID= bundle.getInt(Constant.ID_PRODUCT, 1);
            quantity= bundle.getInt(Constant.QUANTITY_PRODUCT, 1);
            String topping= bundle.getString(Constant.TOPPING_PRODUCT, "");
            String size= bundle.getString(Constant.SIZE_PRODUCT, "M");
            itemPrice= bundle.getDouble(Constant.PRICE_PRODUCT, 10);
            Product product = new Product();
            for(Product p: products){
                if(p.getId() == productID) {
                    product = p;
                    break;
                }
            }
            String thumb= product.getThumbnail();
            String name= product.getName();
            int stock= product.getAvailable();

//          Save product from intent to Cart Database
            boolean isSaved = cartDatabase.insertData(productID, name, thumb, size, topping, quantity, stock, itemPrice);
            if(isSaved)
                Log.i("SAVE DB", "This product has been added");
        }
    }

    private void configLayout() {
        if(carts.size() > 0){
            tvEmptyCart.setVisibility(View.GONE);
            LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            rcvCart.setLayoutManager(manager);
            RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
            rcvCart.addItemDecoration(itemDecoration);
            rcvCart.setAdapter(adapter);
        }
        else {
            rcvCart.setVisibility(View.GONE);
        }
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
            cartDatabase.execSQL("DELETE FROM " + cartDatabase.TABLE_NAME + " WHERE "+ cartDatabase.COL_PRO_ID + " = "+ carts.get(indexDelete).getProductID());
            loadCartData();
            Snackbar snackbar = Snackbar.make(layoutItemCart,nameDelete +" was removed", Snackbar.LENGTH_LONG);
            snackbar.setAction("Undo", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     adapter.undoItem(cartItemDelete, indexDelete);

                    boolean isAdded = cartDatabase.insertData(carts.get(indexDelete).getProductID(),carts.get(indexDelete).getProductName(), carts.get(indexDelete).getThumb(), carts.get(indexDelete).getProductSize(), carts.get(indexDelete).getTopping(), carts.get(indexDelete).getQuantity(), carts.get(indexDelete).getAvailable(), carts.get(indexDelete).getPrice());

                    if(isAdded)
                        loadCartData();
                }
            });
            snackbar.setActionTextColor(Color.YELLOW).show();
        }
    }

    public void updateQuantity(Cart cart, String flag){
        Cursor cursor= cartDatabase.getData("SELECT "+ cartDatabase.COL_PRICE +", "+ cartDatabase.COL_QUANTITY +" FROM "+ cartDatabase.TABLE_NAME + " WHERE "+ cartDatabase.COL_PRO_ID +"= " + cart.getProductID());
        while (cursor.moveToNext()){
            itemPrice = cursor.getDouble(0);
            quantity = cursor.getInt(1);
        }
        cursor.close();
        double productPrice = products.get(cart.getProductID() - 1).getCurrentPrice();
        if (flag.equals("plus")){
            itemPrice = itemPrice + productPrice;
            quantity ++;
        }
        else if (flag.equals("minus")){
            if (quantity > 1 ){
                itemPrice = itemPrice - productPrice;
                quantity --;
            }
            else {
                Toast.makeText(CartActivity.this, "Tr?????t ngang ????? x??a!", Toast.LENGTH_LONG).show();
            }
        }
//        Update Cart Database
        cartDatabase.execSQL("UPDATE " + cartDatabase.TABLE_NAME+ " SET "+ cartDatabase.COL_PRICE+" = " + itemPrice +", "+ cartDatabase.COL_QUANTITY + " = "+ quantity +" WHERE "+ cartDatabase.COL_ID + "= "+ cart.getId());
        loadCartData();
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
                if (purchasingItems.size() > 0){
                    Intent intent = new Intent(CartActivity.this, OrderActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.translate_slide_enter,R.anim.translate_slide_exit);
                }
                else {
                    Toast.makeText(CartActivity.this, "Vui l??ng ch???n s???n ph???m", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void LinkView() {
        rcvCart = findViewById(R.id.rcvCart);
        layoutItemCart = findViewById(R.id.layoutItemCart);
        toolbarCart = findViewById(R.id.toolbarCart);
        btnOrder = findViewById(R.id.btnOrder);
        chkChooseAll= findViewById(R.id.chkChooseAll);
        tvEmptyCart = findViewById(R.id.tvEmptyCart);
        tvTotalCart= findViewById(R.id.tvTotalCart);
    }
}