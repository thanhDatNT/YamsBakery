package com.thanhdat.yams.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Diet;
import com.thanhdat.yams.Models.NewProduct;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.DietAdapter;
import com.thanhdat.yams.adapter.NewProductAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class DietFragment extends Fragment {
    RadioButton radNam,radNu;
    Button btnTinhBMI;
    EditText edtNhapWeight,edtNhapHeight;
    RecyclerView rcvDietProduct;
    TextView txtSuggest;


    //    ListView lvDietProduct;
//    ArrayList<Diet> diets;
    DietAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diet, container, false);
        radNam=view.findViewById(R.id.radNam);
        radNu=view.findViewById(R.id.radNu);
        btnTinhBMI=view.findViewById(R.id.btnTinhBMI);

        edtNhapHeight=view.findViewById(R.id.edtNhapHeigt);
        edtNhapWeight=view.findViewById(R.id.edtNhapWeight);

        rcvDietProduct=view.findViewById(R.id.rcvDietProduct);

        txtSuggest = view.findViewById(R.id.txtSuggest);
        addEvent();

        return view;
    }

    private void addEvent() {
        btnTinhBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog= new Dialog(getContext());
                dialog.setContentView(R.layout.showresult);
                double chieucao = Double.parseDouble(edtNhapHeight.getText().toString());
                double cannang=Double.parseDouble(edtNhapWeight.getText().toString());
                DecimalFormat d = new DecimalFormat("0.00");
                double BMI = cannang/Math.pow(chieucao,2)*10000;

                TextView txtBMI = dialog.findViewById(R.id.txtBMI);
                TextView txtResult =dialog.findViewById(R.id.txtResult);

                txtBMI.setText(d.format(BMI) +"");
                if(BMI<16)
                    txtResult.setText("Quá gầy");
                else if (16<=BMI && BMI<18)
                    txtResult.setText("Gầy");
                else if(19<=BMI && BMI<23)
                    txtResult.setText("Cân đối");
                else if(23<=BMI && BMI<25)
                    txtResult.setText("Béo phì 1");
                else if(25<=BMI && BMI <30)
                    txtResult.setText("Béo phì 2");
                else if(30<=BMI)
                    txtResult.setText("Béo phì 3");

                Button btnConfirm= dialog.findViewById(R.id.btnConfirm);
                ArrayList<Diet> diets= new ArrayList<>();
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        rcvDietProduct.setLayoutManager(layoutManager);

                        //diets=new ArrayList<Diet>();
                        diets.add(new Diet(R.drawable.img_summer_pudding,"Pear Muffins","30 000đ","Những chiếc bánh nướng xốp béo ngậy này rất ngon khi ấm nóng khi kẹo bơ cứng vẫn còn chảy",4.8,25.0));
                        diets.add(new Diet(R.drawable.img_summer_pudding,"Coffee Cake","60 000đ","Một phiên bản ngon nhưng nhẹ hơn của món yêu thích vị cà phê và bánh óc chó",4.8,25.0));
                        diets.add(new Diet(R.drawable.img_summer_pudding,"Coffee Cake","60 000đ","Một phiên bản ngon nhưng nhẹ hơn của món yêu thích vị cà phê và bánh óc chó",4.8,25.0));
                        diets.add(new Diet(R.drawable.img_mango_cake,"Coffee Cake","60 000đ","Một phiên bản ngon nhưng nhẹ hơn của món yêu thích vị cà phê và bánh óc chó",4.8,25.0));
                        diets.add(new Diet(R.drawable.img_mango_cake,"Coffee Cake","60 000đ","Một phiên bản ngon nhưng nhẹ hơn của món yêu thích vị cà phê và bánh óc chó",4.8,25.0));
                        diets.add(new Diet(R.drawable.img_mango_cake,"Coffee Cake","60 000đ","Một phiên bản ngon nhưng nhẹ hơn của món yêu thích vị cà phê và bánh óc chó",4.8,25.0));
                        diets.add(new Diet(R.drawable.img_mango_cake,"Coffee Cake","60 000đ","Một phiên bản ngon nhưng nhẹ hơn của món yêu thích vị cà phê và bánh óc chó",4.8,25.0));
                        diets.add(new Diet(R.drawable.img_mango_cake,"Coffee Cake","60 000đ","Một phiên bản ngon nhưng nhẹ hơn của món yêu thích vị cà phê và bánh óc chó",4.8,25.0));
                        diets.add(new Diet(R.drawable.img_mango_cake,"Coffee Cake","60 000đ","Một phiên bản ngon nhưng nhẹ hơn của món yêu thích vị cà phê và bánh óc chó",4.8,25.0));
                        diets.add(new Diet(R.drawable.img_mango_cake,"Coffee Cake","60 000đ","Một phiên bản ngon nhưng nhẹ hơn của món yêu thích vị cà phê và bánh óc chó",4.8,25.0));
                        adapter=new DietAdapter( getContext(),diets);
                        rcvDietProduct.setAdapter(adapter);
                        txtSuggest.setVisibility(View.VISIBLE);
                        dialog.dismiss();
                    }

                });
                Button btnReType = dialog.findViewById(R.id.btnReType);
                btnReType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtNhapHeight.setText("");
                        edtNhapWeight.setText("");
                        rcvDietProduct.setAdapter(null);

                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

}