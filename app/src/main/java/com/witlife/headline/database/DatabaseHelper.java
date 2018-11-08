package com.witlife.headline.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.witlife.headline.InitApp;
import com.witlife.headline.database.table.NewsChannelTable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Toutiao";
    private static final int DB_VERSION = 6;
    private static SQLiteDatabase db = null;
    private static DatabaseHelper instance = null;

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NewsChannelTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static synchronized DatabaseHelper getInstance(){
        if(instance == null){
            instance = new DatabaseHelper(InitApp.appContext, DB_NAME, null, DB_VERSION);
        }
        return instance;
    }

    public static synchronized SQLiteDatabase getDatabase(){
        if (db == null){
            db = getInstance().getWritableDatabase();
        }
        return  db;
    }

    public static synchronized void closeDatabase(){
        if (db != null){
            db.close();
        }
    }
}
