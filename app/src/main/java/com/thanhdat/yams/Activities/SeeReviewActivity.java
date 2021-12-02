package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.thanhdat.yams.Models.SeeReviewItem;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapter.SeeReviewAdapter;

import java.util.ArrayList;

public class SeeReviewActivity extends AppCompatActivity {

    RecyclerView rcvReviewItem;
    ArrayList<SeeReviewItem> items;
    SeeReviewAdapter adapter;

    Toolbar toolbarSeeReview;
    RatingBar rtbRating;
    TextView txtAvgRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_review);

        linkViews();
        configRecyclerView();
        initData();
        addEventBack();
        addEventRating();

    }

    private void linkViews() {
        rcvReviewItem = findViewById(R.id.rcvReviewItem);
        toolbarSeeReview = findViewById(R.id.toolbarSeeReview);
        rtbRating = findViewById(R.id.rtbRating);
        txtAvgRating = findViewById(R.id.txtAvgRating);
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
        items.add(new SeeReviewItem("Như Quỳnh","Bánh rất ngon, vị đậm đà, vừa béo vừa thơm. Nói chung là ok ạ. Mọi người nên mua để có thể cảm nhận nha!","Size M - Đường kính 17cm","Chocolate",R.drawable.img_photo1,R.drawable.img_product_detail,4.5));
        items.add(new SeeReviewItem("Mai Trang","Bánh rất ngon, Yams tư vấn rất nhiệt tình, mình rất thích","Size L - Đường kính 20cm","Fruit",R.drawable.img_photo2,R.drawable.img_product_detail,5));
        items.add(new SeeReviewItem("Như Quỳnh","Bánh rất ngon, vị đậm đà, vừa béo vừa thơm. Nói chung là ok ạ. Mọi người nên mua để có thể cảm nhận nha!","Size M - Đường kính 17cm","Chocolate",R.drawable.img_photo1,R.drawable.img_product_detail,4.5));
        items.add(new SeeReviewItem("Mai Trang","Bánh rất ngon, Yams tư vấn rất nhiệt tình, mình rất thích","Size L - Đường kính 20cm","Fruit",R.drawable.img_photo2,R.drawable.img_product_detail,4));
        items.add(new SeeReviewItem("Như Quỳnh","Bánh rất ngon, vị đậm đà, vừa béo vừa thơm. Nói chung là ok ạ. Mọi người nên mua để có thể cảm nhận nha!","Size M - Đường kính 17cm","Chocolate",R.drawable.img_photo1,R.drawable.img_product_detail,4.5));
        items.add(new SeeReviewItem("Mai Trang","Bánh rất ngon, Yams tư vấn rất nhiệt tình, mình rất thích","Size L - Đường kính 20cm","Fruit",R.drawable.img_photo2,R.drawable.img_product_detail,5));


        adapter = new SeeReviewAdapter(getApplicationContext(),items);

        rcvReviewItem.setAdapter(adapter);
    }

    private void addEventBack() {
        setSupportActionBar(toolbarSeeReview);
        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(null);
        toolbarSeeReview.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SeeReviewActivity.this,ProductDetailsActivity.class));
            }
        });
    }

    private void addEventRating() {
        float rating = 0;
        //to do?

        rtbRating.setRating(Float.parseFloat(txtAvgRating.getText().toString()));

    }


}