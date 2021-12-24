package com.thanhdat.yams.Fragments;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
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
import com.thanhdat.yams.Activities.SeeAllActivity;
import com.thanhdat.yams.Adapters.CategoryAdapter;
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Category;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.Models.User;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapters.ProductAdapter;
import com.thanhdat.yams.Adapters.SliderBannerAdapter;
import com.thanhdat.yams.Adapters.SuggestionAdapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment{
    private SliderView sliderBanner;
    private RecyclerView rcvNewProduct, rcvPopular, rcvPromotion;
    private GridView gvCategory, gvSuggestion;
    private androidx.appcompat.widget.Toolbar toolbar;
    private AppCompatButton btnSearch;
    private TextView txtGoPromo, txtGoPopular, txtGoNew, tvUser;
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
        btnSearch= view.findViewById(R.id.btnSearch);
        scrollView= view.findViewById(R.id.scrollViewHome);
        txtGoNew= view.findViewById(R.id.tvViewNewProducts);
        txtGoPopular= view.findViewById(R.id.tvViewPopularProducts);
        txtGoPromo= view.findViewById(R.id.tvViewPromoProducts);
        tvUser = view.findViewById(R.id.tvGreetHome);

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
        gvSuggestion.setAdapter(new SuggestionAdapter(getContext(), R.layout.item_product,suggestProducts, onClickInterface));
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
        rcvPromotion.setAdapter(new ProductAdapter(getContext(),R.layout.item_product, promoProducts, onClickInterface));
        txtGoPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SeeAllActivity.class);
                intent.putExtra("idAll","promo");
                startActivity(intent);
            }
        });

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
        rcvPopular.setAdapter(new ProductAdapter(getContext(),R.layout.item_product, popularProducts, onClickInterface));
        txtGoPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SeeAllActivity.class);
                intent.putExtra("idAll","popular");
                startActivity(intent);
            }
        });
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
        rcvNewProduct.setAdapter(new ProductAdapter(getContext(),R.layout.item_product, newProducts, onClickInterface));

        txtGoNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SeeAllActivity.class);
                intent.putExtra("idAll","new");
                startActivity(intent);
            }
        });
    }

    private void addEventCategory() {
        ArrayList<Category> categories= MainActivity.categoryList;
        gvCategory.setAdapter(new CategoryAdapter(getContext(), R.layout.item_category, categories));
        gvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("id", categories.get(position).getId());
                intent.putExtra("category",categories.get(position).getName());
                startActivity(intent);
            }
        });
    }

    private void addEventSliderBanner() {
        int[] banners= {R.drawable.img_banner_1,
                R.drawable.img_banner_2,
                R.drawable.img_banner_3,
                R.drawable.img_banner_4};
        sliderBanner.setSliderAdapter(new SliderBannerAdapter(banners, getContext()));
//        Config Slider image
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
        ArrayList<User> users = MainActivity.user;
        tvUser.setText("Hello, " + users.get(0).getName());
        onClickInterface= number -> {
//            Intent includes Product Id for Product detail activity
            Intent intent= new Intent(getContext(), ProductDetailsActivity.class);
            intent.putExtra(Constant.ID_PRODUCT, number);
            ActivityOptions options = ActivityOptions.makeCustomAnimation(getContext(), R.anim.translate_slide_enter, R.anim.translate_slide_exit);
            startActivity(intent, options.toBundle());
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

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}