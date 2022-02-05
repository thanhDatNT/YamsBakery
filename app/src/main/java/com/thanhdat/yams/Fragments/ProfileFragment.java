package com.thanhdat.yams.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;
import com.thanhdat.yams.Activities.ChatActivity;
import com.thanhdat.yams.Activities.LanguageActivity;
import com.thanhdat.yams.Activities.LoginActivity;
import com.thanhdat.yams.Activities.MainActivity;
import com.thanhdat.yams.Activities.OrderStatusActivity;
import com.thanhdat.yams.Activities.SettingAccount;

import com.thanhdat.yams.Activities.VoucherActivity;
import com.thanhdat.yams.Databases.UserDatabase;
import com.thanhdat.yams.Models.User;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapters.SliderBannerAdapter;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {

    //private ImageButton imbToOrderStatus;
    private Toolbar toolbarProfile;
    private SliderView sliderBannerProfile;
    private SwitchCompat switchNotification;
    private NestedScrollView scrollView;
    private CardView imgProfile;
    private LinearLayout lnOrder, lnVoucher, lnMessage, lnLanguage, lnLogout, lnLogin;
    private TextView txtNameProfile;
    private ImageView imvAvaProfile;
    ArrayList<User> users;
    FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        users = MainActivity.user;
        mAuth = FirebaseAuth.getInstance();
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
        lnLogin = view.findViewById(R.id.lnLogInProfile);

        txtNameProfile = view.findViewById(R.id.txtNameProfile);
        imvAvaProfile = view.findViewById(R.id.imvAvaProfile);

        configLayout();
        addEventSliderBanner();
        addEventCollapsing();
        addEventFunction();
        addEventEditProfile();

        return view;
    }

    private void configLayout() {
        if(mAuth.getCurrentUser() == null){
            lnLogin.setVisibility(View.VISIBLE);
            lnLogout.setVisibility(View.GONE);
        }
        else{
            lnLogin.setVisibility(View.GONE);
        }
    }

    private void addEventSliderBanner() {
        int[] banners= {R.drawable.img_banner_4,
                R.drawable.img_banner_2,
                R.drawable.img_banner_1,
                R.drawable.img_banner_3};
        sliderBannerProfile.setSliderAdapter(new SliderBannerAdapter(banners, getContext()));
//        Config Slider
        sliderBannerProfile.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderBannerProfile.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
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
        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(scrollY > 10){
                    imgProfile.setVisibility(View.GONE);
                }
                else {
                    imgProfile.setVisibility(View.VISIBLE);
                }
            }
        });
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
                    if(mAuth.getCurrentUser() == null){
                        requestLogin();
                        return;
                    }
                    else{
                        startActivity(new Intent(getContext(), OrderStatusActivity.class));
                    }
                    break;
                case R.id.lnVoucherProfile:
                    startActivity(new Intent(getContext(), VoucherActivity.class));
                    break;
                case R.id.lnMessageProfile:
                    if(mAuth.getCurrentUser() == null){
                        requestLogin();
                        return;
                    }
                    else{
                        startActivity(new Intent(getContext(), ChatActivity.class));
                        getActivity().overridePendingTransition(R.anim.translate_slide_enter, R.anim.translate_slide_exit);
                    }
                    break;
                case R.id.lnLanguageProfile:
                    startActivity(new Intent(getContext(), LanguageActivity.class));
                    break;
                case R.id.lnLogInProfile:
                    Log.d("Login", "onClick: Login button was clicked");
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    intent.putExtra("login", 0);
                    startActivity(intent);
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
                            FirebaseAuth.getInstance().signOut();
                            UserDatabase userDatabase = new UserDatabase(getActivity());
                            userDatabase.execSQL("DELETE FROM " + userDatabase.TABLE_NAME + " WHERE " + userDatabase.COL_PHONE + "= '" + users.get(0).getPhone() + "'");
                            startActivity(new Intent(getContext(), LoginActivity.class));
                            dialog.dismiss();
                        }
                    });

                    //Cancel logout
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    break;
            }
        }
    };
    private void addEventEditProfile() {
        if(!users.get(0).getName().equals(""))
            txtNameProfile.setText(users.get(0).getName());
        if (!users.get(0).getPhoto().equals("")){
            Picasso.get().load(users.get(0).getPhoto()).into(imvAvaProfile);
        }
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

    private void requestLogin() {
            Dialog dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.dialog_login_request);

            Button btnConfirm = dialog.findViewById(R.id.btnConfirmLogIn);
            Button btnCancel = dialog.findViewById(R.id.btnCancelLogIn);

            //Confirm logout
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    intent.putExtra("login", 0);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });

            //Cancel logout
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

    }

}