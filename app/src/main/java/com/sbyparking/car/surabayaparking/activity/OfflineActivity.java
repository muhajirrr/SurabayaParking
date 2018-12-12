package com.sbyparking.car.surabayaparking.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sbyparking.car.surabayaparking.R;
import com.sbyparking.car.surabayaparking.common.Global;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class OfflineActivity extends AppCompatActivity {

    private EditText editSearch;
    private Button btnSearch;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Find Location");

        editSearch = findViewById(R.id.editSearch);
        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(OfflineActivity.this, ResultActivity.class);
                nextIntent.putExtra("SEARCH_KEY", editSearch.getText().toString());
                Log.e(Global.APP_TAG, OfflineActivity.class.getName() + ":SEARCH_KEY:" + editSearch.getText().toString());
                startActivity(nextIntent);
            }
        });
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
}
