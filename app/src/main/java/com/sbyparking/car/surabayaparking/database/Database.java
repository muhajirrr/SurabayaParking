package com.sbyparking.car.surabayaparking.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sbyparking.car.surabayaparking.database.Model.TblParking;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DATABASE_SURABAYA_PARKING";
    private static final int DATABASE_VERSION = 3;

    //database_version 3 = menambahkan kolom latitude & longitude

    public static Database instance;

    public static void initInstance(Context context) {
        if (instance == null) {
            instance = new Database(context);
        }
    }

    private Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TblParking.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TblParking.TABLE_NAME);

        onCreate(db);
    }
}
