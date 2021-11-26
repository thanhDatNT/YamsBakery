package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.thanhdat.yams.R;

public class SettingChangeEmailActivity extends AppCompatActivity {
    ImageView imvBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_change_email);
        linkView();
        addEventBackTab();
    }



    private void linkView() {
        imvBack=findViewById(R.id.imvBack);
    }
    private void addEventBackTab() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingChangeEmailActivity.this,SettingAccount.class);
                startActivity(intent);
            }
        });
    }
}