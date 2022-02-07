package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.R;

import org.w3c.dom.Text;

public class AddressActivity extends AppCompatActivity {
    private AutoCompleteTextView edtProvince;
    private EditText edtDist, edtWard, edtDetailAdd;
    private AppCompatRadioButton rdbOffice, rdbHome;
    private SwitchCompat scMakeAsDefault;
    private TextView tvOpenMap;
    private Button btnConfirm, btnCancel;
    String province, district, ward, detail;
    boolean isDefault = false, isHome = false, isOffice = false;
    String ADDRESS_PREF = "my_address";
    StringBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        initViews();
        loadDataAddress();
        navigation();
        initProvinceList();
        saveAddress();
    }

    private void loadDataAddress() {
        SharedPreferences sharedPreferences = getSharedPreferences(ADDRESS_PREF, MODE_PRIVATE);
        String address = sharedPreferences.getString("address", "NaN");
        boolean defaultState = sharedPreferences.getBoolean("default", false);
        if(!address.equals("NaN")){
            String[] addressList = address.split(",");
            edtDetailAdd.setText(addressList[0]);
            edtWard.setText(addressList[1]);
            edtDist.setText(addressList[2]);
            edtProvince.setText(addressList[3]);
            if(defaultState)
                scMakeAsDefault.isChecked();
        }
    }

    private void saveAddress() {
        SharedPreferences sharedPreferences = getSharedPreferences(ADDRESS_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        builder = new StringBuilder();
            province = edtProvince.getText().toString();
            district = edtDist.getText().toString();
            ward = edtWard.getText().toString();
            detail = edtDetailAdd.getText().toString();

            builder.append(detail + ",");
            builder.append(ward + ",");
            builder.append(district + ",");
            builder.append(province);
            editor.putString("address", builder.toString());
            editor.putBoolean("default", isDefault);
            editor.apply();
    }

    private void initProvinceList() {
        String[] provinceList = new String[]{"TP. Hồ Chí Minh", "Bình Dương", "Đồng Nai", "Long An", "Tây Ninh", "Biên Hòa", "Bà Rịa - Vũng Tàu", "Đồng Tháp"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, provinceList);
        edtProvince.setAdapter(adapter);
    }

    private void navigation() {
        btnCancel.setOnClickListener(v -> onBackPressed());
        tvOpenMap.setOnClickListener(v -> startActivity(new Intent(this, MapActivity.class)));
        btnConfirm.setOnClickListener(v -> getDetailAddress());
        rdbOffice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(": ADDRESS", "onCheckedChanged: is office = " + isChecked);
                isOffice = isChecked;
            }
        });
        rdbHome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isHome = isChecked;
            }
        });
        scMakeAsDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    saveAddress();
                    isDefault = true;
                }
                else{
                    isDefault = false;
                }
            }
        });
    }

    private void getDetailAddress() {
        saveAddress();
        if(province.equals("")){
            edtProvince.setError("Vui lòng chọn tỉnh/thành");
            return;
        }
        if(district.equals("")){
            edtDist.setError("Vui lòng chọn quận/huyện");
            return;
        }
        if(ward.equals("")){
            edtWard.setError("Vui lòng chọn phường/xã");
            return;
        }
        if(detail.equals("")){
            edtDetailAdd.setError("Vui lòng chọn địa chỉ cụ thể");
            return;
        }
        Intent intent= new Intent(this, OrderActivity.class);
        intent.putExtra(Constant.ADDRESS_INTENT, builder.toString());
        setResult(Constant.RESULT_ADDRESS, intent);
        AddressActivity.super.onBackPressed();
    }

    private void initViews() {
        btnConfirm= findViewById(R.id.btnConfirmAdd);
        btnCancel = findViewById(R.id.btnCancelAdd);
        edtProvince = findViewById(R.id.edtProvince);
        edtDist = findViewById(R.id.edtDist);
        edtWard = findViewById(R.id.edtWard);
        edtDetailAdd = findViewById(R.id.edtDetailAdd);
        rdbHome = findViewById(R.id.btnHomeAdd);
        rdbOffice = findViewById(R.id.btnOfficeAdd);
        scMakeAsDefault = findViewById(R.id.switchDefaultAdd);
        tvOpenMap = findViewById(R.id.tvOpenMap);
    }
}