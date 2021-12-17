package com.thanhdat.yams.Databases;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.thanhdat.yams.Activities.SeeReviewActivity;
import com.thanhdat.yams.R;

public class ReviewDatabase extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "reviews.sqlite";
    public static final String TBL_NAME = "Review";

    public static final String COL_R_ID = "R_ID";
    public static final String COL_R_RATING = "R_Rating";
    public static final String COL_R_PROFILE = "R_Profile";
    public static final String COL_R_NAME = "R_Name";
    public static final String COL_R_CONTENT = "R_Content";
    public static final String COL_R_IMAGE = "R_Image";
    public static final String COL_R_SIZE = "R_Size";

    public ReviewDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + "(" + COL_R_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_R_RATING + " REAL, " + COL_R_PROFILE + " BLOB, " + COL_R_NAME +" VARCHAR(100), " + COL_R_CONTENT + " VARCHAR(1000), " + COL_R_IMAGE + " BLOB, " + COL_R_SIZE + " VARCHAR(100))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TBL_NAME);
        onCreate(db);
    }
    public void queryExec(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
    public Cursor getData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);
    }
    public boolean insertData(double rating, byte[] profile,  String name, String content, byte[] image, String size){
        try{
            SQLiteDatabase db = getWritableDatabase();
            String sql = "INSERT INTO " + TBL_NAME + " VALUES(null, ?, ?, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindDouble(1, rating);
            statement.bindBlob(2, profile);
            statement.bindString(3, name);
            statement.bindString(4, content);
            statement.bindBlob(5, image);
            statement.bindString(6,size);

            statement.executeInsert();

            return true;
        }catch (Exception ex){
            return false;

        }
    }

    public int getCount(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TBL_NAME, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

//    public void createSomeDefaultReviews(){
//        int count = getCount();
//        if(count == 0){
//
//            queryExec("INSERT INTO " + TBL_NAME + " VALUES(null, 4.5, " + R.drawable.img_logo_launcher + ", 'Minh Xuân', 'Bánh ngon lắm, mọi người nên mua ăn thử nha!', " + R.drawable.img_cake +   ", 'Size M')");
//            queryExec("INSERT INTO " + TBL_NAME + " VALUES(null, 5, " + R.drawable.img_logo_pink + ", 'Uyển Nhi', 'Bánh khá là ngon, thơm nữa, 5 sao nha', " + R.drawable.img_bdcake +   ", 'Size L')");
//            queryExec("INSERT INTO " + TBL_NAME + " VALUES(null, 4.5, " + R.drawable.img_vietcombank + ", 'Mai Trang', 'Ngon, bổ, rẻ', " + R.drawable.img_mango_cake +   ", 'Size M')");
//            queryExec("INSERT INTO " + TBL_NAME + " VALUES(null, 4.5, " + R.drawable.img_logo_launcher + ", 'Thành Đạt', 'Bánh ngon lắm, mọi người nên mua ăn thử nha!', " + R.drawable.img_cake +   ", 'Size M')");
//        }
//    }
}
