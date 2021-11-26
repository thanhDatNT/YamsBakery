package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.thanhdat.yams.R;

public class SettingChangePWActivity extends AppCompatActivity {
    ImageView imvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_change_pwactivity);
        linkView();
        addEventTabBack();
    }


    private void linkView() {
        imvBack=findViewById(R.id.imvBack);
    }
    private void addEventTabBack() {
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingChangePWActivity.this,SettingAccount.class);
                startActivity(intent);
            }
        });
    }

}