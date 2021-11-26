package com.thanhdat.yams.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.thanhdat.yams.Activities.MainActivity;
import com.thanhdat.yams.Activities.ProductDetailsActivity;
import com.thanhdat.yams.R;

public class LanguageFragment extends Fragment {

    RadioGroup radGroupLanguage;
    RadioButton radVietNam, radEnglish, radJapan;
    Toolbar toolbarLanguage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_language, container, false);
        //link views
        radGroupLanguage = view.findViewById(R.id.radGroupLanguage);

        radVietNam = view.findViewById(R.id.radVietNam);
        radEnglish = view.findViewById(R.id.radEnglish);
        radJapan = view.findViewById(R.id.radJapan);

        toolbarLanguage = view.findViewById(R.id.toolbarLanguage);

        addEvent();

        return view;
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

        //back to profile
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if(activity != null){
            activity.setSupportActionBar(toolbarLanguage);
            if(activity.getSupportActionBar() != null){
                activity.getSupportActionBar().setTitle(null);
            }
            toolbarLanguage.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //back to profile fragment
                    if(getActivity() != null){
                        getActivity().onBackPressed();
                    }
                }
            });
        }

    }
}
