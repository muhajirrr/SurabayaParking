package com.sbyparking.car.surabayaparking.activity;

 import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sbyparking.car.surabayaparking.R;
import com.sbyparking.car.surabayaparking.database.Database;
import com.sbyparking.car.surabayaparking.database.Model.TblParking;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);

        insertParking();
    }

    private void insertParking() {
        TblParking.clearTable();
        TblParking.insertParking("Gubeng", "Jalan Dharmawangsa", "600 - 1000m"
                , "5000", "2000", "09.00 - 20.00"
                , R.drawable.dharmawangsa, "-7.273640", "112.756229"
        );
        TblParking.insertParking("RSUD Dr.Soetomo", "Jalan Dharmawangsa"
                , "400 - 600m", "5000", "2000", "09.00 - 20.00"
                , R.drawable.dharmawangsa, "-7.267968", "112.756932"

        );
        TblParking.insertParking("Institut Pembangunan Surabaya","Jalan Urip Sumoharjo"
                , "100 meer", "5000", "2000", "09.00 - 16.00"
                , R.drawable.usumoharjo
                , "-7.2752085", "112.7395801"
        );
        TblParking.insertParking("Pasar Blauran", "Jalan Blauran", "100 meter"
                , "5000", "2000", "09.00 - 20.00", R.drawable.usumoharjo
                , "-7.2572117", "112.7315495"
        );
        TblParking.insertParking("Pasar Blauran", "Jalan Kranggan", "100 meter"
                , "5000", "2000", "09.00 - 20.00", R.drawable.usumoharjo
                , "-7.256004", "112.7307727"
        );
        TblParking.insertParking("Pasar Pucang", "Jalan Pucang Anom", "100 meter"
                , "5000", "2000", "09.00 - 20.00", R.drawable.usumoharjo
                , "-7.284350", " 112.753886"
        );
        TblParking.insertParking("Bank BTPN", "Jalan Kertajaya", "100 meter"
                , "5000", "2000", "09.00 - 20.00", R.drawable.kertajaya
                , "-7.2783007", "112.7541894"
        );
        TblParking.insertParking("Gramedia Manyar Kertoarjo", "Jalan Manyar Kertoarjo"
                , "100 meter", "5000", "2000", "09.00 - 20.00"
                , R.drawable.manyarkertoarjo , "-7.280154", "112.768332"
        );
        TblParking.insertParking("Pasar Keputran", "Jalan Keputran"
                , "100 meter", "5000", "2000", "09.00 - 20.00"
                , R.drawable.usumoharjo, "-7.2758818", "112.7410303"
        );
        TblParking.insertParking("Grapari Pemuda", "Jalan Kayoon"
                , "100 meter", "5000", "2000", "09.00 - 20.00"
                , R.drawable.kayoon, "-7.2699085", "112.7445783"
        );
        TblParking.insertParking("Graha Indosat", "Jalan Kayoon"
                , "100 meter", "5000", "2000", "09.00 - 20.00"
                , R.drawable.kayoon, "-7.2699085", "112.7445783"
        );
        TblParking.insertParking("Balai Pemuda", "Jalan Walikota Mustajab"
                , "100 meter", "5000", "2000", "09.00 - 20.00"
                , R.drawable.walikotamustajab, "-7.260445", "112.745788"
        );
        TblParking.insertParking("Balai Kota", "Jalan Jimerto"
                , "100 meter", "5000", "2000", "09.00 - 20.00"
                , R.drawable.jimerto, "-7.2581574", "112.7460108"
        );
        TblParking.insertParking("Balai Kota", "Jalan Walikota Mustajab"
                , "100 meter", "5000", "2000", "09.00 - 20.00"
                , R.drawable.walikotamustajab, "-7.260445", "112.745788"
        );
        TblParking.insertParking("Balai Kota", "Jalan Sedap Malam"
                , "100 meter", "5000", "2000", "09.00 - 20.00"
                , R.drawable.sedapmalam, "-7.2594334", "112.7455712"
        );
        TblParking.insertParking("Monumen Kapal Selam (Monkasel)", "Jalan Kayoon"
                , "100 meter", "5000", "2000", "09.00 - 20.00"
                , R.drawable.kayoon, "-7.2699085", "112.7445783"
        );
        TblParking.insertParking("House of Sampoerna", "Jalan Rajawali"
                , "700 meter", "5000", "2000", "09.00 - 21.00"
                , R.drawable.rajawali, "-7.2331067", "112.7309005"
        );
        TblParking.insertParking("Tugu Pahlawan", "Jalan Dupak"
                , "600 meter", "5000", "2000","09.00 - 20.00"
                , R.drawable.ic_image_placeholder, "-7.2456563", "112.731951"
        );
        TblParking.insertParking("Tugu Pahlawan", "Jalan Tembaan"
                , "350 meter", "5000", "2000","09.00 - 20.00"
                , R.drawable.ic_image_placeholder, "-7.2465241", "112.7359024"
        );
        TblParking.insertParking("Kebun Binatang Surabaya (Surabaya Zoo)", "Jalan Kutai"
                , "1 km", "5000", "2000","09.00 - 20.00"
                , R.drawable.ic_image_placeholder, "-7.2931932", "112.7340974"
        );
        TblParking.insertParking("Kebun Binatang Surabaya (Surabaya Zoo)", "Jalan Ciliwung"
                , "550 meter", "5000", "2000","09.00 - 20.00"
                , R.drawable.ic_image_placeholder, "-7.2941978", "112.7349584"
        );
        TblParking.insertParking("Taman Bungkul", "Jalan Taman Bungkul"
                , "45 meter", "5000", "2000","09.00 - 20.00"
                , R.drawable.ic_image_placeholder, "-7.2911241", "112.7379271"
        );
        TblParking.insertParking("Taman Bungkul", "Jalan Ciliwung"
                , "650 meter", "5000", "2000","09.00 - 20.00"
                , R.drawable.ic_image_placeholder, "-7.2921263", "112.7355533"
        );
        TblParking.insertParking("Taman Bungkul", "Jalan Serayu"
                , "230 meter", "5000", "2000","09.00 - 20.00"
                , R.drawable.ic_image_placeholder, "-7.2905879", "112.7384362"
        );
        TblParking.insertParking("Taman Bungkul", "Jalan Progo"
                , "48 meter", "5000", "2000","09.00 - 20.00"
                , R.drawable.ic_image_placeholder, "-7.2916926", "112.7379744"
        );
        TblParking.insertParking("Jembatan Merah", "Jalan Karet"
                , "300 meter", "5000", "2000","09.00 - 20.00"
                , R.drawable.ic_image_placeholder, "-7.2370508", "112.7386102"
        );
        TblParking.insertParking("Jembatan Merah", "Jalan Kembang Jepun"
                , "300 meter", "5000", "2000","09.00 - 20.00"
                , R.drawable.ic_image_placeholder, "-7.235833", "112.7335715"
        );
        TblParking.insertParking("Balai Pemuda", "Jl. Gubernur Suryo No.15"
                , "10 meter", "5000", "2000", "09.00 - 18.00"
                ,R.drawable.balaipemuda, "-7.2639278", "112.743107"
        );
        TblParking.insertParking("Bank BTPN", "Jalan Kertajaya no 92", "100 meter"
                , "5000", "2000", "09.00 - 20.00", R.drawable.kertajaya92
                , "-7.2781546", "112.7517853"
        );
        TblParking.insertParking("Convention Hall", "Jalan Arief Rahman Hakim"
                , "50 meter", "5000", "2000", "09.00 - 20.00"
                , R.drawable.conhall, "-7.2886099", "112.7814609"
        );
        TblParking.insertParking("Dinas Kesehatan", "Jalan Jemur Sari No.197", "50 meter"
                , "5000", "2000", "08.00 - 15.00"
                , R.drawable.dinkes, "-7.3170345", "112.7493293"
        );
        TblParking.insertParking("Religi Ampel", "Jalan Ampel Masjid No.53 ", "50 meter"
                , "5000", "2000", "08.00 - 18.00"
                , R.drawable.ampel, "-7.2299808", "112.7405817"
        );
        TblParking.insertParking("Lapangan Hokky", "Jalan Ampel Masjid No.53 ", "50 meter"
                , "5000", "2000", "08.00 - 18.00"
                , R.drawable.ampel, "-7.2691689", "112.7540655"
        );
        TblParking.insertParking("Gedung Siola", "Jl. Tunjungan No.1", " "
                , "5000", "2000", "08.00 - 18.00"
                , R.drawable.siola, "-7.2563177", "112.7354367"
        );
        TblParking.insertParking("RSUD Dr. M. Soewandhie", "Jalan Tambak Rejo No. 45 - 47"
                , " ", "5000", "2000", "24 Jam"
                , R.drawable.soewandi, "-7.2459819", "112.7558208"
        );
        TblParking.insertParking("UPTSA", "Jalan Menur No. 31 C"
                , " ", "5000", "2000", "07.00 - 15.00"
                , R.drawable.uptsa, "-7.2459819", "112.7558208"
        );
        TblParking.insertParking("Park And Ride Sungkono", "Jl. Mayjen Sungkono No.105"
                , " ", "5000", "2000", "24 Jam"
                , R.drawable.parkride, "-7.2905331", "112.7129581"
        );



    }
}