package com.thanhdat.yams.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapter.MessageListAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView rcvMessage;
    private ImageView imvBack, imvGetPhoto, imvSend;
    private EditText edtMessage;
    private LinearLayout layoutContainer;
    String message;
    ArrayList<String> messageList;
    MessageListAdapter adapter;
    View view;
    String currentDate, currentTime;
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initViews();
        addEvents();
        currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        time= currentTime + ", " + currentDate;
    }

    private void addEvents() {
        messageList= new ArrayList<>();
        imvBack.setOnClickListener(v -> finish());
        imvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Send message
                if(!edtMessage.getText().toString().equals("")){
                    message= edtMessage.getText().toString().trim();
                    insertData();
                    edtMessage.setText("");
                    edtMessage.clearFocus();
                }
                else return;
            }
        });
        imvGetPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                User get photo from device
            }
        });
    }

    private void insertData() {
        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvMessage.setLayoutManager(layoutManager);
        if(messageList.size() > 0){
            view= View.inflate(ChatActivity.this, R.layout.viewholder_yamschat, null);
            TextView myText= view.findViewById(R.id.text_chat_message_yams);
            TextView myTime= view.findViewById(R.id.text_chat_date_yams);
            myTime.setText(time);
            myText.setText(messageList.get(0));
            layoutContainer.addView(view);
            messageList.remove(0);
        }
        messageList.add(message);
        adapter= new MessageListAdapter(this, messageList);
        adapter.setSender(0);
        rcvMessage.setAdapter(adapter);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view= View.inflate(ChatActivity.this, R.layout.viewholder_mychat, null);
                TextView myText= view.findViewById(R.id.text_chat_message_me);
                TextView myTime= view.findViewById(R.id.text_chat_date_me);
                myTime.setText(time);
                myText.setText(message);
                layoutContainer.addView(view);
                messageList.remove(0);
                messageList.add("Bạn chờ Yams một chút nha \nChúng tôi sẽ liên hệ lại sớm nhất");
                adapter.notifyDataSetChanged();
                adapter.setSender(1);
            }
        }, 3000);
    }

    private void initViews() {
        rcvMessage= findViewById(R.id.rcvChat);
        edtMessage= findViewById(R.id.edtMessage);
        imvGetPhoto= findViewById(R.id.imvPhoto);
        imvSend= findViewById(R.id.imvSend);
        imvBack= findViewById(R.id.imvBackChat);
        layoutContainer= findViewById(R.id.layoutChat);
    }
}