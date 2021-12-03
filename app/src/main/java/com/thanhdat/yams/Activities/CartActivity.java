package com.thanhdat.yams.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.thanhdat.yams.Models.Cart;
import com.thanhdat.yams.R;
<<<<<<< Updated upstream
import com.thanhdat.yams.Adapter.CartAdapter;
=======
import com.thanhdat.yams.Adapters.CartAdapter;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    ListView lvCartProduct;
    ArrayList<Cart> carts;
    CartAdapter adapter;
    Toolbar toolbarCart;
    Button btnOrder;
    Handler mHandler;
    View  footerView;
    int currentId=0;
    boolean isLoading=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        LinkView();
        initData();
        loadData();
        addEvent();
        AddToPayment();
        backTab();
    }


    private void addEvent() {
        lvCartProduct.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }
// i: firstItem, i1:visibleItemCount, i2:TotalItemCount
            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if(absListView.getLastVisiblePosition()==i2-1 && isLoading==false){
                    isLoading=true;
                    Thread thread = new ThreadGetMoreData();
                    thread.start();
                }
            }
        });
    }


    private void LinkView() {
        lvCartProduct=findViewById(R.id.lvCartProduct);
        toolbarCart=findViewById(R.id.toolbarCart);
        btnOrder = findViewById(R.id.btnOrder);

    }
    private void initData() {
        carts = new ArrayList<Cart>();
        carts.add(new Cart(1,R.drawable.img_mango_cake,"Fruit Cake","M",230000,1,10));
        carts.add(new Cart(2,R.drawable.img_cake,"Chocolate Cake","S",200000,1,10));
        carts.add(new Cart(3,R.drawable.img_summer_pudding,"Tart Egg","S",20000,2,20));
        carts.add(new Cart(4,R.drawable.img_mango_cake,"Fruit Cake","M",230000,1,10));
        carts.add(new Cart(5,R.drawable.img_mango_cake,"Fruit Cake","M",230000,1,10));
        carts.add(new Cart(6,R.drawable.img_mango_cake,"Fruit Cake","M",230000,1,10));
        carts.add(new Cart(7,R.drawable.img_mango_cake,"Fruit Cake","M",230000,1,10));
        carts.add(new Cart(8,R.drawable.img_mango_cake,"Fruit Cake","M",230000,1,10));
        carts.add(new Cart(9,R.drawable.img_mango_cake,"Fruit Cake","M",230000,1,10));

    }
    private void loadData() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerView = inflater.inflate(R.layout.footer_view_cart,null);
        mHandler = new mHandler();

        adapter=new CartAdapter(CartActivity.this,R.layout.items_cart,carts);
        lvCartProduct.setAdapter(adapter);

    }
    //tao class
    public class mHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
           switch (msg.what){
               case 0:
                   lvCartProduct.addFooterView(footerView);
                   break;
               case 1:
                   //update data adapter and UI
                   adapter.AddListItemAdapter((ArrayList<Cart>)msg.obj);
                   //remove loading view after update listview
                   lvCartProduct.removeFooterView(footerView);
                   isLoading = false;
                   break;
               default:
                   break;

           }
        }
    }
    private  ArrayList<Cart> getMoreData(){
        ArrayList<Cart>arrayList= new ArrayList<>();
        arrayList.add(new Cart(++currentId,R.drawable.img_cake,"Fruit Cake","M",230000,1,10));
        arrayList.add(new Cart(++currentId,R.drawable.img_cake,"Chocolate Cake","S",200000,1,10));
        arrayList.add(new Cart(++currentId,R.drawable.img_summer_pudding,"Tart Egg","S",20000,2,20));
        arrayList.add(new Cart(++currentId,R.drawable.img_pink_cake,"Fruit Cake","M",230000,1,10));
       arrayList.add(new Cart(++currentId,R.drawable.img_mango_cake,"Fruit Cake","M",230000,1,10));
        arrayList.add(new Cart(++currentId,R.drawable.img_mango_cake,"Fruit Cake","M",230000,1,10));
        return arrayList;

    }
    public class ThreadGetMoreData extends  Thread{
    @Override
    public void run() {
        //add footer view after get data
        mHandler.sendEmptyMessage(0);
        //search more data
        ArrayList<Cart> lstResult =getMoreData();
        //Delay time to show loading footer when debug, remove it when release
        try {
            Thread.sleep(3000);//sau 3s thực hiện gửi dữ liệu và tin nhan cho hanler
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //send the result to handle
        Message msg = mHandler.obtainMessage(1,lstResult);
        mHandler.sendMessage(msg);
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