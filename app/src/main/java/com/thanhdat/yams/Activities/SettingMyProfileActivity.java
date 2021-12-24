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

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;
import com.thanhdat.yams.Constants.Constant;
import com.thanhdat.yams.Fragments.OtpFragment;
import com.thanhdat.yams.Models.User;
import com.thanhdat.yams.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class SettingMyProfileActivity extends AppCompatActivity {
    EditText edtName, edtPhone, edtEmail;
    AppCompatButton btnConfirm;
    Toolbar toolbarMyProfile;
    ImageView imvAva, imvUploadPhoto;
    Bitmap photo;
    BottomSheetDialog dialog;
    AppCompatButton openSheetCamera, openSheetGallery;
    ActivityResultLauncher<Intent> activityResultLauncher;
    boolean isCamera,cameraPermissionGranted;
    private static final int PERMISSIONS_REQUEST_ACCESS_CAMERA = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_my_profile);

        linkView();
        addEvent();
    }

    private void  addEvent() {
        activityResultLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    if (isCamera) {
                        photo = (Bitmap) result.getData().getExtras().get("data");
                        imvAva.setImageBitmap(photo);
                    } else {
                        Uri uri = result.getData().getData();
                        if (uri != null) {
                            try {
                                InputStream inputStream = getContentResolver().openInputStream(uri);
                                photo = BitmapFactory.decodeStream(inputStream);
                                imvAva.setImageBitmap(photo);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        ArrayList<User> user = MainActivity.user;
        if(!user.get(0).getPhoto().equals("")){
            Picasso.get().load(user.get(0).getPhoto()).into(imvAva);
        }
        if(!user.get(0).getName().equals(""))
            edtName.setText(user.get(0).getName());
        edtPhone.setText(user.get(0).getPhone());
        if(!user.get(0).getEmail().equals("")){
            edtEmail.setText(user.get(0).getEmail());
        }
        setSupportActionBar(toolbarMyProfile);
        getSupportActionBar().setTitle(null);
        toolbarMyProfile.setNavigationOnClickListener(view -> onBackPressed());
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= edtName.getText().toString();
                String phone= edtPhone.getText().toString();
                String information = name + " | " + phone;
                if(!(name.equals("") && phone.equals(""))){
                    Intent intent= new Intent(SettingMyProfileActivity.this, OrderActivity.class);
                    intent.putExtra(Constant.INFORMATION_INTENT, information);
                    setResult(Constant.RESULT_INFORMATION, intent);
                    SettingMyProfileActivity.super.onBackPressed();
                }
            }
        });
        imvUploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSheetDialog();
                dialog.show();
            }
        });
    }

    private void createSheetDialog(){
        if(dialog == null){
            View view= LayoutInflater.from(SettingMyProfileActivity.this).inflate(R.layout.bottom_sheet_upload_image, null);
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
            dialog= new BottomSheetDialog(SettingMyProfileActivity.this);
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

    private void linkView() {
        toolbarMyProfile = findViewById(R.id.toolbarMyProfile);
        btnConfirm= findViewById(R.id.buttonConFirmEditAcc);
        edtName = findViewById(R.id.edtAccountName);
        edtPhone= findViewById(R.id.edtAccountPhone);
        edtEmail= findViewById(R.id.editEmailAccount);
        imvAva = findViewById(R.id.imvAvatarMyAcc);
        imvUploadPhoto = findViewById(R.id.imvUploadPhoto);
    }
}