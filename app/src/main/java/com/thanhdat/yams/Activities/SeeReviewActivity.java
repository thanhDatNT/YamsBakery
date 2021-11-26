package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.thanhdat.yams.Models.SeeReviewItem;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.SeeReviewAdapter;

import java.util.ArrayList;

public class SeeReviewActivity extends AppCompatActivity {

    RecyclerView rcvReviewItem;
    ArrayList<SeeReviewItem> items;
    SeeReviewAdapter adapter;

//    ImageButton imbBackToProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_review);

        linkViews();
        configRecyclerView();
        initData();
//        addEvent();

    }

    private void linkViews() {
        rcvReviewItem = findViewById(R.id.rcvReviewItem);
    }

    private void configRecyclerView() {
        //orientation
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcvReviewItem.setLayoutManager(manager);

        rcvReviewItem.setHasFixedSize(true);
        rcvReviewItem.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {
        items = new ArrayList<>();
        items.add(new SeeReviewItem("Như Quỳnh","Bánh rất ngon, vị đậm đà, vừa béo vừa thơm. Nói chung là ok ạ. Mọi người nên mua để có thể cảm nhận nha!","Kích cỡ: ","Size M - Đường kính 17cm","Chocolate","Toppings: ",R.drawable.img_photo1,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.img_product_detail));
        items.add(new SeeReviewItem("Mai Trang","Bánh rất ngon, Yams tư vấn rất nhiệt tình, mình rất thích","Kích cỡ: ","Size L - Đường kính 20cm","Fruit","Toppings: ",R.drawable.img_photo2,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.img_product_detail));
        items.add(new SeeReviewItem("Như Quỳnh","Bánh rất ngon, vị đậm đà, vừa béo vừa thơm. Nói chung là ok ạ. Mọi người nên mua để có thể cảm nhận nha!","Kích cỡ: ","Size M - Đường kính 17cm","Chocolate","Toppings: ",R.drawable.img_photo1,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.img_product_detail));
        items.add(new SeeReviewItem("Như Quỳnh","Bánh rất ngon, vị đậm đà, vừa béo vừa thơm. Nói chung là ok ạ. Mọi người nên mua để có thể cảm nhận nha!","Kích cỡ: ","Size M - Đường kính 17cm","Chocolate","Toppings: ",R.drawable.img_photo1,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.ic_star,R.drawable.img_product_detail));

        adapter = new SeeReviewAdapter(getApplicationContext(),items);

        rcvReviewItem.setAdapter(adapter);
    }

//    private void addEvent() {
//        imbBackToProduct.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(SeeReviewActivity.this,ProductDetailsActivity.class));
//            }
//        });
//    }


}