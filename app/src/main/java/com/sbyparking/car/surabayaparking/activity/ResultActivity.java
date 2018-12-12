package com.sbyparking.car.surabayaparking.activity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.sbyparking.car.surabayaparking.R;
import com.sbyparking.car.surabayaparking.adapter.ResultAdapter;
import com.sbyparking.car.surabayaparking.common.Global;
import com.sbyparking.car.surabayaparking.database.Database;
import com.sbyparking.car.surabayaparking.database.Model.TblParking;
import com.sbyparking.car.surabayaparking.model.Parking;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ResultActivity extends AppCompatActivity {

    private TextView textView;
    private RecyclerView rcvResult;
    private ResultAdapter resultAdapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textView = findViewById(R.id.textView);
        rcvResult = findViewById(R.id.rcvResult);
//        rcvResult.setHasFixedSize(true);
        rcvResult.setLayoutManager(new LinearLayoutManager(this));
        rcvResult.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        String searchKey = getIntent().getStringExtra("SEARCH_KEY");
        setTitle("Search Result(s): " + searchKey);

        getParkingList(searchKey);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void getParkingList(String zona) {
        List<Parking> parkingList = new ArrayList<>();
        SQLiteDatabase db = Database.instance.getReadableDatabase();
        Log.e(Global.APP_TAG, ResultActivity.class.getName() + ":zona:" + zona);

        Cursor cursor;
        if (zona.isEmpty()) {
            cursor = db.query(TblParking.TABLE_NAME,
                    new String[]{},
                    null,
                    null, null, null, null, null);
        } else {
            cursor = db.query(TblParking.TABLE_NAME,
                    new String[]{},
                    TblParking.COLUMN_PENCARIAN_ZONA + " like ?",
                    new String[]{"%"+zona+"%"}, null, null, null, null);
        }


        while (cursor.moveToNext()) {
            Parking parking = new Parking(
                    cursor.getInt(cursor.getColumnIndex(TblParking.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(TblParking.COLUMN_PENCARIAN_ZONA)),
                    cursor.getString(cursor.getColumnIndex(TblParking.COLUMN_SARAN_ZONA)),
                    cursor.getString(cursor.getColumnIndex(TblParking.COLUMN_WAKTU_TEMPUH)),
                    cursor.getString(cursor.getColumnIndex(TblParking.COLUMN_HARGA_TIKET_MOBIL)),
                    cursor.getString(cursor.getColumnIndex(TblParking.COLUMN_HARGA_TIKET_MOTOR)),
                    cursor.getString(cursor.getColumnIndex(TblParking.COLUMN_JAM_OPERASIONAL)),
                    cursor.getInt(cursor.getColumnIndex(TblParking.COLUMN_FOTO)),
                    cursor.getString(cursor.getColumnIndex(TblParking.COLUMN_LAT)),
                    cursor.getString(cursor.getColumnIndex(TblParking.COLUMN_LNG))

            );

            parkingList.add(parking);
        }

        cursor.close();
        db.close();

        if (parkingList.size() == 0) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
        }

        resultAdapter = new ResultAdapter(parkingList, this);
        rcvResult.setAdapter(resultAdapter);
    }
}
