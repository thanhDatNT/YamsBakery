package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.R;

public class SettingMyProfileActivity extends AppCompatActivity {
    EditText edtName, edtPhone, edtBirthday;
    AppCompatButton btnConfirm;
    Toolbar toolbarMyProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_my_profile);

        linkView();
        addEvent();
    }

    private void  addEvent() {
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
        edtBirthday= findViewById(R.id.editBirthday);
    }
}