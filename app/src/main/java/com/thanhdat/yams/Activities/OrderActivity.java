package com.thanhdat.yams.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Databases.OrderDatabase;
import com.thanhdat.yams.Fragments.ChoosePaymentMethodFragment;
import com.thanhdat.yams.Fragments.PaymentSuccessFragment;
import com.thanhdat.yams.Models.Cart;
import com.thanhdat.yams.Models.Product;
import com.thanhdat.yams.Models.Voucher;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapters.OrderAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class OrderActivity extends AppCompatActivity {
    ImageButton btnOpenChooseTime, btnOpenChooseAddress, edtCustomer;
    AppCompatButton btnCompleteOrder;
    RadioGroup rdgDelivery;
    TextView txtOpenChoosePaymentMethod, txtOpenVoucher, txtPaymentMethod,
            txtSubtotal, txtDeliFee, txtDeliDiscount, txtYamsCoupon,
            txtTotalBill, txtDeliTime, txtCusAddress, txtCustomer;
    Toolbar toolbarPayment;

    ArrayList<Product> products;
    ArrayList<Cart> orderProducts;
    RecyclerView rcvPaymentProduct;
    OrderAdapter adapter;
    Voucher voucher;

    Calendar c;
    DatePickerDialog dpd;
    TimePickerDialog tpd;

    boolean isDelivery = true;
    double subTotal = 0, deliFee = 25000, discount = 0, total = 0, deliDiscount = 0, orderDiscount = 0;
    String paymentMethod = "Thanh toán khi nhận hàng";
    String date = "", time = "";
    public static String orderCode = "";
    public static OrderDatabase orderDatabase;
    ActivityResultLauncher<Intent> resultLauncher;
    String CHANNEL_ID = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        orderDatabase = new OrderDatabase(this);

        linkViews();
        getDataFromIntent();
        configRecyclerView();
        initData();
        addEvents();
    }

    private void getDataFromIntent() {
        Intent intent= getIntent();
        Bundle bundle= intent.getBundleExtra(Constant.STRING_INTENT);
        if (bundle != null){
            products= MainActivity.productList;
            int productID= bundle.getInt(Constant.ID_PRODUCT, 1);
            int quantity= bundle.getInt(Constant.QUANTITY_PRODUCT, 1);
            String topping= bundle.getString(Constant.TOPPING_PRODUCT, "");
            String size= bundle.getString(Constant.SIZE_PRODUCT, "M");
            double itemPrice= bundle.getDouble(Constant.PRICE_PRODUCT, 10);
            Product product= products.get(productID);
            String thumb= product.getThumbnail();
            String name= product.getName();
            int stock= product.getAvailable();
            orderProducts = new ArrayList<>();
            orderProducts.add(new Cart(productID, name, quantity, stock, thumb, size, topping, itemPrice));
        }
        else{
            orderProducts = CartActivity.purchasingItems;
        }
        for (int i= 0; i< orderProducts.size(); i++){
            subTotal += orderProducts.get(i).getPrice();
        }
        updateBill();
    }

    private void configRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvPaymentProduct.setLayoutManager(manager);

        rcvPaymentProduct.setHasFixedSize(true);
        rcvPaymentProduct.setItemAnimator(new DefaultItemAnimator());
    }

    private void initData() {
        adapter = new OrderAdapter(getApplicationContext(), R.layout.item_payment, orderProducts);
        rcvPaymentProduct.setAdapter(adapter);
    }

    private void addEvents() {
        resultLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Constant.RESULT_INTENT){
                    Intent intent = result.getData();
                    if (intent != null){
                        voucher = (Voucher) intent.getSerializableExtra(Constant.VOUCHER_INTENT);
//                        Voucher delivery
                        if (voucher.isType()){
                            deliDiscount = voucher.getValue();
                            orderDiscount = 0;
                        }
                        else{
                            orderDiscount = voucher.getValue();
                            deliDiscount = 0;
                        }
                        txtOpenVoucher.setText(voucher.getName());
                        updateBill();
                    }
                }
                else if(result.getResultCode() == Constant.RESULT_INFORMATION){
                    Intent intent = result.getData();
                    if (intent != null){
                        String customer = intent.getStringExtra(Constant.INFORMATION_INTENT);
                        txtCustomer.setText(customer);
                    }
                }
            }
        });
        rdgDelivery.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (group.getCheckedRadioButtonId()){
                    case R.id.radDelivery:
                        isDelivery = true;
                        deliFee = 25000;
                        break;
                    case R.id.radPickUp:
                        isDelivery = false;
                        deliFee = 0;
                        break;
                }
                updateBill();
            }
        });
        btnOpenChooseTime.setOnClickListener(view -> clickOpenBottomSheetDialog());

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
                Intent intent = new Intent(OrderActivity.this, VoucherActivity.class);
                resultLauncher.launch(intent);
            }
        });

        //Open Payment success
        btnCompleteOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Save order to Database
                saveOrderDB();
            }
        });

        // Open Map
        btnOpenChooseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        // Open edit customer information
        edtCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, SettingMyProfileActivity.class);
                resultLauncher.launch(intent);
            }
        });

        // Go Back
        setSupportActionBar(toolbarPayment);
        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(null);
        toolbarPayment.setNavigationOnClickListener(view -> onBackPressed());

    }

    private void clickOpenBottomSheetDialog() {
        View viewDialog = getLayoutInflater().inflate(R.layout.bottom_sheet_choose_time, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewDialog);
        bottomSheetDialog.show();

        // Ngăn người dùng bấm ra ngoài dialog
        bottomSheetDialog.setCancelable(false);

        ImageButton imvCancel = viewDialog.findViewById(R.id.imvCancel);
        imvCancel.setOnClickListener(view -> bottomSheetDialog.dismiss());

        //Date, timePicker
        ImageButton imbDate, imbTime;
        TextView txtTime, txtDate;
        RadioButton radDeliNow = viewDialog.findViewById(R.id.rdbRightNow);
        txtTime = viewDialog.findViewById(R.id.txtTime);
        txtDate = viewDialog.findViewById(R.id.txtDate);
        imbDate= viewDialog.findViewById(R.id.btnDate);

        radDeliNow.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                bottomSheetDialog.dismiss();
            }
        });
        c = Calendar.getInstance();
        imbDate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int mYear = c.get(Calendar.YEAR); // current year
            int mMonth = c.get(Calendar.MONTH); // current month
            int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
            orderCode += mDay + mMonth;
            // date picker dialog
            dpd = new DatePickerDialog(OrderActivity.this, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {

                    txtDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                    updateBill();
                }
            }, mYear, mMonth, mDay);
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
                tpd = new TimePickerDialog(OrderActivity.this,  R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int mHour, int mMinute) {
                        txtTime.setText(mHour + ":" + mMinute);
                        time = mHour + ":" + mMinute;
                        updateBill();
                    }
                }, hour, minute, false);
                tpd.show();
            }
        });
    }

    private void updateBill(){
        if(isDelivery){
            txtDeliFee.setText(String.format("%.0f", deliFee));
            txtDeliDiscount.setText(String.format("%.0f", deliDiscount));
        }
        else{
            deliDiscount = 0;
            txtDeliFee.setText("0");
            txtDeliDiscount.setText("0");
        }
//      Calculate Total Invoice
        discount = deliDiscount + orderDiscount;
        total = subTotal + deliFee - discount;
        total = total <= 0 ? 0 : total;

        if(deliDiscount == 0){
            txtYamsCoupon.setText(String.format("%.0f", orderDiscount));
            txtDeliDiscount.setText("0");
        }
        else{
            txtDeliDiscount.setText(String.format("%.0f", deliDiscount));
            txtYamsCoupon.setText("0");
        }
        txtPaymentMethod.setText(paymentMethod);
        txtSubtotal.setText(String.format("%.0f", subTotal));
        txtTotalBill.setText(String.format("%.0f", total));
        if(date.equals("")){
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date d = new Date();
            txtDeliTime.setText(formatter.format(d));
        }
        else{
            txtDeliTime.setText(date + " | " + time);
        }

    }

    private void saveOrderDB(){
        Cursor cursor= orderDatabase.getData("SELECT * FROM "+ orderDatabase.TABLE_NAME);
        int count = 0;
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                count = cursor.getCount() + 1;
            }
        }
        cursor.close();
        orderCode += count;
        int isDeli= isDelivery ? 0: 1;
        Cart firstItem = orderProducts.get(0);
        boolean isSaved = orderDatabase.insertData(orderCode, firstItem.getProductName(), firstItem.getQuantity(), firstItem.getThumb(),
                firstItem.getProductSize(), isDeli, total);
        if (isSaved){
//            Notify user the status of order
            notifyStatus();
            // Go to Successful order page
            FragmentManager fm = getSupportFragmentManager();
            PaymentSuccessFragment fragment= new PaymentSuccessFragment();
            fm.beginTransaction().replace(R.id.layoutContainerPayment, fragment).commit();
        }
        else{
            Toast.makeText(OrderActivity.this, "Đặt hàng thất bại. Vui lòng thử lại", Toast.LENGTH_LONG).show();
        }
    }

    private void notifyStatus() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel= new NotificationChannel(CHANNEL_ID,"My channel", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("This is my channel");
            NotificationManager manager= getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        Notification notification= new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Đặt hàng thành công")
                .setContentText("Đơn hàng " + orderCode + " đang chờ xác nhận")
                .setColor(Color.MAGENTA)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager!=null){
            notificationManager.notify(1, notification);
        }
    }

    private void linkViews() {
        btnOpenChooseTime = findViewById(R.id.btnOpenChooseTime);
        btnCompleteOrder = findViewById(R.id.btnAddToPayment);
        btnOpenChooseAddress = findViewById(R.id.btnOpenChooseAddress);
        edtCustomer = findViewById(R.id.edtCustomer);

        rcvPaymentProduct = findViewById(R.id.rcvPaymentProduct);
        rdgDelivery= findViewById(R.id.rdgDeli);

        toolbarPayment = findViewById(R.id.toolbarPayment);
        txtOpenChoosePaymentMethod = findViewById(R.id.txtOpenChoosePaymentMethod);

        txtSubtotal= findViewById(R.id.txtSubTotal);
        txtOpenVoucher = findViewById(R.id.txtOpenVoucher);
        txtPaymentMethod = findViewById(R.id.txtPaymentMethod);
        txtDeliTime = findViewById(R.id.tvDeliTime);
        txtCusAddress = findViewById(R.id.tvCusAddress);
        txtCustomer = findViewById(R.id.tvCustomer);
        txtDeliFee= findViewById(R.id.txtPriceDelivery);
        txtDeliDiscount = findViewById(R.id.txtVoucherDelivery);
        txtYamsCoupon = findViewById(R.id.txtYamsVoucher);
        txtTotalBill = findViewById(R.id.txtTotalBill);
    }
}