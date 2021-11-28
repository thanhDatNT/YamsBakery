package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.thanhdat.yams.Fragments.ChoosePaymentMethodFragment;
import com.thanhdat.yams.Fragments.PaymentSuccessFragment;
import com.thanhdat.yams.Fragments.VoucherFragment;
import com.thanhdat.yams.Models.PaymentProduct;
import com.thanhdat.yams.R;
import com.thanhdat.yams.adapter.PaymentAdapter;
import com.thanhdat.yams.adapter.SeeReviewAdapter;

import java.util.ArrayList;
import java.util.Calendar;

public class PaymentActivity extends AppCompatActivity {

    ImageButton btnOpenChooseTime, btnOpenChooseAddress;
    AppCompatButton btnAddToPayment;
    TextView txtOpenChoosePaymentMethod, txtOpenVoucher;

    RecyclerView rcvPaymentProduct;
    ArrayList<PaymentProduct> paymentProducts;
    PaymentAdapter adapter;
    Calendar c;
    DatePickerDialog dpd;
    TimePickerDialog tpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        linkViews();
        addEvents();
        initData();
        configRecyclerView();
    }

    private void linkViews() {
        btnOpenChooseTime = findViewById(R.id.btnOpenChooseTime);
        txtOpenChoosePaymentMethod = findViewById(R.id.txtOpenChoosePaymentMethod);
        txtOpenVoucher = findViewById(R.id.txtOpenVoucher);
        btnAddToPayment = findViewById(R.id.btnAddToPayment);
        btnOpenChooseAddress = findViewById(R.id.btnOpenChooseAddress);

        rcvPaymentProduct = findViewById(R.id.rcvPaymentProduct);
    }

    private void configRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvPaymentProduct.setLayoutManager(manager);

        rcvPaymentProduct.setHasFixedSize(true);
        rcvPaymentProduct.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {

        paymentProducts = new ArrayList<>();
        paymentProducts.add(new PaymentProduct(R.drawable.img_bdcake, "Birthday Cake", 100000, "Cốt bánh mềm xốp, kết hợp thêm hương vị chocolate thơm lừng", 1));
        paymentProducts.add(new PaymentProduct(R.drawable.img_matcha_maracon, "Maracon Matcha", 20000, "Cốt bánh mềm xốp, kết hợp thêm hương vị chocolate thơm lừng", 2));

//
//         adapter = new PaymentAdapter(getApplicationContext(),paymentProducts);
//
//        rcvPaymentProduct.setAdapter(adapter);

}



    private void addEvents() {

        btnOpenChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOpenBottomSheetDialog();

            }
        });

        //Open ChoosePaymentMethodFragment
        txtOpenChoosePaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fm = getSupportFragmentManager();
                ChoosePaymentMethodFragment fragment= new ChoosePaymentMethodFragment();
                fm.beginTransaction().replace(R.id.layoutContainerPayment, fragment).commit();

                    txtOpenChoosePaymentMethod.setVisibility(View.GONE);

            }
        });

        //Open voucher
        txtOpenVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                VoucherFragment fragment= new VoucherFragment();
                fm.beginTransaction().replace(R.id.layoutContainerPayment, fragment).commit();

                txtOpenVoucher.setVisibility(View.GONE);
            }
        });

        //Open Payment success
        btnAddToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                PaymentSuccessFragment fragment= new PaymentSuccessFragment();
                fm.beginTransaction().replace(R.id.layoutContainerPayment, fragment).commit();
           }
        });

        //Open Map
        btnOpenChooseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaymentActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });

    }

    private void clickOpenBottomSheetDialog() {
        c = Calendar.getInstance();
        View viewDialog = getLayoutInflater().inflate(R.layout.fragment_choose_time, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewDialog);
        bottomSheetDialog.show();

            //Ngăn người dùng bấm ra ngoài dialog
        bottomSheetDialog.setCancelable(false);

        ImageButton imvCancel = viewDialog.findViewById(R.id.imvCancel);
        imvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
            }
        });
        ImageButton imbDate, imbTime;
        TextView txtTime, txtDate;
        txtTime = viewDialog.findViewById(R.id.txtTime);
        txtDate = viewDialog.findViewById(R.id.txtDate);
        imbDate= viewDialog.findViewById(R.id.btnDate);
        imbDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(PaymentActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        txtDate.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                    }
                }, day, month, year);
                dpd.show();
            }
        });
        imbTime= viewDialog.findViewById(R.id.btnTime);
        imbTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int minute = c.get(Calendar.MINUTE);
                int hour = c.get(Calendar.HOUR_OF_DAY);
                Log.d("TAG", "Time picker");
                tpd = new TimePickerDialog(PaymentActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int mHour, int mMinute) {
                        txtTime.setText((mHour) + ":" + mMinute);
                    }
                }, hour, minute, false);
                tpd.show();
            }
        });
    }
}