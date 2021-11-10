package com.thanhdat.yams.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.thanhdat.yams.Activities.CartActivity;
import com.thanhdat.yams.Activities.MainActivity;
import com.thanhdat.yams.Activities.NotificationActivity;
import com.thanhdat.yams.Activities.ProductDetailsActivity;
import com.thanhdat.yams.Activities.SearchActivity;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Banner;
import com.thanhdat.yams.Models.NewProduct;
import com.thanhdat.yams.Models.SimpleViewGroup;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.NewProductAdapter;
import com.thanhdat.yams.adapter.SimpleViewGroupAdapter;
import com.thanhdat.yams.adapter.SliderBannerAdapter;
import com.thanhdat.yams.adapter.SuggestionAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private SliderView sliderBanner;
    private RecyclerView rcvNewProduct, rcvPopular, rcvPromotion;
    private GridView gvCategory, gvSuggestion;
    private ImageButton imbCart, imbNotify;
    private EditText edtSearch;

    private OnClickInterface onClickInterface;
    private AppCompatButton btnSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        edtSearch= view.findViewById(R.id.edtSearchHome);
        sliderBanner= view.findViewById(R.id.imageSliderHome);
        rcvNewProduct= view.findViewById(R.id.rcvNewProducts);
        rcvPopular= view.findViewById(R.id.rcvPopular);
        rcvPromotion= view.findViewById(R.id.rcvPromotion);
        gvCategory= view.findViewById(R.id.gvCategory);
        gvSuggestion= view.findViewById(R.id.gvSuggestion);
        imbNotify= view.findViewById(R.id.imbNotificationHome);
        imbCart= view.findViewById(R.id.imbCartHome);
        btnSearch= view.findViewById(R.id.buttonSearchHome);

        edtSearch.clearFocus();
        configAndNavigate();
        addEventSliderBanner();
        addEventNewProduct();
        addEventCategory();
        addEventPopular();
        addEventPromotion();
        addEventSuggestion();
        return view;
    }


    private void addEventSuggestion() {
        ArrayList<NewProduct> newProducts= new ArrayList<>();
        newProducts.add(new NewProduct(R.drawable.img_summer_pudding, "Matcha Cookie", "65000","4.3","top"));
        newProducts.add(new NewProduct(R.drawable.img_bdcake, "Biscuit", "55000","4.7","new"));
        newProducts.add(new NewProduct(R.drawable.img_cake, "Pink Biscuit", "50000","4.1","promo"));
        newProducts.add(new NewProduct(R.drawable.img_mango_cake, "Macaroon", "35000","4.7","top"));
        newProducts.add(new NewProduct(R.drawable.img_cake, "Fruit Cake", "39000","4.8","new"));
        gvSuggestion.setAdapter(new SuggestionAdapter(getContext(), R.layout.viewholder_new_product, newProducts));
    }

    private void addEventPromotion() {
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcvPromotion.setLayoutManager(layoutManager);
        ArrayList<NewProduct> newProducts= new ArrayList<>();
        newProducts.add(new NewProduct(R.drawable.img_bdcake, "Biscuit", "55000","4.7","promo"));
        newProducts.add(new NewProduct(R.drawable.img_cake, "Pink Biscuit", "50000","4.1","promo"));
        newProducts.add(new NewProduct(R.drawable.img_mango_cake, "Macaroon", "35000","4.7","promo"));
        newProducts.add(new NewProduct(R.drawable.img_summer_pudding, "Matcha Cookie", "65000","4.3","promo"));
        newProducts.add(new NewProduct(R.drawable.img_cake, "Fruit Cake", "39000","4.8","promo"));
        rcvPromotion.setAdapter(new NewProductAdapter(getContext(),R.layout.viewholder_new_product, newProducts, onClickInterface));
    }

    private void addEventPopular() {
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcvPopular.setLayoutManager(layoutManager);
        ArrayList<NewProduct> newProducts= new ArrayList<>();
        newProducts.add(new NewProduct(R.drawable.img_summer_pudding, "Matcha Cookie", "65000","4.3","top"));
        newProducts.add(new NewProduct(R.drawable.img_bdcake, "Biscuit", "55000","4.7","top"));
        newProducts.add(new NewProduct(R.drawable.img_cake, "Pink Biscuit", "50000","4.1","top"));
        newProducts.add(new NewProduct(R.drawable.img_mango_cake, "Macaroon", "35000","4.7","top"));
        newProducts.add(new NewProduct(R.drawable.img_cake, "Fruit Cake", "39000","4.8","top"));
        rcvPopular.setAdapter(new NewProductAdapter(getContext(),R.layout.viewholder_new_product, newProducts, onClickInterface));
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
        gvCategory.setAdapter(new SimpleViewGroupAdapter(getContext(), R.layout.viewholder_category, categories));
    }

    private void addEventNewProduct() {
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcvNewProduct.setLayoutManager(layoutManager);
        ArrayList<NewProduct> newProducts= new ArrayList<>();
        newProducts.add(new NewProduct(R.drawable.img_mango_cake, "Macaroon", "35000","4.7","new"));
        newProducts.add(new NewProduct(R.drawable.img_cake, "Fruit Cake", "39000","4.8","new"));
        newProducts.add(new NewProduct(R.drawable.img_summer_pudding, "Matcha Cookie", "65000","4.3","new"));
        newProducts.add(new NewProduct(R.drawable.img_bdcake, "Biscuit", "55000","4.7","new"));
        newProducts.add(new NewProduct(R.drawable.img_cake, "Pink Biscuit", "50000","4.1","new"));
        rcvNewProduct.setAdapter(new NewProductAdapter(getContext(),R.layout.viewholder_new_product, newProducts, onClickInterface));

    }

    private void addEventSliderBanner() {
        ArrayList<Banner> banners= new ArrayList<>();
        banners.add(new Banner(R.drawable.img_banner1));
        banners.add(new Banner(R.drawable.img_banner2));
        banners.add(new Banner(R.drawable.img_banner3));
        banners.add(new Banner(R.drawable.img_banner4));
        sliderBanner.setSliderAdapter(new SliderBannerAdapter(banners, getContext()));
//        Config Slider Banner
        sliderBanner.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
        sliderBanner.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderBanner.startAutoCycle();
    }


    private void configAndNavigate() {
        onClickInterface= abc -> startActivity(new Intent(getContext(), ProductDetailsActivity.class));
        imbNotify.setOnClickListener(v -> startActivity(new Intent(getContext(), NotificationActivity.class)));
        edtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    btnSearch.setVisibility(View.VISIBLE);
                    btnSearch.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            edtSearch.clearFocus();
                            startActivity(new Intent(getContext(), SearchActivity.class));
                        }
                    });
                }
                else{
                    edtSearch.clearFocus();
                    btnSearch.setVisibility(View.GONE);
                }
            }
        });
        if(getActivity().hasWindowFocus()){
            edtSearch.clearFocus();
            btnSearch.setVisibility(View.GONE);
        }
        imbCart.setOnClickListener(v -> startActivity(new Intent(getContext(), CartActivity.class)));

    }

}