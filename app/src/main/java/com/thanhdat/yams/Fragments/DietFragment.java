package com.thanhdat.yams.Fragments;

import static com.thanhdat.yams.Activities.MainActivity.productList;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.thanhdat.yams.Activities.CartActivity;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapters.DietAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class DietFragment extends Fragment {
    RadioButton radNam,radNu;
    Button btnCalculateBMI;
    EditText edtInputWeight,edtInputHeight;
    RecyclerView rcvDietProduct;
    TextView txtSuggest;
    Toolbar toolbarDiet;
    OnClickInterface onClickInterface;
    DietAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet, container, false);
        radNam=view.findViewById(R.id.radNam);
        radNu=view.findViewById(R.id.radNu);
        btnCalculateBMI=view.findViewById(R.id.btnCalculateBMI);

        edtInputHeight=view.findViewById(R.id.edtInputHeight);
        edtInputWeight=view.findViewById(R.id.edtInputWeight);

        rcvDietProduct=view.findViewById(R.id.rcvDietProduct);

        txtSuggest = view.findViewById(R.id.txtSuggest);

        toolbarDiet = view.findViewById(R.id.toolbarDiet);
        addEvent();
        addEventToCart();

        return view;
    }



    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.diet_heading,menu);
    }
    private void addEventToCart() {

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if(activity != null){
            activity.setSupportActionBar(toolbarDiet);
            if(activity.getSupportActionBar() != null){
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                activity.getSupportActionBar().setTitle(null);
            }
        }
        toolbarDiet.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(getContext(), CartActivity.class);
                startActivity(intent);
                return false;
            }
        });

    }
    private void addEvent() {
        btnCalculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog= new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_show_bmi);
                double height = Double.parseDouble(edtInputHeight.getText().toString());
                double weight=Double.parseDouble(edtInputWeight.getText().toString());
                DecimalFormat d = new DecimalFormat("0.00");
                double BMI = weight/Math.pow(height,2)*10000;

                TextView txtBMI = dialog.findViewById(R.id.txtBMI);
                TextView txtResult =dialog.findViewById(R.id.txtResult);

                txtBMI.setText(d.format(BMI) +" ");
                if(BMI<18)
                    txtResult.setText("Gầy");
                else if(18<=BMI && BMI<23)
                    txtResult.setText("Cân đối");
                else if(23<=BMI && BMI<30)
                    txtResult.setText("Thừa cân");
                else if(30<=BMI)
                    txtResult.setText("Béo phì");

                Button btnConfirm= dialog.findViewById(R.id.btnConfirm);
                ArrayList<Product> diets= new ArrayList<>();
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        rcvDietProduct.setLayoutManager(layoutManager);
                        for (Product p : productList)
                            if(BMI<18){
                                {
                                    if(p.getDiet().equals("1")){
                                        diets.add(p);
                                    }
                                }
                            }
                            else if(18<=BMI && BMI<23){
                                {
                                    if(p.getDiet().equals("2")){
                                        diets.add(p);
                                    }
                                }
                            }
                            else if(23<=BMI && BMI<30){
                                {
                                    if(p.getDiet().equals("3")){
                                        diets.add(p);
                                    }
                                }
                            }
                            else if(30<=BMI){
                                {
                                    if(p.getDiet().equals("4")){
                                        diets.add(p);
                                    }
                                }
                            }

                        adapter=new DietAdapter( getContext(),diets,onClickInterface);
                        rcvDietProduct.setAdapter(adapter);
                        txtSuggest.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    }

                });
                Button btnReType = dialog.findViewById(R.id.btnReType);
                btnReType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtInputHeight.setText("");
                        edtInputWeight.setText("");
                        rcvDietProduct.setAdapter(null);

                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

}