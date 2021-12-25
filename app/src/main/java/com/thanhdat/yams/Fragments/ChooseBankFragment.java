package com.thanhdat.yams.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.thanhdat.yams.Activities.CartActivity;
import com.thanhdat.yams.Activities.OrderActivity;
import com.thanhdat.yams.Activities.PaymentActivity;
import com.thanhdat.yams.Adapters.SimpleViewGroupAdapter;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.TextThumbView;
import com.thanhdat.yams.R;

import java.util.ArrayList;

public class ChooseBankFragment extends Fragment {
    private ListView lvBank;
    ImageButton imbBack;
    ArrayList<TextThumbView> bankList;
    String pBank;
    OnClickInterface clickInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_bank,container, false);

        //link views
        imbBack = view.findViewById(R.id.imbBack);
        lvBank= view.findViewById(R.id.lvBank);
        addEventFunction();
        addEventBack();
        return view;
    }

    private void addEventBack() {
        imbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickInterface = (OnClickInterface) getActivity();
                if(view.getId() == R.id.imbBack){
                    clickInterface.setClick(3);
                }
            }
        });
    }


    private void addEventFunction() {
        initData();
        lvBank.setOnItemClickListener((parent, view, position, id) -> {
            pBank = bankList.get(position).getCate();
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            PaymentMethodsFragment methodsFragment = new PaymentMethodsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("bank", pBank);
            methodsFragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.layoutContainerPayment, methodsFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        });
    }



    private void initData() {
        bankList = new ArrayList<>();
        bankList.add(new TextThumbView(R.drawable.img_bidv, "BIDV - Ngân hàng đầu tư và phát triển"));
        bankList.add(new TextThumbView(R.drawable.img_ocb, "OCB - Ngân hàng Phương Đông"));
        bankList.add(new TextThumbView(R.drawable.img_vietcombank, "Vietcombank - Ngân hàng Ngoại thương"));
        bankList.add(new TextThumbView(R.drawable.img_viettinbank, "Viettinbank - Ngân hàng TMCP Công Thương"));
        bankList.add(new TextThumbView(R.drawable.img_sacombank, "Sacombank - Ngân hàng TMCP Sài Gòn"));
        SimpleViewGroupAdapter adapter = new SimpleViewGroupAdapter(getContext(), R.layout.item_bank, bankList);
        lvBank.setAdapter(adapter);
    }
}
