package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.thanhdat.yams.Models.Diet;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.DietAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DietActivity extends AppCompatActivity {
    RadioButton radNam,radNu;
    Button btnTinhBMI;
    EditText edtNhapWeight,edtNhapHeight;
    ListView lvDietProduct;
    ArrayList<Diet>diets;
    DietAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        linkView();
        addEvent();
    }


    private void linkView() {
        radNam=findViewById(R.id.radNam);
        radNu=findViewById(R.id.radNu);
        btnTinhBMI=findViewById(R.id.btnTinhBMI);

        edtNhapHeight=findViewById(R.id.edtNhapHeigt);
        edtNhapWeight=findViewById(R.id.edtNhapWeight);

        lvDietProduct=findViewById(R.id.lvDietProduct);

    }
    private void addEvent() {
        btnTinhBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog= new Dialog(DietActivity.this);
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
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        diets=new ArrayList<Diet>();
                        diets.add(new Diet(R.drawable.diet_pearmuffins,"Pear Muffins","30 000đ","Những chiếc bánh nướng xốp béo ngậy này rất ngon khi ấm nóng khi kẹo bơ cứng vẫn còn chảy",4.8,25.0));
                        diets.add(new Diet(R.drawable.dietcoffee_cake,"Coffee Cake","60 000đ","Một phiên bản ngon nhưng nhẹ hơn của món yêu thích vị cà phê và bánh óc chó",4.8,25.0));
                        diets.add(new Diet(R.drawable.dietburst_muffins,"Coffee Cake","60 000đ","Một phiên bản ngon nhưng nhẹ hơn của món yêu thích vị cà phê và bánh óc chó",4.8,25.0));
                        diets.add(new Diet(R.drawable.dietdrizzle_cake,"Coffee Cake","60 000đ","Một phiên bản ngon nhưng nhẹ hơn của món yêu thích vị cà phê và bánh óc chó",4.8,25.0));
                        adapter=new DietAdapter(DietActivity.this,R.layout.items_diet_product,diets);
                        lvDietProduct.setAdapter(adapter);
                        dialog.dismiss();
                    }

                });
                Button btnReType = dialog.findViewById(R.id.btnReType);
                btnReType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtNhapHeight.setText("");
                        edtNhapWeight.setText("");
                        lvDietProduct.setAdapter(null);

                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

}