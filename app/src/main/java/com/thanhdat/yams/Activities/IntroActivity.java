package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.thanhdat.yams.R;

public class IntroActivity extends AppCompatActivity {
    private AppCompatButton btnSignIn, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        View decorView = getWindow().getDecorView();
//      Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        linkViews();
        addEvents();
    }

    private void addEvents() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, RegisterActivity.class));
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
            }
        });
    }

    private void linkViews() {
        btnSignIn= findViewById(R.id.buttonSignInIntro);
        btnSignUp= findViewById(R.id.buttonSignUpIntro);
    }
}