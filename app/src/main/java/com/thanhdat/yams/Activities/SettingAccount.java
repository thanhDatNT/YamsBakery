package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.thanhdat.yams.R;

public class SettingAccount extends AppCompatActivity {
    LinearLayout lnMyProfile,lnChangeEmail,lnChangePwd;
    ImageButton imbBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_account);
        linkView();
        addEventTabSetting();
        addEventBack();
    }



    private void linkView() {
        lnMyProfile=findViewById(R.id.lnMyProfile);
        lnChangeEmail=findViewById(R.id.lnChangeEmail);
        lnChangePwd=findViewById(R.id.lnChangePwd);

        imbBack = findViewById(R.id.imbBack);
    }

    private void addEventTabSetting() {
        lnMyProfile.setOnClickListener(MyClickEdit);
        lnChangeEmail.setOnClickListener(MyClickEdit);
        lnChangePwd.setOnClickListener(MyClickEdit);

//        lnMyProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SettingAccount.this,SettingMyProfileActivity.class);
//                startActivity(intent);
//            }
//        });
//        lnChangeEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SettingAccount.this,SettingChangeEmailActivity.class);
//                startActivity(intent);
//            }
//        });
//        lnChangePwd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(SettingAccount.this,SettingChangePWActivity.class);
//                startActivity(intent);
//            }
//        });

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
        }
    };
    private void addEventBack() {
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

}