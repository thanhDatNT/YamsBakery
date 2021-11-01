package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.thanhdat.yams.Models.Banner;
import com.thanhdat.yams.Models.SimpleViewGroup;
import com.thanhdat.yams.Models.NewProduct;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.SimpleViewGroupAdapter;
import com.thanhdat.yams.adapter.NewProductAdapter;
import com.thanhdat.yams.adapter.SliderBannerAdapter;
import com.thanhdat.yams.adapter.SuggestionAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SliderView sliderBanner;
    private RecyclerView rcvNewProduct, rcvPopular, rcvPromotion;
    private GridView gvCategory, gvSuggestion;
    private ImageButton imbCart;
    EditText edtSearch;
    private LinearLayout favoriteTab, feedTab, dietTab, profileTab;
    ImageButton imbNotify;
    private AppCompatButton btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linkViews();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        edtSearch.clearFocus();
        navigateTabs();
        addEventSliderBanner();
        addEventNewProduct();
        addEventCategory();
        addEventPopular();
        addEventPromotion();
        addEventSuggestion();
    }

    private void addEventSuggestion() {
        ArrayList<NewProduct> newProducts= new ArrayList<>();
        newProducts.add(new NewProduct(R.drawable.img_summer_pudding, "Matcha Cookie", "65000","4.3","top"));
        newProducts.add(new NewProduct(R.drawable.img_bdcake, "Biscuit", "55000","4.7","new"));
        newProducts.add(new NewProduct(R.drawable.img_cake, "Pink Biscuit", "50000","4.1","promo"));
        newProducts.add(new NewProduct(R.drawable.img_mango_cake, "Macaroon", "35000","4.7","top"));
        newProducts.add(new NewProduct(R.drawable.img_cake, "Fruit Cake", "39000","4.8","new"));
        gvSuggestion.setAdapter(new SuggestionAdapter(this, R.layout.viewholder_new_product, newProducts));
    }

    private void addEventPromotion() {
        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvPromotion.setLayoutManager(layoutManager);
        ArrayList<NewProduct> newProducts= new ArrayList<>();
        newProducts.add(new NewProduct(R.drawable.img_bdcake, "Biscuit", "55000","4.7","promo"));
        newProducts.add(new NewProduct(R.drawable.img_cake, "Pink Biscuit", "50000","4.1","promo"));
        newProducts.add(new NewProduct(R.drawable.img_mango_cake, "Macaroon", "35000","4.7","promo"));
        newProducts.add(new NewProduct(R.drawable.img_summer_pudding, "Matcha Cookie", "65000","4.3","promo"));
        newProducts.add(new NewProduct(R.drawable.img_cake, "Fruit Cake", "39000","4.8","promo"));
        rcvPromotion.setAdapter(new NewProductAdapter(this,R.layout.viewholder_new_product, newProducts));
    }

    private void addEventPopular() {
        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvPopular.setLayoutManager(layoutManager);
        ArrayList<NewProduct> newProducts= new ArrayList<>();
        newProducts.add(new NewProduct(R.drawable.img_summer_pudding, "Matcha Cookie", "65000","4.3","top"));
        newProducts.add(new NewProduct(R.drawable.img_bdcake, "Biscuit", "55000","4.7","top"));
        newProducts.add(new NewProduct(R.drawable.img_cake, "Pink Biscuit", "50000","4.1","top"));
        newProducts.add(new NewProduct(R.drawable.img_mango_cake, "Macaroon", "35000","4.7","top"));
        newProducts.add(new NewProduct(R.drawable.img_cake, "Fruit Cake", "39000","4.8","top"));
        rcvPopular.setAdapter(new NewProductAdapter(this,R.layout.viewholder_new_product, newProducts));
    }

    private void addEventCategory() {
        ArrayList<SimpleViewGroup> categories= new ArrayList<>();
        categories.add(new SimpleViewGroup(R.drawable.img_cate1, "Cheese"));
        categories.add(new SimpleViewGroup(R.drawable.img_cate2, "Pudding"));
        categories.add(new SimpleViewGroup(R.drawable.img_cate3, "Birthday cake"));
        categories.add(new SimpleViewGroup(R.drawable.img_cate4, "Tiramisu"));
        categories.add(new SimpleViewGroup(R.drawable.img_cate3, "Biscuit"));
        categories.add(new SimpleViewGroup(R.drawable.img_cate1, "Cookie"));
        categories.add(new SimpleViewGroup(R.drawable.img_cate4, "Donut"));
        categories.add(new SimpleViewGroup(R.drawable.img_cate2, "Macaroon"));
        gvCategory.setAdapter(new SimpleViewGroupAdapter(this, R.layout.viewholder_category, categories));
    }

    private void addEventNewProduct() {
        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rcvNewProduct.setLayoutManager(layoutManager);
        ArrayList<NewProduct> newProducts= new ArrayList<>();
        newProducts.add(new NewProduct(R.drawable.img_mango_cake, "Macaroon", "35000","4.7","new"));
        newProducts.add(new NewProduct(R.drawable.img_cake, "Fruit Cake", "39000","4.8","new"));
        newProducts.add(new NewProduct(R.drawable.img_summer_pudding, "Matcha Cookie", "65000","4.3","new"));
        newProducts.add(new NewProduct(R.drawable.img_bdcake, "Biscuit", "55000","4.7","new"));
        newProducts.add(new NewProduct(R.drawable.img_cake, "Pink Biscuit", "50000","4.1","new"));
        rcvNewProduct.setAdapter(new NewProductAdapter(this,R.layout.viewholder_new_product, newProducts));
    }

    private void addEventSliderBanner() {
        ArrayList<Banner> banners= new ArrayList<>();
        banners.add(new Banner(R.drawable.img_banner1));
        banners.add(new Banner(R.drawable.img_banner2));
        banners.add(new Banner(R.drawable.img_banner3));
        banners.add(new Banner(R.drawable.img_banner4));
        sliderBanner.setSliderAdapter(new SliderBannerAdapter(banners, this));
//        Config Slider Banner
        sliderBanner.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
        sliderBanner.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderBanner.startAutoCycle();
    }

    private void navigateTabs() {
        favoriteTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FavoriteActivity.class));
            }
        });
        dietTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DietActivity.class));
            }
        });
        feedTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FeedActivity.class));
            }
        });
        profileTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            }
        });

        imbNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
            }
        });
        edtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    btnSearch.setVisibility(View.VISIBLE);
                    btnSearch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            edtSearch.clearFocus();
                            startActivity(new Intent(MainActivity.this, SearchActivity.class));
                        }
                    });
                }
                else{
                    edtSearch.clearFocus();
                    btnSearch.setVisibility(View.GONE);
                }
            }
        });
        if(hasWindowFocus()){
            edtSearch.clearFocus();
            btnSearch.setVisibility(View.GONE);
        }
    }

    private void linkViews() {
        edtSearch= findViewById(R.id.edtSearchHome);
        sliderBanner= findViewById(R.id.imageSliderHome);
        rcvNewProduct= findViewById(R.id.rcvNewProducts);
        rcvPopular= findViewById(R.id.rcvPopular);
        rcvPromotion= findViewById(R.id.rcvPromotion);
        gvCategory= findViewById(R.id.gvCategory);
        gvSuggestion= findViewById(R.id.gvSuggestion);
        favoriteTab= findViewById(R.id.favoriteNav);
        feedTab= findViewById(R.id.feedNav);
        dietTab= findViewById(R.id.dietNav);
        profileTab= findViewById(R.id.profileNav);
        imbNotify= findViewById(R.id.imbNotificationHome);
        imbCart= findViewById(R.id.imbCartHome);
        btnSearch= findViewById(R.id.buttonSearchHome);
    }
}