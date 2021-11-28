package com.thanhdat.yams.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.thanhdat.yams.Activities.ChatActivity;
import com.thanhdat.yams.Activities.FunctionProfileActivity;
import com.thanhdat.yams.Activities.IntroActivity;
import com.thanhdat.yams.Activities.LoginActivity;
import com.thanhdat.yams.Activities.MainActivity;
import com.thanhdat.yams.Activities.NotificationActivity;
import com.thanhdat.yams.Activities.OrderStatusActivity;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Activities.SettingAccount;

import com.thanhdat.yams.Activities.IntroActivity;
import com.thanhdat.yams.Activities.LoginActivity;
import com.thanhdat.yams.Activities.OrderStatusActivity;

import com.thanhdat.yams.Models.Banner;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.SliderBannerAdapter;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    //private ImageButton imbToOrderStatus;
    private Toolbar toolbarProfile;
    private SliderView sliderBannerProfile;

    ImageButton imbEditProfile;

    private NestedScrollView scrollView;
    private  CardView imgProfile;
    private LinearLayout lnOrder, lnVoucher, lnMessage, lnLanguage, lnNoti, lnLogout;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container, false);

        //link views
        toolbarProfile = view.findViewById(R.id.toolbarProfile);
        sliderBannerProfile = view.findViewById(R.id.imageSliderProfile);


        scrollView= view.findViewById(R.id.scrollViewProfile);
        imgProfile= view.findViewById(R.id.imgProfile);
        lnOrder = view.findViewById(R.id.lnOrderProfile);
        lnVoucher = view.findViewById(R.id.lnVoucherProfile);
        lnMessage = view.findViewById(R.id.lnMessageProfile);
        lnLanguage = view.findViewById(R.id.lnLanguageProfile);
        lnNoti = view.findViewById(R.id.lnNotiProfile);
        lnLogout = view.findViewById(R.id.lnLogoutProfile);

        addEventSliderBanner();
        addEventCollapsing();
        addEventFunction();


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
        sliderBannerProfile.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderBannerProfile.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderBannerProfile.startAutoCycle();
    }

    private void addEventCollapsing() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if(activity != null){
            activity.setSupportActionBar(toolbarProfile);
            if(activity.getSupportActionBar() != null){
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);

            }
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.profile_heading, menu);
    }

    private void addEventFunction() {
        lnOrder.setOnClickListener(myClick);
        lnVoucher.setOnClickListener(myClick);
        lnMessage.setOnClickListener(myClick);
        lnLanguage.setOnClickListener(myClick);
        lnLogout.setOnClickListener(myClick);
    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.lnOrderProfile){
                startActivity(new Intent(getContext(), OrderStatusActivity.class));
            }
            //start function activity
            if(view.getId() == R.id.lnMessageProfile){
                startActivity(new Intent(getContext(), ChatActivity.class));
            }
            if(view.getId() == R.id.lnVoucherProfile || view.getId() == R.id.lnLanguageProfile){
                Intent intent = new Intent(getContext(), FunctionProfileActivity.class);
                intent.setFlags(view.getId());
                startActivity(intent);
            }
            if(view.getId() == R.id.lnLogoutProfile){
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_logout);


                Button btnConfirm = dialog.findViewById(R.id.btnConfirmLogout);
                Button btnCancel = dialog.findViewById(R.id.btnCancelLogout);

                //Confirm logout
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getContext(), LoginActivity.class));
                    }
                });

                //Cancel logout
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        }
    };
    private void addEventEditProfile() {
        toolbarProfile.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.mnuEditProfile)
                {
                    startActivity(new Intent(getContext(), SettingAccount.class));
                }
                return false;
            }
        });
    }


}