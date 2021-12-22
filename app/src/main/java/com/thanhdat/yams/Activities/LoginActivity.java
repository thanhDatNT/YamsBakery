package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.thanhdat.yams.Fragments.LoginFragment;
import com.thanhdat.yams.Fragments.PaymentMethodsFragment;
import com.thanhdat.yams.Fragments.RegisterFragment;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.R;

public class LoginActivity extends AppCompatActivity implements OnClickInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);

        getPurpose();
    }

    private void getPurpose() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.getIntExtra("login", 1) == 0){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.layoutLoginContainer, new LoginFragment());
                transaction.commit();
            }
            else{
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.layoutLoginContainer, new RegisterFragment());
                transaction.commit();
            }

        }
    }

    @Override
    public void setClick(int number) {
        if(number == 1){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.layoutLoginContainer, new RegisterFragment());
            transaction.commit();
        }
        else{
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.layoutLoginContainer, new LoginFragment());
            transaction.commit();
        }
    }
}