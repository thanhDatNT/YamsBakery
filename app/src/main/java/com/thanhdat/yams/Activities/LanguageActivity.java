package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.thanhdat.yams.R;

public class LanguageActivity extends AppCompatActivity{
    RadioGroup radGroupLanguage;
    RadioButton radVietNam, radEnglish, radJapan;
    Toolbar toolbarLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        radGroupLanguage = findViewById(R.id.radGroupLanguage);

        radVietNam = findViewById(R.id.radVietNam);
        radEnglish = findViewById(R.id.radEnglish);
        radJapan = findViewById(R.id.radJapan);

        toolbarLanguage = findViewById(R.id.toolbarLanguage);

        addEvent();
    }
    private void addEvent() {
        radVietNam.setTextColor(getResources().getColor(R.color.primary));

        radGroupLanguage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.radVietNam){
                    radVietNam.setTextColor(getResources().getColor(R.color.primary));
                    radEnglish.setTextColor(getResources().getColor(R.color.textColor));
                    radJapan.setTextColor(getResources().getColor(R.color.textColor));
                }else if(i == R.id.radEnglish){
                    radEnglish.setTextColor(getResources().getColor(R.color.primary));
                    radVietNam.setTextColor(getResources().getColor(R.color.textColor));
                    radJapan.setTextColor(getResources().getColor(R.color.textColor));
                }else if(i == R.id.radJapan){
                    radJapan.setTextColor(getResources().getColor(R.color.primary));
                    radVietNam.setTextColor(getResources().getColor(R.color.textColor));
                    radEnglish.setTextColor(getResources().getColor(R.color.textColor));
                }
            }
        });

        //back to previous
        setSupportActionBar(toolbarLanguage);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(null);
            toolbarLanguage.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
    }
}