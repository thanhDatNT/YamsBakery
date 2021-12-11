package com.thanhdat.yams.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.thanhdat.yams.Models.Category;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.Models.TextThumbView;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapter.ViewPagerMainAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private ProgressBar progressBar;
    private TextView tvWaiting;
    public static final String TAG= MainActivity.class.getSimpleName();
    public static ArrayList<Product> productList;
    public static ArrayList<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        requestCategoryDataAPI();
        requestProductDataAPI();
    }

    private void requestCategoryDataAPI() {
        categoryList= new ArrayList<>();
        RequestQueue queue= Volley.newRequestQueue(this);
        String url= "https://thanhdatnt.github.io/database/category.json";
        JsonObjectRequest objectRequest= new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("category");
                            for(int i=0; i<array.length(); i++){
                                JSONObject object= array.getJSONObject(i);
                                Category item=new Category();
                                item.setId(object.getInt("id"));
                                item.setName(object.getString("name"));
                                item.setThumb(object.getString("thumb"));
                                categoryList.add(item);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error + "");
                    }
                });
        queue.add(objectRequest);
    }

    private void requestProductDataAPI() {
        productList= new ArrayList<>();
        RequestQueue queue= Volley.newRequestQueue(this);
        String url= "https://thanhdatnt.github.io/database/productsDB.json";
        JsonObjectRequest objectRequest= new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ArrayList<String> topping= new ArrayList<>();
                        try {
                            JSONArray array= response.getJSONArray("product");
                            for(int i=0; i<array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                Product p= new Product();
                                p.setId(object.getInt("id"));
                                p.setName(object.getString("name"));
                                p.setDescription(object.getString("description"));
                                p.setTag(object.getString("tag"));
                                p.setPrice(object.getDouble("price"));
                                p.setCurrentPrice(object.getDouble("currentPrice"));
                                p.setRating(object.getDouble("rating"));
                                p.setChecked(object.getInt("checked"));
                                p.setThumbnail(object.getString("image"));
                                p.setAvailable(object.getBoolean("isAvailable"));
                                p.setFavorite(object.getBoolean("isFavorite"));
                                p.setPromo(object.getBoolean("isPromo"));
                                p.setAvailable(object.getInt("available"));
                                p.setBuy(object.getString("buyed"));
                                JSONArray arrTopping= object.getJSONArray("toping");
                                for(int j=0; j<arrTopping.length(); j++){
                                    topping.add(arrTopping.getString(j));
                                }
                                p.setTopping(topping);
                                p.setCategory(object.getString("category"));
                                p.setDiet(object.getString("diet"));
                                productList.add(p);
                            }
                            prepareForLaunching();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error + "");
                    }
                });
        queue.add(objectRequest);
    }

    private void prepareForLaunching() {
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(productList.size() > 0 && categoryList.size()>0){
                    setUpViewPager();
                    navigateTabs();
                    progressBar.setVisibility(View.GONE);
                    tvWaiting.setVisibility(View.GONE);
                    navigationView.setVisibility(View.VISIBLE);
                }
            }
        },1000);
    }

    private void setUpViewPager() {
        ViewPagerMainAdapter viewPagerMainAdapter= new ViewPagerMainAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerMainAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigationView.getMenu().findItem(R.id.homeNav).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.favoriteNav).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.dietNav).setChecked(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.feedNav).setChecked(true);
                        break;
                    case 4:
                        navigationView.getMenu().findItem(R.id.accountNav).setChecked(true);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void navigateTabs() {
//      Handle when click navigation buttons
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeNav:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.favoriteNav:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.dietNav:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.feedNav:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.accountNav:
                        viewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }

    private void linkViews() {
        navigationView= findViewById(R.id.navigationMain);
        viewPager= findViewById(R.id.layoutContainerMain);
        progressBar= findViewById(R.id.progressBarHome);
        tvWaiting= findViewById(R.id.tvWaiting);
    }
}