package com.witlife.headline;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.witlife.headline.database.table.NewsChannelTable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static SQLiteDatabase db = null;

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
