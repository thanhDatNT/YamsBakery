package com.thanhdat.yams.Fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.thanhdat.yams.Activities.CartActivity;

import com.thanhdat.yams.Activities.CategoryActivity;

import com.thanhdat.yams.Activities.MainActivity;
import com.thanhdat.yams.Activities.MapActivity;
import com.thanhdat.yams.Activities.NotificationActivity;
import com.thanhdat.yams.Activities.PaymentActivity;
import com.thanhdat.yams.Activities.ProductDetailsActivity;
import com.thanhdat.yams.Activities.SearchActivity;
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Banner;
import com.thanhdat.yams.Models.NewProduct;
import com.thanhdat.yams.Models.SimpleViewGroup;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapter.NewProductAdapter;
import com.thanhdat.yams.Adapter.SimpleViewGroupAdapter;
import com.thanhdat.yams.Adapter.SliderBannerAdapter;
import com.thanhdat.yams.Adapter.SuggestionAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment{
    private SliderView sliderBanner;
    private RecyclerView rcvNewProduct, rcvPopular, rcvPromotion;
    private GridView gvCategory, gvSuggestion;
    private androidx.appcompat.widget.Toolbar toolbar;
    private SearchView searchView;
    private TextView txtGoPromo, txtGoPopular, txtGoNew;
    private OnClickInterface onClickInterface;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        sliderBanner= view.findViewById(R.id.imageSliderHome);
        rcvNewProduct= view.findViewById(R.id.rcvNewProducts);
        rcvPopular= view.findViewById(R.id.rcvPopular);
        rcvPromotion= view.findViewById(R.id.rcvPromotion);
        gvCategory= view.findViewById(R.id.gvCategory);
        gvSuggestion= view.findViewById(R.id.gvSuggestion);
        toolbar= view.findViewById(R.id.toolbarHome);
        searchView= view.findViewById(R.id.svSearchHome);
        txtGoNew= view.findViewById(R.id.tvViewNewProducts);
        txtGoPopular= view.findViewById(R.id.tvViewPopularProducts);
        txtGoPromo= view.findViewById(R.id.tvViewPromoProducts);

        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        setHasOptionsMenu(true);
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
        gvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getContext(), CategoryActivity.class));
            }
        });
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
        banners.add(new Banner(R.drawable.img_banner_1));
        banners.add(new Banner(R.drawable.img_banner_2));
        banners.add(new Banner(R.drawable.img_banner_3));
        banners.add(new Banner(R.drawable.img_banner_4));
        sliderBanner.setSliderAdapter(new SliderBannerAdapter(banners, getContext()));
//        Config Slider Banner
        sliderBanner.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
        sliderBanner.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderBanner.startAutoCycle();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.homepage_heading, menu);
    }

    private void configAndNavigate() {
        onClickInterface= abc -> startActivity(new Intent(getContext(), ProductDetailsActivity.class));
        toolbar.setTitle(null);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.mnuNotificationHome){
                    startActivity(new Intent(getContext(), NotificationActivity.class));
                    getActivity().overridePendingTransition(R.anim.translate_slide_enter, R.anim.translate_slide_exit);
                }
                if(item.getItemId() == R.id.mnuCartHome){
                    startActivity(new Intent(getContext(), CartActivity.class));
                }
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra(Constant.STRING_INTENT, query);
                startActivity(intent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}