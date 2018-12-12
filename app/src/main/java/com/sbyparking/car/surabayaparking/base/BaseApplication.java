package com.sbyparking.car.surabayaparking.base;

import android.app.Application;
import android.util.Log;

import com.sbyparking.car.surabayaparking.R;
import com.sbyparking.car.surabayaparking.common.Global;
import com.sbyparking.car.surabayaparking.database.Database;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class BaseApplication extends Application {

    private static final String CLASS_NAME = "BaseApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Database.initInstance(getApplicationContext());
        Log.e(Global.APP_TAG, CLASS_NAME + ":onCreate");

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/intro.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @Override
    public void onTerminate() {
        Database.instance.close();
        Log.e(Global.APP_TAG, CLASS_NAME + ":onTerminate");
        super.onTerminate();
    }
}
