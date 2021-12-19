package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.R;

import java.util.ArrayList;
import java.util.List;


public class ProductDetailsActivity extends AppCompatActivity {
    AppCompatButton btnPayment, btnAddToCart;
    TextView txtSeeReview,txtProductDetailName, txtProductPrice, txtMPrice, txtLPrice, txtXLPrice, txtTopping1Price, txtTopping2Price, txtTopping3Price, txtProductQuantity,txtStartVote, txtVoteQuality,txtProductDetailDescrip, txtOldPrice;
    Toolbar toolbar;
    RadioGroup radGroupSize;
    RadioButton radSizeM, radSizeL, radSizeXL;
    CheckBox chkTopping1, chkTopping2, chkTopping3,chkLike ;
    ImageButton imbAdd, imbSubtract, imbFavourite;
    ImageView imvProductDetailThumb;
    String topping = "";
    String size = "M";
    int total = 0, flag = 0, totalSize = 0, quantity = 1, productID;
    ArrayList<Product> productList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        linkViews();
        totalMoney();
        addEvent();
        loadData();
        addEventFavourite();
    }


    private void loadData() {
//        Bundle bundle= getIntent().getExtras();
//        Product product = (Product) bundle.get("productItem");
//        Picasso.get().load(product.getThumbnail()).into(imvProductDetailThumb);
//        txtProductDetailName.setText(product.getName());
//        txtProductPrice.setText(String.valueOf(product.getCurrentPrice()));
//        txtStartVote.setText(String.valueOf(product.getRating()));
//        txtVoteQuality.setText(String.valueOf(product.getChecked()));
//        txtProductDetailDescrip.setText(product.getDescription());
//        ArrayList<String> ListTopings = (ArrayList<String>) product.getTopping();
//        chkTopping1.setText(ListTopings.get(0));
//        chkTopping2.setText(ListTopings.get(1));
//        chkTopping3.setText(ListTopings.get(2));

            Intent intent = getIntent();
            int productID = intent.getExtras().getInt("idProduct");
            Product itemProduct = new Product();
            int price = 0;
        for(int i = 0; i < MainActivity.productList.size(); i++)
        {
            if(productID == MainActivity.productList.get(i).getId())
            {
                itemProduct = MainActivity.productList.get(i);
                Picasso.get().load(itemProduct.getThumbnail()).into(imvProductDetailThumb);
                txtProductDetailName.setText(itemProduct.getName());
//                txtOldPrice.setText(String.valueOf(itemProduct.getPrice()));
                    if(itemProduct.getTag().equals("Promo")){

                        SpannableString spannableString= new SpannableString(String.format("%.0f",itemProduct.getPrice()) +"đ");
                        spannableString.setSpan(new StrikethroughSpan(),0, 5, 0);
                        txtOldPrice.setText(spannableString);
                        txtOldPrice.setVisibility(View.VISIBLE);
                    }

                price = (int) itemProduct.getCurrentPrice();
                txtProductPrice.setText(String.valueOf(price));
                txtMPrice.setText(String.valueOf(price));
                txtLPrice.setText(String.valueOf(price+5000));
                txtXLPrice.setText(String.valueOf(price+10000));

                txtStartVote.setText(String.valueOf(itemProduct.getRating()));
                txtVoteQuality.setText(String.valueOf(itemProduct.getChecked())+"+");
                txtProductDetailDescrip.setText(itemProduct.getDescription());

                List<String> listToppings = itemProduct.getTopping();
                 chkTopping1.setText(listToppings.get(0));
                 chkTopping2.setText(listToppings.get(1));
                 chkTopping3.setText(listToppings.get(2));

            }


        }


    }
    private void addEventFavourite() {
        chkLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
    }

    private void addEvent() {
//        Test product id
//        Intent intent= getIntent();
//        productID= intent.getIntExtra(Constant.ID_PRODUCT, 1);
//        Toast.makeText(this, "Product id is "+ productID, Toast.LENGTH_LONG).show();

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
                Intent intent2= new Intent(ProductDetailsActivity.this, OrderActivity.class);
                Bundle bundle= new Bundle();
                bundle.putInt(Constant.ID_PRODUCT, productID);
                bundle.putInt(Constant.QUANTITY_PRODUCT, quantity);
                bundle.putString(Constant.TOPPING_PRODUCT, topping);
                bundle.putString(Constant.SIZE_PRODUCT, size);
                bundle.putDouble(Constant.PRICE_PRODUCT, total);
                intent2.putExtra(Constant.STRING_INTENT, bundle);
                startActivity(intent2);
            }
        });
