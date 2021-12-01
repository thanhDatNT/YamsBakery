package com.thanhdat.yams.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.thanhdat.yams.R;

public class WriteReviewActivity extends AppCompatActivity {

    AppCompatButton btnUpReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);
        linkViews();
        addEvent();
    }

    private void linkViews() {
        btnUpReview = findViewById(R.id.btnUpReview);
    }

    private void addEvent() {
        btnUpReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WriteReviewActivity.this, SeeReviewActivity.class));
            }
        });
    }

}
