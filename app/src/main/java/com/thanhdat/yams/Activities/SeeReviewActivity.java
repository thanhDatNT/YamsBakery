package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.thanhdat.yams.Databases.ReviewDatabase;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.thanhdat.yams.Models.Cart;
import com.thanhdat.yams.Models.Category;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.Models.SeeReviewItem;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapters.SeeReviewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.BitSet;

public class SeeReviewActivity extends AppCompatActivity {

    RecyclerView rcvReviewItem;
    Toolbar toolbarSeeReview;
    RatingBar rtbRating;
    TextView txtAvgRating;
//    public static final String TAG= MainActivity.class.getSimpleName();
    ArrayList<SeeReviewItem> items;
    SeeReviewAdapter adapter;
    public static ReviewDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_review);

//        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        db = new ReviewDatabase(this);
        items = new ArrayList<SeeReviewItem>();

        linkViews();
        addEventBack();
        //requestReviewsDataAPI();
        configRecyclerView();
        insertDB();
        loadData();
        addEventRating();
    }

    private void linkViews() {
        rcvReviewItem = findViewById(R.id.rcvReviewItem);
        toolbarSeeReview = findViewById(R.id.toolbarSeeReview);
        rtbRating = findViewById(R.id.rtbRating);
        txtAvgRating = findViewById(R.id.txtAvgRating);
    }


    private void addEventBack() {
        setSupportActionBar(toolbarSeeReview);
        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(null);
        toolbarSeeReview.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(SeeReviewActivity.this,ProductDetailsActivity.class));
                finish();
            }
        });
    }

//    private void requestReviewsDataAPI() {
//        RequestQueue queue= Volley.newRequestQueue(this);
//        String url= "https://thanhdatnt.github.io/database/reviews.json";
//        JsonObjectRequest objectRequest= new JsonObjectRequest(Request.Method.GET, url, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray array= response.getJSONArray("reviews");
//                            for(int i=0; i<array.length(); i++) {
//                                JSONObject object = array.getJSONObject(i);
//                                SeeReviewItem review = new SeeReviewItem();
//                                review.setId(object.getInt("id"));
//                                review.setRating(object.getDouble("rating"));
//                                review.setProfile(convertByteArray(object.getString("profile")));
//                                review.setName(object.getString("name"));
//                                review.setContent(object.getString("content"));
//                                review.setProfile(convertByteArray(object.getString("image")));
//                                review.setSize(object.getString("size"));
//                                items.add(review);
//                            }
//
//                        } catch (JSONException | MalformedURLException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.e(TAG, error + "");
//                    }
//                });
//        queue.add(objectRequest);
//    }
//
//    private byte[] convertByteArray(String string) throws IOException {
//        URL url = new URL(string);
//        InputStream inputStream = url.openConnection().getInputStream();
//        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
//        return outputStream.toByteArray();
//    }

    private void configRecyclerView() {
        //orientation
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rcvReviewItem.setLayoutManager(manager);
        rcvReviewItem.setHasFixedSize(true);
        rcvReviewItem.setItemAnimator(new DefaultItemAnimator());
    }

    private void insertDB() {
        if(db.getCount() == 0){
            db.insertData(4.5, convertByteArray(R.drawable.img_logo_launcher), "Minh Xuân", "Bánh ngon lắm, mọi người nên mua ăn thử nha!", convertByteArray(R.drawable.img_mango_cake), "Size M");
            db.insertData(5, convertByteArray(R.drawable.img_logo_pink), "Mai Trang", "Bánh khá là ngon, thơm nữa, 5 sao nha", convertByteArray(R.drawable.img_matcha_maracon), "Size L");
            db.insertData(5, convertByteArray(R.drawable.img_bdcake), "Uyển Nhi", "Ngon bổ rẻ nha cả nhà iu của kem!", convertByteArray(R.drawable.img_bdcake), "Size XL");
            db.insertData(4, convertByteArray(R.drawable.img_chef), "Thành Đạt", "Tuyệt vời!", convertByteArray(R.drawable.img_cake), "Size L");
        }

    }
    private byte[] convertByteArray(int id){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }

    private void loadData() {

        Cursor cursor = db.getData("SELECT * FROM " + ReviewDatabase.TBL_NAME);
        items.clear();
        while (cursor.moveToNext()){
            items.add(new SeeReviewItem(cursor.getInt(0), cursor.getDouble(1), cursor.getBlob(2), cursor.getString(3), cursor.getString(4), cursor.getBlob(5), cursor.getString(6)));
        }
        cursor.close();

        adapter = new SeeReviewAdapter(this,items);
        rcvReviewItem.setAdapter(adapter);
    }

    @SuppressLint("SetTextI18n")
    private void addEventRating() {
        double avgRating = 0;
        Cursor cursor = db.getData("SELECT AVG(" + ReviewDatabase.COL_R_RATING + ") as Average FROM " + ReviewDatabase.TBL_NAME);
        if (cursor.moveToFirst()){
            avgRating = cursor.getDouble(cursor.getColumnIndex("Average"));
        }
        cursor.close();
        txtAvgRating.setText((Math.ceil(avgRating * 10) / 10) + "");
        rtbRating.setRating((float) (Math.ceil(avgRating * 10) / 10));
    }

}