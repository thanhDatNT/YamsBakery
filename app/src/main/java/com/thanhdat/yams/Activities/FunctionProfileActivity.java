package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.thanhdat.yams.Fragments.LanguageFragment;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.R;

public class FunctionProfileActivity extends AppCompatActivity{

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_profile);
        addEvent();
    }

    @SuppressLint("WrongConstant")
    private void addEvent() {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = null;

        intent = getIntent();
        if(intent.getFlags() == R.id.lnVoucherProfile){
            //new voucher fragment?
        }
        else if(intent.getFlags() == R.id.lnMessageProfile){
            //new message fragment?
        }
        else if(intent.getFlags() == R.id.lnLanguageProfile){
            fragment = new LanguageFragment();
        }

        transaction.replace(R.id.layoutContainerProfile, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }
}