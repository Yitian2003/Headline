package com.witlife.headline;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

public class InitApp extends MultiDexApplication {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();

        initTheme();
    }

    private void initTheme() {

    }
}
