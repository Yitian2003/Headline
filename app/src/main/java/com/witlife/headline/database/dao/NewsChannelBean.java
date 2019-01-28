package com.witlife.headline.database.dao;

import android.database.sqlite.SQLiteDatabase;

import com.witlife.headline.DatabaseHelper;
import com.witlife.headline.InitApp;
import com.witlife.headline.R;

public class NewsChannelBean {

    private String channelId;
    private String channelName;
    private int isEnable;
    private int position;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        NewsChannelBean bean = (NewsChannelBean) obj;

        if(isEnable != bean.isEnable) return false;
        if(position != bean.position) return false;
        if(channelId != null ? !channelId.equals(bean.channelId) : bean.channelId != null)
            return false;
        return channelName != null ? channelName.equals(bean.channelName) : bean.channelName == null;
    }

    @Override
    public int hashCode() {
        int result = channelId != null ? channelId.hashCode() : 0;
        result  = 31 * result + (channelName != null ? channelName.hashCode() :0);
        result = 31 * result + isEnable;
        result = 31 * result + position;
        return result;
    }
}
