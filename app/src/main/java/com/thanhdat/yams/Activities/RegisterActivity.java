package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;

import com.thanhdat.yams.R;

public class RegisterActivity extends AppCompatActivity {
    private AppCompatButton btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        addEvents();
    }

    private void addEvents() {

    }

    private void initViews() {
        btnSignUp=findViewById(R.id.btnSignUp);
    }
}