//        Send product item to cart
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(ProductDetailsActivity.this, CartActivity.class);
                Bundle bundle= new Bundle();
                bundle.putInt(Constant.ID_PRODUCT, productID);
                bundle.putInt(Constant.QUANTITY_PRODUCT, quantity);
                bundle.putString(Constant.TOPPING_PRODUCT, topping);
                bundle.putString(Constant.SIZE_PRODUCT, size);
                bundle.putDouble(Constant.PRICE_PRODUCT, total);
                intent1.putExtra(Constant.STRING_INTENT, bundle);
                startActivity(intent1);
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
        btnPayment.setText("Mua hàng " + txtMPrice.getText().toString());
        radGroupSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(flag == 0){
                    if(i == R.id.radSizeM){
                        size = "M";
                        totalSize += Integer.parseInt(txtMPrice.getText().toString());
                    }
                    else if(i == R.id.radSizeL){
                        size= "L";
                        totalSize += Integer.parseInt(txtLPrice.getText().toString());
                    }
                    else if(i == R.id.radSizeXL){
                        size= "XL";
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
                btnPayment.setText("Mua hàng " + total);
            }
        });

        //Topping total money
        int topping1 = Integer.parseInt(txtTopping1Price.getText().toString()),
                topping2 = Integer.parseInt(txtTopping2Price.getText().toString()),
                topping3 = Integer.parseInt(txtTopping3Price.getText().toString());

        chkTopping1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    total += topping1;
                    topping+= chkTopping1.getText() + ", ";
                }
                else total -= topping1;
                btnPayment.setText("Mua hàng " + total);
            }
        });
        chkTopping2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    total += topping2;
                    topping += chkTopping2.getText() + ", ";
                }
                else total -= topping2;
                btnPayment.setText("Mua hàng " + total);
            }
        });
        chkTopping3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    total += topping3;
                    topping += chkTopping3.getText() + ", ";
                }
                else total -= topping3;
                btnPayment.setText("Mua hàng " + total);
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
            //btnPayment.setText("Mua hàng " + total);
        }
    };

    private void linkViews() {
        txtProductDetailName=findViewById(R.id.txtProductDetailName);
        txtSeeReview = findViewById(R.id.txtSeeReview);
        txtProductPrice = findViewById(R.id.txtProductDetailPrice);
        txtMPrice = findViewById(R.id.txtMPrice);
        txtLPrice = findViewById(R.id.txtLPrice);
        txtXLPrice = findViewById(R.id.txtXLPrice);
        txtTopping1Price = findViewById(R.id.txtTopping1Price);
        txtTopping2Price = findViewById(R.id.txtTopping2Price);
        txtTopping3Price = findViewById(R.id.txtTopping3Price);
        txtProductQuantity = findViewById(R.id.txtProductQuantity);
        txtStartVote = findViewById(R.id.txtStarVote);
        txtVoteQuality=findViewById(R.id.txtVoteQuantity);
        txtProductDetailDescrip = findViewById(R.id.txtProductDetailDescrip);
        txtOldPrice = findViewById(R.id.txtOldPrice);


        btnPayment = findViewById(R.id.btnAddToPayment);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        toolbar = findViewById(R.id.toolbarProduct);

        radGroupSize = findViewById(R.id.radGroupSize);

        radSizeM = findViewById(R.id.radSizeM);
        radSizeL = findViewById(R.id.radSizeL);
        radSizeXL = findViewById(R.id.radSizeXL);

        chkTopping1 = findViewById(R.id.chkTopping1);
        chkTopping2 = findViewById(R.id.chkTopping2);
        chkTopping3 = findViewById(R.id.chkTopping3);
        chkLike = findViewById(R.id.chkLike);

        imbAdd = findViewById(R.id.imbAdd);
        imbSubtract = findViewById(R.id.imbSubtract);


        imvProductDetailThumb = findViewById(R.id.imvProductDetailThumb);

    }

}