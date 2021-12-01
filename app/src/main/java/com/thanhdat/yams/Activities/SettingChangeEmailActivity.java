package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.thanhdat.yams.R;

public class SettingChangeEmailActivity extends AppCompatActivity {
    Toolbar toolbarChangEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_change_email);
        linkView();
        addEventBackTab();
    }



    private void linkView() {
        toolbarChangEmail= findViewById(R.id.toolbarChangEmail);
    }
    private void addEventBackTab()
    {
        setSupportActionBar(toolbarChangEmail);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(null);
            toolbarChangEmail.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                onBackPressed();
                }
            });
        }
    }
}