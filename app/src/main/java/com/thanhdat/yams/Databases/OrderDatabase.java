package com.thanhdat.yams.Databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class OrderDatabase extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "order.sqlite";
    public static final String TABLE_NAME = "order_table";
    public static final String COL_ID = "o_id";
    public static final String COL_CODE = "o_code";
    public static final String COL_NAME = "o_name";
    public static final String COL_THUMB = "o_thumb";
    public static final String COL_SIZE = "p_size";
    public static final String COL_QUANTITY = "o_quantity";
    public static final String COL_DELIVERY = "o_delivery";
    public static final String COL_TOTAL = "p_price";

    public OrderDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql= "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +" (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COL_CODE +" VARCHAR(100), "+ COL_NAME +" VARCHAR(200), " +COL_QUANTITY +" INTEGER, "+ COL_THUMB +" VARCHAR(500), "+ COL_SIZE +" VARCHAR(100), "+ COL_DELIVERY +" INTEGER, "+ COL_TOTAL +" REAL)";
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

    public boolean insertData(String code, String name, int quantity, String thumb, String size, int deli, double total){
        try {
            SQLiteDatabase database = getWritableDatabase();
            String sql = "INSERT INTO " + TABLE_NAME + " Values(null, ?, ?, ?, ?, ?, ?, ?)";
            SQLiteStatement statement = database.compileStatement(sql);
            statement.bindString(1, code);
            statement.bindString(2, name);
            statement.bindLong(3, quantity);
            statement.bindString(4, thumb);
            statement.bindString(5, size);
            statement.bindLong(6, deli);
            statement.bindDouble(7, total);
            statement.executeInsert();
            statement.clearBindings();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
