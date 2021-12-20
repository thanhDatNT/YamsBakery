package com.thanhdat.yams.Databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class CartDatabase extends SQLiteOpenHelper {
    public static final int DB_VERSION= 1;
    public static final String DB_NAME= "cart.sqlite";
    public static final String TABLE_NAME= "cart";
    public static final String COL_ID= "p_id";
    public static final String COL_PRO_ID= "p_proID";
    public static final String COL_NAME= "p_name";
    public static final String COL_QUANTITY= "p_quantity";
    public static final String COL_AVAILABLE= "p_available";
    public static final String COL_THUMB= "p_thumb";
    public static final String COL_SIZE= "p_size";
    public static final String COL_TOPPING= "p_topping";
    public static final String COL_PRICE= "p_price";

    public CartDatabase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql= "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_PRO_ID +" INTEGER, "+ COL_NAME +" VARCHAR(200), " +COL_QUANTITY +" INTEGER, " + COL_AVAILABLE +" INTEGER, "+ COL_THUMB +" VARCHAR(500), "+ COL_SIZE +" VARCHAR(100), "+ COL_TOPPING +" VARCHAR(200), "+ COL_PRICE +" REAL)";
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

    public boolean insertData(int proID, String name, String thumb, String size, String topping, int quantity, int stock, double price){
        try {
            SQLiteDatabase database = getWritableDatabase();
            String sql = "INSERT INTO " + TABLE_NAME + " Values(null, ?, ?, ?, ?, ?, ?, ?, ?)";
            SQLiteStatement statement = database.compileStatement(sql);
            statement.bindLong(1, proID);
            statement.bindString(2, name);
            statement.bindLong(3, quantity);
            statement.bindLong(4, stock);
            statement.bindString(5, thumb);
            statement.bindString(6, size);
            statement.bindString(7, topping);
            statement.bindDouble(8, price);
            statement.executeInsert();
            statement.clearBindings();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
