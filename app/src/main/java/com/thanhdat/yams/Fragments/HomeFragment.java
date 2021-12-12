package com.thanhdat.yams.Fragments;

import android.content.Intent;
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
import com.thanhdat.yams.Activities.NotificationActivity;
import com.thanhdat.yams.Activities.ProductDetailsActivity;
import com.thanhdat.yams.Activities.SearchActivity;
import com.thanhdat.yams.Adapter.CategoryAdapter;
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Banner;
import com.thanhdat.yams.Models.Category;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapter.ProductAdapter;
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
    private NestedScrollView scrollView;
    ArrayList<Product> productList;


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
        scrollView= view.findViewById(R.id.scrollViewHome);
        txtGoNew= view.findViewById(R.id.tvViewNewProducts);
        txtGoPopular= view.findViewById(R.id.tvViewPopularProducts);
        txtGoPromo= view.findViewById(R.id.tvViewPromoProducts);

        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        setHasOptionsMenu(true);

        productList= MainActivity.productList;
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
        ArrayList<Product> suggestProducts = new ArrayList<>();
        for (int i= 10; i<=15; i++){
            suggestProducts.add(productList.get(i));
        }
        gvSuggestion.setAdapter(new SuggestionAdapter(getContext(), R.layout.viewholder_product,suggestProducts));
    }

    private void addEventPromotion() {
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);;
        rcvPromotion.setLayoutManager(layoutManager);
        ArrayList<Product> promoProducts = new ArrayList<>();
        for (Product p : productList){
            if(p.getTag().equals("Promo")){
                promoProducts.add(p);
            }
        }
        if (promoProducts.size() > 10) {
            promoProducts.subList(10, promoProducts.size()).clear();
        }
        rcvPromotion.setAdapter(new ProductAdapter(getContext(),R.layout.viewholder_product, promoProducts, onClickInterface));
    }

    private void addEventPopular() {
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);;
        rcvPopular.setLayoutManager(layoutManager);
        ArrayList<Product> popularProducts = new ArrayList<>();
        for (Product p : productList){
            if(p.getTag().equals("Popular")){
                popularProducts.add(p);
            }
        }
        if (popularProducts.size() > 10) {
            popularProducts.subList(10, popularProducts.size()).clear();
        }
        rcvPopular.setAdapter(new ProductAdapter(getContext(),R.layout.viewholder_product, popularProducts, onClickInterface));
    }

    private void addEventNewProduct() {
        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);;
        rcvNewProduct.setLayoutManager(layoutManager);
        ArrayList<Product> newProducts = new ArrayList<>();
        for (Product p : productList){
            if(p.getTag().equals("New")){
                newProducts.add(p);
            }
        }
        if (newProducts.size() > 10) {
            newProducts.subList(10, newProducts.size()).clear();
        }
        rcvNewProduct.setAdapter(new ProductAdapter(getContext(),R.layout.viewholder_product, newProducts, onClickInterface));

    }

    private void addEventCategory() {
        ArrayList<Category> categories= MainActivity.categoryList;
        gvCategory.setAdapter(new CategoryAdapter(getContext(), R.layout.viewholder_category, categories));
        gvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getContext(), CategoryActivity.class));
            }
        });
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
        onClickInterface= abc -> {
//            Intent includes Product Id for Product detail activity
            Intent intent= new Intent(getContext(), ProductDetailsActivity.class);
            intent.putExtra(Constant.ID_PRODUCT, abc);
            startActivity(intent);
        };
        toolbar.setTitle(null);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.mnuSearchHome){
                    startActivity(new Intent(getContext(), SearchActivity.class));
                    getActivity().overridePendingTransition(R.animator.translate_slide_enter, R.animator.translate_slide_exit);
                }
                if(item.getItemId() == R.id.mnuNotificationHome){
                    startActivity(new Intent(getContext(), NotificationActivity.class));
                    getActivity().overridePendingTransition(R.animator.translate_slide_enter, R.animator.translate_slide_exit);
                }
                if(item.getItemId() == R.id.mnuCartHome){
                    startActivity(new Intent(getContext(), CartActivity.class));
                    getActivity().overridePendingTransition(R.animator.translate_slide_enter, R.animator.translate_slide_exit);
                }
                return false;
            }
        });
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY > 10){
                    toolbar.getMenu().getItem(0).setVisible(true);
                }
                else{
                    toolbar.getMenu().getItem(0).setVisible(false);
                }
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