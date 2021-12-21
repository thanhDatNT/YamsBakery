package com.thanhdat.yams.Databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class UserDatabase extends SQLiteOpenHelper {
    public static final int DB_VERSION= 1;
    public static final String DB_NAME= "user.sqlite";
    public static final String TABLE_NAME= "user";
    public static final String COL_ID= "u_id";
    public static final String COL_NAME= "u_name";
    public static final String COL_PHONE= "u_phone";
    public static final String COL_EMAIL= "u_email";
    public static final String COL_PHOTO= "u_ava";

    public UserDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql= "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME +" VARCHAR(200), " + COL_PHONE +" VARCHAR(200), "+ COL_EMAIL +" VARCHAR(200), "+ COL_PHOTO +" VARCHAR(600))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }

    public void execSQL(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor getData (String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public boolean insertData(String name, String phone, String mail, String photo){
        try {
            SQLiteDatabase database = getWritableDatabase();
            String sql = "INSERT INTO " + TABLE_NAME + " Values(null, ?, ?, ?, ?)";
            SQLiteStatement statement = database.compileStatement(sql);
            statement.bindString(1, name);
            statement.bindString(2, phone);
            statement.bindString(3, mail);
            statement.bindString(4, photo);
            statement.executeInsert();
            statement.clearBindings();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
