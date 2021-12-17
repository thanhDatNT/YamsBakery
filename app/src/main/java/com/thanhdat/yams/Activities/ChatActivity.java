package com.thanhdat.yams.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.thanhdat.yams.Models.ChatView;
import com.thanhdat.yams.R;
import com.thanhdat.yams.Adapters.MessageListAdapter;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView rcvMessage;
    private ImageView imvGetPhoto, imvSend;
    private EditText edtMessage;
    private LinearLayout layoutContainer;
    private Toolbar toolbar;
    String message;
    Bitmap photo = null;
    ArrayList<ChatView> messageList;
    MessageListAdapter adapter;
    View view;
    String currentDate, currentTime;
    String time;
    BottomSheetDialog dialog;
    AppCompatButton openSheetCamera, openSheetGallery;
    ActivityResultLauncher<Intent> activityResultLauncher;
    boolean isCamera,cameraPermissionGranted;
    private static final int PERMISSIONS_REQUEST_ACCESS_CAMERA = 11;

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
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
            overridePendingTransition(R.anim.translate_slide_enter, R.anim.translate_slide_exit);
        });
        messageList= new ArrayList<>();
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
                createSheetDialog();
                dialog.show();
            }
        });
        activityResultLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    if (isCamera) {
                        photo = (Bitmap) result.getData().getExtras().get("data");
                        insertData();
                    } else {
                        Uri uri = result.getData().getData();
                        if (uri != null) {
                            try {
                                InputStream inputStream = getContentResolver().openInputStream(uri);
                                photo = BitmapFactory.decodeStream(inputStream);
                                insertData();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    private void insertData() {
        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcvMessage.setLayoutManager(layoutManager);
        if(messageList.size() > 0){
//            Add view message to LinearLayout
            view= View.inflate(ChatActivity.this, R.layout.viewholder_yamschat, null);
            TextView myText= view.findViewById(R.id.text_chat_message_yams);
            TextView myTime= view.findViewById(R.id.text_chat_date_yams);
            myTime.setText(time);
            myText.setText(messageList.get(0).getMessage());
            layoutContainer.addView(view);
            messageList.remove(0);
        }
//        Add Message to RecyclerView
        if(photo != null){
            messageList.add(new ChatView(photo, null));
        }
        else {
            messageList.add(new ChatView(null, message));
        }
        adapter= new MessageListAdapter(this, messageList);
        adapter.setSender(0);
        rcvMessage.setAdapter(adapter);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//              Set view for layout container: My Message
                view= View.inflate(ChatActivity.this, R.layout.viewholder_mychat, null);
                TextView myText= view.findViewById(R.id.text_chat_message_me);
                TextView myTime= view.findViewById(R.id.text_chat_date_me);
                ImageView myPhoto= view.findViewById(R.id.imvPhotoMessage);
                if(photo !=null){
                    myPhoto.setImageBitmap(photo);
                    myText.setVisibility(View.GONE);
                    photo= null;
                }
                else{
                    myText.setText(message);
                    myPhoto.setVisibility(View.GONE);
                }
                myTime.setText(time);
                layoutContainer.addView(view);
                //                Delay 3 seconds, then, the system send a message to user
                messageList.remove(0);
                messageList.add(new ChatView(null, "Bạn chờ Yams một chút nha \nChúng tôi sẽ liên hệ lại sớm nhất"));
                adapter.notifyDataSetChanged();
                adapter.setSender(1);
            }
        }, 3000);
    }

    private void createSheetDialog() {
        if(dialog == null){
            View view= LayoutInflater.from(ChatActivity.this).inflate(R.layout.bottom_sheet_upload_image, null);
            openSheetCamera= view.findViewById(R.id.btnCamera);
            openSheetGallery= view.findViewById(R.id.btnGallery);
            openSheetCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Check permission and Open camera
                    getCameraPermission();
                    isCamera= true;
                    if(cameraPermissionGranted){
                        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        activityResultLauncher.launch(intent);
                        dialog.dismiss();
                    }
                }
            });
            openSheetGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Open Gallery
                    isCamera= false;
                    Intent intent= new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    activityResultLauncher.launch(intent);
                    dialog.dismiss();
                }
            });
            dialog= new BottomSheetDialog(ChatActivity.this);
            dialog.setContentView(view);
        }
    }

    private void getCameraPermission() {

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            cameraPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    PERMISSIONS_REQUEST_ACCESS_CAMERA);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraPermissionGranted = false;
        if (requestCode == PERMISSIONS_REQUEST_ACCESS_CAMERA) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                cameraPermissionGranted = true;
            }
        }
    }

    private void initViews() {
        rcvMessage= findViewById(R.id.rcvChat);
        edtMessage= findViewById(R.id.edtMessage);
        imvGetPhoto= findViewById(R.id.imvPhoto);
        imvSend= findViewById(R.id.imvSend);
        layoutContainer= findViewById(R.id.layoutChat);
        toolbar= findViewById(R.id.toolbarChat);
    }
}