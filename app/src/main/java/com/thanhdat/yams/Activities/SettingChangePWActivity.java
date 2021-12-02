package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.thanhdat.yams.R;

public class SettingChangePWActivity extends AppCompatActivity {
    Toolbar toolChangePwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_change_pwactivity);
        linkView();
        addEventTabBack();

    }


    private void linkView() {
        toolChangePwd = findViewById(R.id.toolbarChangePwd);
    }
    private void addEventTabBack() {
        setSupportActionBar(toolChangePwd);
        getSupportActionBar().setTitle(null);
        toolChangePwd.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}