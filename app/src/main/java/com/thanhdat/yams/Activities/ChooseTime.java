package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.thanhdat.yams.R;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChooseTime extends AppCompatActivity {
    ImageButton btnDate, btnTime;
    ImageButton btnBack;
    TextView txtTime, txtDate;


    Calendar c;

    DatePickerDialog dpd;
    TimePickerDialog tpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_time);

        btnDate = (ImageButton) findViewById(R.id.btnDate);
        btnTime = (ImageButton) findViewById(R.id.btnTime);
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtDate = (TextView) findViewById(R.id.txtDate);

        btnBack = (ImageButton) findViewById(R.id.btnBack);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(ChooseTime.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        txtDate.setText(mDay + "/" + (mMonth+1) + "/" + mYear);
                    }
                }, day, month, year);
                dpd.show();;
            }
        });

//        btnTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                c = Calendar.getInstance();
//                int minute = c.get(Calendar.MINUTE);
//                int hour = c.get(Calendar.HOUR_OF_DAY);
//
//                tpd = new TimePickerDialog(ChooseTime.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int mMinute, int mHour) {
//                        txtTime.setText(mMinute +" : " + mHour);
//                    }
//                }, minute, hour);
//                tpd.show();;
//
//            }
//        });

        //back payment
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseTime.this, Payment.class );
                startActivity(intent);
            }
        });

    }
}