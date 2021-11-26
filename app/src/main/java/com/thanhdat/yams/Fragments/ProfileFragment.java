package com.thanhdat.yams.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.thanhdat.yams.Activities.SettingAccount;
import com.thanhdat.yams.Models.Banner;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.SliderBannerAdapter;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    //private ImageButton imbToOrderStatus;
    private Toolbar toolbarProfile;
    private SliderView sliderBannerProfile;
    ImageButton imbEditProfile;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container, false);

        //link views
        //imbToOrderStatus = view.findViewById(R.id.imbToOrderStatus);
        toolbarProfile = view.findViewById(R.id.toolbarProfile);
        sliderBannerProfile = view.findViewById(R.id.imageSliderProfile);
        imbEditProfile = view.findViewById(R.id.imbEditProfile);

        addEventSliderBanner();
        addEventCollapsing();
        addEventEditProfile();


        return view;
    }



    private void addEventSliderBanner() {
        ArrayList<Banner> banners= new ArrayList<>();
        banners.add(new Banner(R.drawable.img_banner_profile_1));
        banners.add(new Banner(R.drawable.img_banner_profile_2));
        banners.add(new Banner(R.drawable.img_banner_profile_3));
        sliderBannerProfile.setSliderAdapter(new SliderBannerAdapter(banners, getContext()));
//      Config Slider Banner profile
        sliderBannerProfile.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
        sliderBannerProfile.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderBannerProfile.startAutoCycle();
    }

    private void addEventCollapsing() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if(activity != null){
            activity.setSupportActionBar(toolbarProfile);
            if(activity.getSupportActionBar() != null){
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    private void addEventEditProfile() {
        imbEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SettingAccount.class);
                startActivity(intent);
            }
        });

    }



}