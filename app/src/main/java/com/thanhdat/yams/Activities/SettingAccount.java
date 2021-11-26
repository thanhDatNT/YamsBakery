package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.thanhdat.yams.R;

public class SettingAccount extends AppCompatActivity {
    LinearLayout lnMyProfile,lnChangeEmail,lnChangePwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_account);
        linkView();
        addEventTabSetting();
    }




    private void linkView() {
        lnMyProfile=findViewById(R.id.lnMyProfile);
        lnChangeEmail=findViewById(R.id.lnChangeEmail);
        lnChangePwd=findViewById(R.id.lnChangePwd);
    }

    private void addEventTabSetting() {
        lnMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingAccount.this,SettingMyProfileActivity.class);
                startActivity(intent);
            }
        });
        lnChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingAccount.this,SettingChangeEmailActivity.class);
                startActivity(intent);
            }
        });
        lnChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingAccount.this,SettingChangePWActivity.class);
                startActivity(intent);
            }
        });

    }
}