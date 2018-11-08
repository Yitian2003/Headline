package com.witlife.headline.database.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.witlife.headline.Constant;
import com.witlife.headline.DatabaseHelper;
import com.witlife.headline.InitApp;
import com.witlife.headline.R;
import com.witlife.headline.database.table.NewsChannelTable;

import java.util.List;

public class NewsChannelDao {

    private final SQLiteDatabase db;

    public NewsChannelDao(){
        this.db = DatabaseHelper.getDatabase();
    }

    public void addInitData(){
        String categoryId[] = InitApp.appContext.getResources().getStringArray(R.array.mobile_news_id);
        String categoryName[] = InitApp.appContext.getResources().getStringArray(R.array.mobile_news_name);
        for(int i = 0; i< 8; i++){
            add(categoryId[i], categoryName[i], Constant.NEWS_CHANNEL_ENABLE, i);
        }
        for (int i = 9; i < categoryId.length; i++){
            add(categoryId[i], categoryName[i], Constant.NEWS_CHANNEL_DISABLE, i);
        }
    }

    private boolean add(String channelId, String channelName, int isEnable, int position) {
        ContentValues values = new ContentValues();
        values.put(NewsChannelTable.ID, channelId);
        values.put(NewsChannelTable.NAME, channelName);
        values.put(NewsChannelTable.IS_ENABLE, isEnable);
        values.put(NewsChannelTable.POSITION, position);
        long result = db.insert(NewsChannelTable.TABLENAME, null, values);
        return  result != -1;
    }

    public List<NewsChannelBean> query(int isEnable) {
        db.query(NewsChannelTable.TABLENAME, null, NewsChannelTable.IS_ENABLE + "=?", new String[]{isEnable + ""}, null, null, null);

    }
}
