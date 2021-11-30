package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.thanhdat.yams.R;


public class ProductDetailsActivity extends AppCompatActivity {
    AppCompatButton btnPayment;
    TextView txtSeeReview, txtProductPrice, txtMPrice, txtLPrice, txtXLPrice, txtTopping1Price, txtTopping2Price, txtTopping3Price, txtProductQuantity;
    Toolbar toolbar;
    RadioGroup radGroupSize;
    RadioButton radSizeM, radSizeL, radSizeXL;
    CheckBox chkTopping1, chkTopping2, chkTopping3;
    ImageButton imbAdd, imbSubtract;

    int total = 0, flag = 0, totalSize = 0, quantity = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        linkViews();
        addEvent();
        totalMoney();
    }

    private void linkViews() {
        txtSeeReview = findViewById(R.id.txtSeeReview);
        txtProductPrice = findViewById(R.id.txtProductDetailPrice);
        txtMPrice = findViewById(R.id.txtMPrice);
        txtLPrice = findViewById(R.id.txtLPrice);
        txtXLPrice = findViewById(R.id.txtXLPrice);
        txtTopping1Price = findViewById(R.id.txtTopping1Price);
        txtTopping2Price = findViewById(R.id.txtTopping2Price);
        txtTopping3Price = findViewById(R.id.txtTopping3Price);
        txtProductQuantity = findViewById(R.id.txtProductQuantity);

        btnPayment = findViewById(R.id.btnAddToPayment);

        toolbar = findViewById(R.id.toolbarProduct);

        radGroupSize = findViewById(R.id.radGroupSize);

        radSizeM = findViewById(R.id.radSizeM);
        radSizeL = findViewById(R.id.radSizeL);
        radSizeXL = findViewById(R.id.radSizeXL);

        chkTopping1 = findViewById(R.id.chkTopping1);
        chkTopping2 = findViewById(R.id.chkTopping2);
        chkTopping3 = findViewById(R.id.chkTopping3);

        imbAdd = findViewById(R.id.imbAdd);
        imbSubtract = findViewById(R.id.imbSubtract);
    }

    private void addEvent() {
        txtSeeReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductDetailsActivity.this,SeeReviewActivity.class));
            }
        });
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add product to payment?
                startActivity(new Intent(ProductDetailsActivity.this,PaymentActivity.class));
            }
        });

        //Back previous
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(null);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(ProductDetailsActivity.this,MainActivity.class));
                }
            });
        }
    }

    @SuppressLint("SetTextI18n")
    public void totalMoney() {
        //Size total money
        btnPayment.setText(txtMPrice.getText().toString());
        radGroupSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(flag == 0){
                    if(i == R.id.radSizeM){
                        totalSize += Integer.parseInt(txtMPrice.getText().toString());
                    }
                    else if(i == R.id.radSizeL){
                        totalSize += Integer.parseInt(txtLPrice.getText().toString());
                    }
                    else if(i == R.id.radSizeXL){
                        totalSize += Integer.parseInt(txtXLPrice.getText().toString());
                    }
                    flag = 1;

                }else {
                    total -= totalSize;
                    totalSize = 0;
                    if(i == R.id.radSizeM){
                        totalSize += Integer.parseInt(txtMPrice.getText().toString());
                    }
                    else if(i == R.id.radSizeL){
                        totalSize += Integer.parseInt(txtLPrice.getText().toString());
                    }
                    else if(i == R.id.radSizeXL){
                        totalSize += Integer.parseInt(txtXLPrice.getText().toString());
                    }
                }
                total += totalSize;
                btnPayment.setText(Integer.toString(total));
            }
        });

        //Topping total money
        int topping1 = Integer.parseInt(txtTopping1Price.getText().toString()),
                topping2 = Integer.parseInt(txtTopping2Price.getText().toString()),
                topping3 = Integer.parseInt(txtTopping3Price.getText().toString());

        chkTopping1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    total += topping1;
                else total -= topping1;
                btnPayment.setText(Integer.toString(total));
            }
        });
        chkTopping2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    total += topping2;
                else total -= topping2;
                btnPayment.setText(Integer.toString(total));
            }
        });
        chkTopping3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                    total += topping3;
                else total -= topping3;
                btnPayment.setText(Integer.toString(total));
            }
        });

        //add quantity
        imbAdd.setOnClickListener(myClick);
        imbSubtract.setOnClickListener(myClick);
    }
    View.OnClickListener myClick = new View.OnClickListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.imbAdd){
                quantity += 1;
                //total*=2;
            }
            if(view.getId() == R.id.imbSubtract){
                if(quantity > 1){
                    quantity -= 1;
                    //total/=2;
                }else {
                    Toast.makeText(ProductDetailsActivity.this, "Số lượng sản phẩm phải lớn hơn 0!", Toast.LENGTH_SHORT).show();
                }
            }
            txtProductQuantity.setText(Integer.toString(quantity));
            //btnPayment.setText(Integer.toString(total));
        }
    };
}