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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.thanhdat.yams.R;

public class IntroActivity extends AppCompatActivity {
    private AppCompatButton btnSignIn, btnSignUp;
    private TextView tvSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_intro);

        linkViews();
        addEvents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(IntroActivity.this, MainActivity.class));
        }
    }

    private void addEvents() {
        Intent intent = new Intent(this, LoginActivity.class);
        btnSignIn.setOnClickListener(v -> {
            intent.putExtra("login", 0);
            startActivity(intent);
        });
        btnSignUp.setOnClickListener(v -> {
            intent.putExtra("register", 1);
            startActivity(intent);
        });
//        Change font family for slogan
        Typeface typeface= Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");
        tvSlogan.setTypeface(typeface);
    }

    private void linkViews() {
        btnSignIn= findViewById(R.id.buttonSignInIntro);
        btnSignUp= findViewById(R.id.buttonSignUpIntro);
        tvSlogan= findViewById(R.id.tvSlogan);
    }
}