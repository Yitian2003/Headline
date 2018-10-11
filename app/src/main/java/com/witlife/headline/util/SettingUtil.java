package com.witlife.headline.util;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

import com.witlife.headline.InitApp;
import com.witlife.headline.R;

public class SettingUtil {

    private SharedPreferences setting = PreferenceManager.getDefaultSharedPreferences(InitApp.appContext);

    public static SettingUtil getInstance() {
        return SettingsUtilInstance.instance;
    }

    public int getCustomIconValue() {
        String s = setting.getString("custom_icon", "0");
        return Integer.parseInt(s);
    }

    public int getSlidable() {
        String s = setting.getString("slidable", "1");
        return Integer.parseInt(s);
    }

    public int getColor() {
        int defaultColor = InitApp.appContext.getResources().getColor(R.color.colorPrimary);
        int color = setting.getInt("color", defaultColor);
        if((color != 0) && Color.alpha(color) != 255){
            return defaultColor;
        }
        return color;
    }

    public boolean getNavBar() {
        return setting.getBoolean("nav_bar", false);
    }


    private static class SettingsUtilInstance {

        private static SettingUtil instance = new SettingUtil();
    }
}
