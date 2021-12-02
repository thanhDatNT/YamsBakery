package com.thanhdat.yams.Fragments;

import android.annotation.SuppressLint;
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
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.thanhdat.yams.Activities.ChatActivity;
import com.thanhdat.yams.Activities.LanguageActivity;
import com.thanhdat.yams.Activities.LoginActivity;
import com.thanhdat.yams.Activities.OrderStatusActivity;
import com.thanhdat.yams.Activities.SettingAccount;

import com.thanhdat.yams.Models.Banner;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapter.SliderBannerAdapter;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    //private ImageButton imbToOrderStatus;
    private Toolbar toolbarProfile;
    private SliderView sliderBannerProfile;
    private SwitchCompat switchNotification;
    private NestedScrollView scrollView;
    private CardView imgProfile;
    private LinearLayout lnOrder, lnVoucher, lnMessage, lnLanguage, lnLogout;


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

        switchNotification = view.findViewById(R.id.switchNotification);

        scrollView= view.findViewById(R.id.scrollViewProfile);
        imgProfile= view.findViewById(R.id.imgProfile);

        lnOrder = view.findViewById(R.id.lnOrderProfile);
        lnVoucher = view.findViewById(R.id.lnVoucherProfile);
        lnMessage = view.findViewById(R.id.lnMessageProfile);
        lnLanguage = view.findViewById(R.id.lnLanguageProfile);
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
        
        //notification
        switchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Toast.makeText(getContext(), "Đã " + (switchNotification.isChecked() ? "bật" : "tắt") + " thông báo!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.lnOrderProfile:
                    startActivity(new Intent(getContext(), OrderStatusActivity.class));
                    break;
                case R.id.lnVoucherProfile:
                    //startActivity(new Intent(getContext(), .class));
                    break;
                case R.id.lnMessageProfile:
                    startActivity(new Intent(getContext(), ChatActivity.class));
                    break;
                case R.id.lnLanguageProfile:
                    startActivity(new Intent(getContext(), LanguageActivity.class));
                    break;
                case R.id.lnLogoutProfile:
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
                    break;
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