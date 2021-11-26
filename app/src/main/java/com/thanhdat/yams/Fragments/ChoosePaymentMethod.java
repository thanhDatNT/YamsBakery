package com.thanhdat.yams.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.thanhdat.yams.R;

public class ChoosePaymentMethod extends Fragment {
    RadioButton radMomo, radZaloPay, radCod;
    ImageButton btnOpenChoosePaymentMethod;
    Button btnConfirm;

    public ChoosePaymentMethod(FragmentManager supportFragmentManager, int behaviorResumeOnlyCurrentFragment) {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_payment_method, container, false);
    }
}
