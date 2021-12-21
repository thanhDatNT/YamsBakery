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
    TextView txtSeeReview,txtProductDetailName, txtProductPrice, txtMPrice, txtLPrice, txtXLPrice,
            txtTopping1Price, txtTopping2Price, txtTopping3Price, txtProductQuantity,
            txtStartVote, txtVoteCount, txtPDDescription, txtOldPrice;
    Toolbar toolbar;
    RadioGroup radGroupSize;
    RadioButton radSizeM, radSizeL, radSizeXL;
    CheckBox chkTopping1, chkTopping2, chkTopping3, chkLike ;
    ImageButton imbAdd, imbSubtract;
    ImageView imvProductDetailThumb;
    String topping = "";
    String size = "M";
    int total = 0;
    int totalSize = 0;
    int quantity = 1;
    int productID;
    ArrayList<Product> productList;
    Product itemProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        linkViews();
        loadData();
        totalMoney();
        navigate();
//        addEventFavourite();
    }


    @SuppressLint("SetTextI18n")
    private void loadData() {
        Intent intent = getIntent();
        productID= intent.getIntExtra(Constant.ID_PRODUCT, 1);
        productList = MainActivity.productList;
        itemProduct = productList.get(productID - 1);

        total= (int) itemProduct.getCurrentPrice();

        Picasso.get().load(itemProduct.getThumbnail()).into(imvProductDetailThumb);
        txtProductDetailName.setText(itemProduct.getName());
        txtProductPrice.setText(String.valueOf(total));
        txtStartVote.setText(String.valueOf(itemProduct.getRating()));
        txtVoteCount.setText(String.valueOf(itemProduct.getChecked()));
        txtPDDescription.setText(itemProduct.getDescription());
        chkLike.setChecked(itemProduct.isFavorite());
        if(itemProduct.isFavorite()){
            chkLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    itemProduct.setFavorite(false);
                }
            });
        }else {
            chkLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    itemProduct.setFavorite(true);
                }
            });
        }

        ArrayList<String> toppings = (ArrayList<String>) itemProduct.getTopping();
        chkTopping1.setText(toppings.get(0));
        chkTopping2.setText(toppings.get(1));
        chkTopping3.setText(toppings.get(2));

        if (itemProduct.getTag().equals("Promo")) {
            SpannableString spannableString = new SpannableString(String.format("%.0f", itemProduct.getPrice()) + "đ");
            spannableString.setSpan(new StrikethroughSpan(), 0, 6, 0);
            txtOldPrice.setText(spannableString);
            txtOldPrice.setVisibility(View.VISIBLE);
        }

        txtMPrice.setText(String.valueOf(total));
        txtLPrice.setText(String.valueOf(total + 5000));
        txtXLPrice.setText(String.valueOf(total + 10000));

        btnPayment.setText("Mua ngay " + total);
    }

//    private void addEventFavourite() {
//        chkLike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(loadData().g)
//            }
//        });
//    }

    private void navigate() {
        txtSeeReview.setOnClickListener(view -> startActivity(new Intent(ProductDetailsActivity.this, SeeReviewActivity.class)));

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send product to order
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
                    onBackPressed();
                }

            });
        }
    }

    @SuppressLint("SetTextI18n")
    public void totalMoney() {
        //Size total money
        radSizeM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                totalSize = Integer.parseInt(txtMPrice.getText().toString());
                if(isChecked){
                    size = "M";
                    total += totalSize;
                }
                else {
                    total -= totalSize;
                }
                btnPayment.setText("Mua hàng " + total);
            }
        });
        radSizeL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                totalSize = Integer.parseInt(txtLPrice.getText().toString());
                if(isChecked){
                    size = "L";
                    total += totalSize;
                }
                else {
                    total -= totalSize;
                }
                btnPayment.setText("Mua hàng " + total);
            }
        });
        radSizeXL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                totalSize = Integer.parseInt(txtXLPrice.getText().toString());
                if(isChecked){
                    size = "XL";
                    total += totalSize;
                }
                else {
                    total -= totalSize;
                }
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
                    topping += chkTopping1.getText() + ", ";
                }
                else {
                    if(topping.contains(chkTopping1.getText().toString())){
                        topping.replace(chkTopping1.getText(), "");
                    }
                    total -= topping1;}
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
                else {
                    if(topping.contains(chkTopping2.getText().toString())){
                        topping.replace(chkTopping2.getText(), "");
                    }
                    total -= topping2;}
                btnPayment.setText("Mua hàng " + total);
            }
        });
        chkTopping3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    total += topping3;
                    topping += chkTopping3.getText();
                }
                else {
                    if(topping.contains(chkTopping3.getText().toString())){
                        topping.replace(chkTopping3.getText(), "");
                    }
                    total -= topping3;}
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
                total += itemProduct.getCurrentPrice();
            }
            if(view.getId() == R.id.imbSubtract){
                if(quantity > 1){
                    quantity -= 1;
                    total -= itemProduct.getCurrentPrice();
                }else {
                    Toast.makeText(ProductDetailsActivity.this, "Số lượng sản phẩm phải lớn hơn 0!", Toast.LENGTH_SHORT).show();
                }
            }
            txtProductQuantity.setText(Integer.toString(quantity));
            btnPayment.setText("Mua ngay " + total);
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
        txtVoteCount=findViewById(R.id.txtVoteQuantity);
        txtPDDescription = findViewById(R.id.txtProductDetailDescrip);
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