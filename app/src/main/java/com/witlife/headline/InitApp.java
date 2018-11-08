package com.witlife.headline;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import com.witlife.headline.util.SettingUtil;

import java.util.Calendar;

public class InitApp extends MultiDexApplication {

    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();

        initTheme();
    }

    private void initTheme() {
        SettingUtil settingUtil = SettingUtil.getInstance();

        if(settingUtil.getIsAutoNightMode()){
            int nightStartHour = Integer.parseInt(settingUtil.getNightStartHour());
            int nightStartMinute = Integer.parseInt(settingUtil.getNightStartMinute());
            int dayStartHour = Integer.parseInt(settingUtil.getDayStartHour());
            int dayStartMinute = Integer.parseInt(settingUtil.getDayStartMinute());

            Calendar calendar = Calendar.getInstance();
            int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            int currentMinute = calendar.get(Calendar.MINUTE);

            int nightValue = nightStartHour * 60 + nightStartHour;
            int dayValue = dayStartHour * 60 + dayStartMinute;
            int currentValue = currentHour * 60 + currentMinute;

            if (currentValue >= nightValue || currentValue <= dayValue){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }
    }
}
