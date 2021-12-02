package com.thanhdat.yams.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class ReviewDatabase extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "reviews.sqlite";
    public static final String TBL_NAME = "Review";

    public static final String COL_R_ID = "R_ID";
    public static final String COL_R_TEXT = "R_Text";
    public static final String COL_R_PHOTO = "R_Photo";
    public static final String COL_R_RATING = "R_Rating";
    public static final String COL_R_DELIVERY = "R_Delivery";
    public static final String COL_R_SERVICE = "R_Service";

    public ReviewDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TBL_NAME + "(" + COL_R_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_R_TEXT + " VARCHAR(1000), " + COL_R_PHOTO + " BLOB, " + COL_R_RATING + " REAL, " + COL_R_DELIVERY + " REAL, " + COL_R_SERVICE + " REAL)";
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
        //thêm try catch nếu kiểm tra
    }
    public Cursor getData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);
    }
    public boolean insertData(String text, byte[] photo, double rating, double delivery, double service){
        try{
            SQLiteDatabase db = getWritableDatabase();
            String sql = "INSERT INTO " + TBL_NAME + " VALUES(null, ?, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, text);
            statement.bindBlob(2, photo);
            statement.bindDouble(3, rating);
            statement.bindDouble(4, delivery);
            statement.bindDouble(5, service);

            statement.executeInsert();

            return true;
        }catch (Exception ex){
            return false;

        }

    }
}
