package com.thanhdat.yams.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.thanhdat.yams.Database.ReviewDatabase;
import com.thanhdat.yams.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class WriteReviewActivity extends AppCompatActivity {

    EditText edtReviewText;
    AppCompatButton btnUpReview, btnUploadImage, btnCamera, btnGallery;
    ImageView imvReviewImage;
    RatingBar rtbReviewRating, rtbDeliveryRating, rtbServiceRating;

    BottomSheetDialog sheetDialog;
    ActivityResultLauncher<Intent> activityResultLauncher;
    ReviewDatabase db;

    Toolbar toolbarWriteReview;

    boolean isCamera, isSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_review);

        linkViews();
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

        rtbReviewRating = findViewById(R.id.rtbReviewRating);
        rtbDeliveryRating = findViewById(R.id.rtbDeliveryRating);
        rtbServiceRating = findViewById(R.id.rtbServiceRating);

        toolbarWriteReview = findViewById(R.id.toolbarWriteReview);
    }

    private void createBottomSheet() {
        if(sheetDialog == null){
            View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_upload_image, null);
            btnCamera = view.findViewById(R.id.btnCamera);
            btnCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //open camera
                    isCamera = true;
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    activityResultLauncher.launch(intent);
                    sheetDialog.dismiss();
                    isSelected = true;
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


    private void addEvent() {
        //upload image
        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open bottom sheet dialog
                if(!isSelected)
                    sheetDialog.show();
                else
                    Toast.makeText(WriteReviewActivity.this, "Bạn chỉ được upload 1 ảnh!", Toast.LENGTH_SHORT).show();

            }
        });

        //up review
        btnUpReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //insert data
                String text;
                double rating, delivery, service;

                text = edtReviewText.getText().toString();
                rating = rtbReviewRating.getRating();
                delivery = rtbDeliveryRating.getRating();
                service = rtbServiceRating.getRating();

                if(!text.equals("")){
                    boolean flag = db.insertData(text, convertPhoto(), rating, delivery, service);
                    if(flag){
                        Toast.makeText(WriteReviewActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(WriteReviewActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(WriteReviewActivity.this, "Vui lòng nhập lời đánh giá của bạn!", Toast.LENGTH_SHORT).show();
                }

//                startActivity(new Intent(WriteReviewActivity.this, SeeReviewActivity.class));
            }
        });
    }
    private byte[] convertPhoto() {
        BitmapDrawable drawable = (BitmapDrawable) imvReviewImage.getDrawable();
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
        menu.setHeaderTitle("Chọn tác vụ");
        menu.add("Thay đổi ảnh");
        menu.add("Xóa ảnh");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getTitle() == "Thay đổi ảnh"){
            //change image
            sheetDialog.show();
        }
        if(item.getTitle() == "Xóa ảnh"){
            AlertDialog.Builder builder = new AlertDialog.Builder(WriteReviewActivity.this);
            builder.setIcon(R.drawable.ic_warning);
            builder.setTitle("Xác nhận");
            builder.setMessage("Bạn chắc chắn muốn xóa ảnh này?");

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
