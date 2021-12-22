package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Fragments.OtpFragment;
import com.thanhdat.yams.Models.User;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class SettingMyProfileActivity extends AppCompatActivity {
    EditText edtName, edtPhone, edtEmail;
    AppCompatButton btnConfirm;
    Toolbar toolbarMyProfile;
    ImageView imvAva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_my_profile);

        linkView();
        addEvent();
    }

    private void  addEvent() {
        ArrayList<User> user = MainActivity.user;
        if(!user.get(0).getPhoto().equals("")){
            Picasso.get().load(user.get(0).getPhoto()).into(imvAva);
        }
        if(!user.get(0).getName().equals(""))
            edtName.setText(user.get(0).getName());
        edtPhone.setText(user.get(0).getPhone());
        if(!user.get(0).getEmail().equals("")){
            edtEmail.setText(user.get(0).getEmail());
        }
        setSupportActionBar(toolbarMyProfile);
        getSupportActionBar().setTitle(null);
        toolbarMyProfile.setNavigationOnClickListener(view -> onBackPressed());
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= edtName.getText().toString();
                String phone= edtPhone.getText().toString();
                String information = name + " | " + phone;
                if(!(name.equals("") && phone.equals(""))){
                    Intent intent= new Intent(SettingMyProfileActivity.this, OrderActivity.class);
                    intent.putExtra(Constant.INFORMATION_INTENT, information);
                    setResult(Constant.RESULT_INFORMATION, intent);
                    SettingMyProfileActivity.super.onBackPressed();
                }
            }
        });
    }

    private void linkView() {
        toolbarMyProfile = findViewById(R.id.toolbarMyProfile);
        btnConfirm= findViewById(R.id.buttonConFirmEditAcc);
        edtName = findViewById(R.id.edtAccountName);
        edtPhone= findViewById(R.id.edtAccountPhone);
        edtEmail= findViewById(R.id.editEmailAccount);
        imvAva = findViewById(R.id.imvAvatarMyAcc);
    }
}