package com.sbyparking.car.surabayaparking.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sbyparking.car.surabayaparking.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomeActivity extends AppCompatActivity {

//    private Button btnOnline;
    private Button btnContact;
    private Button btnOffline;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Surabaya Parking");

//        btnOnline = findViewById(R.id.btnOnline);
//        btnOnline.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                openOnActivity();
//            }
//        });

        btnContact = findViewById(R.id.btnContact);
        btnContact.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openConActivity();
            }
        });
        btnOffline = findViewById(R.id.btnOffline);
        btnOffline.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openOffActivity();
            }
        });
    }

    public void openOnActivity(){
        Intent onIntent = new Intent(HomeActivity.this, OnlineActivity.class);
        startActivity(onIntent);
    }

    public void openOffActivity(){
        Intent offIntent = new Intent(HomeActivity.this, OfflineActivity.class);
        startActivity(offIntent);
    }

    public void openConActivity(){
        Intent contactIntent = new Intent(HomeActivity.this, ContactActivity.class);
        startActivity(contactIntent);
    }

    //tampilan utama
}
