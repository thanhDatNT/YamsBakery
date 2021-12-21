package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.thanhdat.yams.R;

public class SettingAccount extends AppCompatActivity {
    LinearLayout lnMyProfile,lnChangeEmail,lnChangePwd,lnChangeAddress;
    Toolbar toolbarSettingAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_account);
        linkView();
        addEventTabSetting();
        addEventTabBack();
    }



    private void linkView() {
        lnMyProfile=findViewById(R.id.lnMyProfile);
        lnChangeEmail=findViewById(R.id.lnChangeEmail);
        lnChangePwd=findViewById(R.id.lnChangePwd);
        lnChangeAddress=findViewById(R.id.lnChangeAddress);

        toolbarSettingAccount = findViewById(R.id.toolbarSettingAccount);

    }

    private void addEventTabSetting() {
        lnMyProfile.setOnClickListener(MyClickEdit);
        lnChangeEmail.setOnClickListener(MyClickEdit);
        lnChangePwd.setOnClickListener(MyClickEdit);
        lnChangeAddress.setOnClickListener(MyClickEdit);


    }
    View.OnClickListener MyClickEdit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.lnMyProfile)
            {
                Intent intent = new Intent(SettingAccount.this,SettingMyProfileActivity.class);
                startActivity(intent);
            }
            if(view.getId()==R.id.lnChangeEmail){
                Intent intent = new Intent(SettingAccount.this,SettingChangeEmailActivity.class);
                 startActivity(intent);
            }
            if(view.getId()==R.id.lnChangePwd){
                Intent intent = new Intent(SettingAccount.this,SettingChangePWActivity.class);
                startActivity(intent);
            }
            if(view.getId()==R.id.lnChangeAddress){
                Intent intent = new Intent(SettingAccount.this,MapActivity.class);
                startActivity(intent);
            }
        }
    };
    private void addEventTabBack() {
        setSupportActionBar(toolbarSettingAccount);
        getSupportActionBar().setTitle(null);
        toolbarSettingAccount.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }




}