package com.thanhdat.yams.Activities;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;
import com.thanhdat.yams.Databases.ReviewDatabase;
import com.thanhdat.yams.Fragments.ProfileFragment;
import com.thanhdat.yams.Models.PreviousOrder;
import com.thanhdat.yams.Models.User;
import com.thanhdat.yams.R;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class WriteReviewActivity extends AppCompatActivity {

    EditText edtReviewText;
    AppCompatButton btnUpReview, btnUploadImage, btnCamera, btnGallery;
    ImageView imvReviewImage, imvReviewThumb, imvProfile;
    TextView txtReviewSize, txtReviewName;
    RatingBar rtbReviewRating;

    BottomSheetDialog sheetDialog;
    ActivityResultLauncher<Intent> activityResultLauncher;
    public static ReviewDatabase db;
    ArrayList<User> user = MainActivity.user;

    Toolbar toolbarWriteReview;
    boolean isCamera, cameraPermissionGranted, isSelected = false;
    private static final int PERMISSIONS_REQUEST_ACCESS_CAMERA = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        linkViews();
        receiveDataFromPrevious();
        createBottomSheet();
        addEvent();
        changeOrDeleteImage();
        backToPrevious();

        db = new ReviewDatabase(this);
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(isCamera){
                    Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                    imvReviewImage.setImageBitmap(bitmap);

                }else {
                    Uri uri = result.getData().getData();
                    if(uri != null){
                        try{
                            InputStream inputStream = getContentResolver().openInputStream(uri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            imvReviewImage.setImageBitmap(bitmap);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
    }

    private void linkViews() {
        edtReviewText = findViewById(R.id.edtReviewText);

        btnUpReview = findViewById(R.id.btnUpReview);
        btnUploadImage = findViewById(R.id.btnUploadImage);
        btnCamera = findViewById(R.id.btnCamera);
        btnGallery = findViewById(R.id.btnGallery);

        imvReviewImage = findViewById(R.id.imvReviewImage);
        imvReviewThumb = findViewById(R.id.imvReviewThumb);
        imvProfile = findViewById(R.id.imvProfileComment);

        txtReviewSize = findViewById(R.id.txtReviewSize);
        txtReviewName = findViewById(R.id.txtReviewName);

        rtbReviewRating = findViewById(R.id.rtbReviewRating);

        toolbarWriteReview = findViewById(R.id.toolbarWriteReview);

    }

    private void receiveDataFromPrevious() {
        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        PreviousOrder previousOrder = (PreviousOrder) bundle.get("object_previous");
        txtReviewName.setText(previousOrder.getPreviousName());
        txtReviewSize.setText(previousOrder.getPreviousContent());
        imvReviewThumb.setImageResource(previousOrder.getPreviousThumb());
        Picasso.get().load(user.get(0).getPhoto()).into(imvProfile);
    }


    private void createBottomSheet() {
        if(sheetDialog == null){
            View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_upload_image, null);
            btnCamera = view.findViewById(R.id.btnCamera);
            btnCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //open camera
                    getCameraPermission();
                    isCamera = true;
                    if(cameraPermissionGranted){
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        activityResultLauncher.launch(intent);
                        sheetDialog.dismiss();
                        isSelected = true;
                    }

                }
            });

            btnGallery = view.findViewById(R.id.btnGallery);
            btnGallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //open gallery
                    isCamera = false;
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    activityResultLauncher.launch(intent);
                    sheetDialog.dismiss();
                    isSelected = true;
                }
            });

            sheetDialog = new BottomSheetDialog(this);
            sheetDialog.setContentView(view);
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


    private void addEvent() {
        //upload image
        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open bottom sheet dialog
                if(!isSelected)
                    sheetDialog.show();
                else
                    Toast.makeText(WriteReviewActivity.this, "B???n ch??? ???????c upload 1 ???nh!", Toast.LENGTH_SHORT).show();

            }
        });

        //up review
        btnUpReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //insert data

                double rating;
                BitmapDrawable profile, image;
                String name, content, size;

                rating = rtbReviewRating.getRating();
                profile = (BitmapDrawable) imvProfile.getDrawable();
                image = (BitmapDrawable) imvReviewImage.getDrawable();
                name = user.get(0).getName();
                content = edtReviewText.getText().toString();
                size = txtReviewSize.getText().toString();

                if(!content.equals("") && imvReviewImage.getDrawable() != null){
                    boolean flag = db.insertData(rating, convertPhoto(profile), name, content, convertPhoto(image), size);
                    if(flag){
                        //Toast.makeText(WriteReviewActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(WriteReviewActivity.this, SeeReviewActivity.class));

                    }else {

                        Toast.makeText(WriteReviewActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(WriteReviewActivity.this, "Vui l??ng nh???p l???i ????nh gi?? v?? ????ng t???i h??nh ???nh!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public byte[] convertPhoto(BitmapDrawable drawable) {
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();
    }

    private void changeOrDeleteImage() {
        imvReviewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSelected){
                    registerForContextMenu(view);
                    openContextMenu(view);
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Ch???n t??c v???");
        menu.add("Thay ?????i ???nh");
        menu.add("X??a ???nh");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getTitle() == "Thay ?????i ???nh"){
            //change image
            sheetDialog.show();
        }
        if(item.getTitle() == "X??a ???nh"){
            AlertDialog.Builder builder = new AlertDialog.Builder(WriteReviewActivity.this);
            builder.setIcon(R.drawable.ic_warning);
            builder.setTitle("X??c nh???n");
            builder.setMessage("B???n ch???c ch???n mu???n x??a ???nh n??y?");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //delete image
                    imvReviewImage.setImageResource(0);
                    isSelected = false;
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //cancel delete
                    dialogInterface.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onContextItemSelected(item);
    }

    private void backToPrevious() {
        setSupportActionBar(toolbarWriteReview);
        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle(null);
        toolbarWriteReview.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

}
