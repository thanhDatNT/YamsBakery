package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.thanhdat.yams.R;

public class IntroActivity extends AppCompatActivity {
    private AppCompatButton btnSignIn, btnSignUp;
    private TextView tvSkip, tvSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);

//        View decorView = getWindow().getDecorView();
//      Hide the status bar.
//        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);

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
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
            }
        });
//        Change font family for slogan
        Typeface typeface= Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");
        tvSlogan.setTypeface(typeface);
    }

    private void linkViews() {
        btnSignIn= findViewById(R.id.buttonSignInIntro);
        btnSignUp= findViewById(R.id.buttonSignUpIntro);
        tvSlogan= findViewById(R.id.tvSlogan);
        tvSkip= findViewById(R.id.tvSkip);
    }
}