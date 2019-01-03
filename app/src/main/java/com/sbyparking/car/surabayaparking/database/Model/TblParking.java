package com.sbyparking.car.surabayaparking.database.Model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.sbyparking.car.surabayaparking.database.Database;

public class TblParking {

    public static final String TABLE_NAME = "Parking";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PENCARIAN_ZONA = "pencarian_zona";
    public static final String COLUMN_SARAN_ZONA = "saran_zona";
    public static final String COLUMN_WAKTU_TEMPUH = "waktu_tempuh";
    public static final String COLUMN_HARGA_TIKET_MOBIL = "harga_tiket_mobil";
    public static final String COLUMN_HARGA_TIKET_MOTOR = "harga_tiket_motor";
    public static final String COLUMN_JAM_OPERASIONAL = "jam_operasional";
    public static final String COLUMN_FOTO = "foto";
    public static final String COLUMN_LAT = "lat";
    public static final String COLUMN_LNG = "lng";

    private int id;
    private String pencarianZona;
    private String saranZona;
    private String waktuTempuh;
    private String hargaTiketMobil;
    private String hargaTiketMotor;
    private String jamOperasional;
    private int foto;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PENCARIAN_ZONA + " TEXT,"
                    + COLUMN_SARAN_ZONA + " TEXT,"
                    + COLUMN_WAKTU_TEMPUH + " TEXT,"
                    + COLUMN_HARGA_TIKET_MOBIL + " TEXT,"
                    + COLUMN_HARGA_TIKET_MOTOR + " TEXT,"
                    + COLUMN_JAM_OPERASIONAL + " TEXT,"
                    + COLUMN_FOTO + " INTEGER,"
                    + COLUMN_LAT + " TEXT,"
                    + COLUMN_LNG + " TEXT"
                    + ")";


    public TblParking() {
    }

    public TblParking(int id, String pencarianZona, String saranZona, String waktuTempuh
            , String hargaTiketMobil, String hargaTiketMotor, String jamOperasional, int foto) {
        this.id = id;
        this.pencarianZona = pencarianZona;
        this.saranZona = saranZona;
        this.waktuTempuh = waktuTempuh;
        this.hargaTiketMobil = hargaTiketMobil;
        this.hargaTiketMotor = hargaTiketMotor;
        this.jamOperasional = jamOperasional;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPencarianZona() {
        return pencarianZona;
    }

    public void setPencarianZona(String pencarianZona) {
        this.pencarianZona = pencarianZona;
    }

    public String getSaranZona() {
        return saranZona;
    }

    public void setSaranZona(String saranZona) {
        this.saranZona = saranZona;
    }

    public String getWaktuTempuh() {
        return waktuTempuh;
    }

    public void setWaktuTempuh(String waktuTempuh) {
        this.waktuTempuh = waktuTempuh;
    }

    public String getHargaTiketMobil() {
        return hargaTiketMobil;
    }

    public void setHargaTiketMobil(String hargaTiketMobil) {
        this.hargaTiketMobil = hargaTiketMobil;
    }

    public String getHargaTiketMotor() {
        return hargaTiketMotor;
    }

    public void setHargaTiketMotor(String hargaTiketMotor) {
        this.hargaTiketMotor = hargaTiketMotor;
    }

    public String getJamOperasional() {
        return jamOperasional;
    }

    public void setJamOperasional(String jamOperasional) {
        this.jamOperasional = jamOperasional;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public static void insertParking(int id, String pencarianZona, String saranZona, String waktuTempuh
            , String hargaTiketMobil, String hargaTiketMotor, String jamOperasional, int foto
            , String lat, String lng
    ) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id);
        values.put(COLUMN_PENCARIAN_ZONA, pencarianZona);
        values.put(COLUMN_SARAN_ZONA, saranZona);
        values.put(COLUMN_WAKTU_TEMPUH, waktuTempuh);
        values.put(COLUMN_HARGA_TIKET_MOBIL, hargaTiketMobil);
        values.put(COLUMN_HARGA_TIKET_MOTOR, hargaTiketMotor);
        values.put(COLUMN_JAM_OPERASIONAL, jamOperasional);
        values.put(COLUMN_FOTO, foto);
        values.put(COLUMN_LAT, lat);
        values.put(COLUMN_LNG, lng);

        SQLiteDatabase db = Database.instance.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public static void clearTable() {
        SQLiteDatabase db = Database.instance.getWritableDatabase();
        db.execSQL("delete from "+TABLE_NAME+";");
        db.close();
    }

}
