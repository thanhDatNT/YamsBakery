package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Fragments.ChooseBankFragment;
import com.thanhdat.yams.Fragments.PaymentMethodsFragment;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.R;

public class PaymentActivity extends AppCompatActivity implements OnClickInterface {
    String paymentMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layoutContainerPayment, new PaymentMethodsFragment());
        transaction.commit();
    }

    @Override
    public void setClick(int number) {
        if (number == 1) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.layoutContainerPayment, new ChooseBankFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (number == 3) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.layoutContainerPayment, new PaymentMethodsFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (number == 2) {
            paymentMethod = PaymentMethodsFragment.pMethod;
            Intent intent = new Intent(this, OrderActivity.class);
            intent.putExtra(Constant.PAYMENT_INTENT, paymentMethod);
            setResult(Constant.RESULT_PAYMENT, intent);
            finish();
        }
    }
}