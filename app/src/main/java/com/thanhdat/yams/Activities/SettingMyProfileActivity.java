package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.thanhdat.yams.Models.SettingMyProfile;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapter.SettingMyProfileAdapter;

import java.util.ArrayList;

public class SettingMyProfileActivity extends AppCompatActivity {
    ListView lvMyProfile;
    ArrayList<SettingMyProfile> profileArrayList;
    SettingMyProfileAdapter adapter;
    Toolbar toolbarMyProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_my_profile);
        linkView();
        addEvent();
        EventTabBack();

    }




    private void linkView() {
        lvMyProfile=findViewById(R.id.lvMyProfile);
        toolbarMyProfile=findViewById(R.id.toolbarMyProfile);
//        imvBack = findViewById(R.id.imvBack);
    }
    private void addEvent() {
        profileArrayList=new ArrayList<SettingMyProfile>();
        profileArrayList.add(new SettingMyProfile(1,"Tên","Như Quỳnh"));
        profileArrayList.add(new SettingMyProfile(2,"Số điện thoại","0397287661"));
        profileArrayList.add(new SettingMyProfile(3,"Ngày sinh","30/12/2001"));
        adapter = new SettingMyProfileAdapter(SettingMyProfileActivity.this,R.layout.items_setting_myprofile,profileArrayList);
        lvMyProfile.setAdapter(adapter);
    }
    private void  EventTabBack() {
        setSupportActionBar(toolbarMyProfile);
        getSupportActionBar().setTitle(null);
        toolbarMyProfile.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


}