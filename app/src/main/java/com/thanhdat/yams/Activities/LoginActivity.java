package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.thanhdat.yams.R;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    private TextView tvForgotPassword, tvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        addEvents();
    }

    private void addEvents() {
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void initViews() {
        tvForgotPassword= findViewById(R.id.tvForgotPass);
        tvSignUp= findViewById(R.id.tvSignUpLogin);
    }
}