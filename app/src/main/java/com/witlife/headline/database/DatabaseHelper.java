package com.witlife.headline;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.witlife.headline.database.table.NewsChannelTable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String CLEAR_TABLE_DATA = "delete from ";
    private static final String DB_NAME = "Headline";
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
        switch (oldVersion){
            case 1:
                break;
            case 2:
                db.execSQL(CLEAR_TABLE_DATA + NewsChannelTable.TABLENAME);
                break;
            case 3:
                ContentValues values = new ContentValues();
                values.put(NewsChannelTable.ID, "");
                values.put(NewsChannelTable.NAME, "Recommondation");
                values.put(NewsChannelTable.IS_ENABLE, 0);
                values.put(NewsChannelTable.POSITION, 46);
                db.insert(NewsChannelTable.TABLENAME, null, values);
                break;
            case 4:
                //db.execSQL();
            case 5:
                db.delete(NewsChannelTable.TABLENAME, NewsChannelTable.ID + "=?", new String[]{"essay_joke"});
                break;
        }

    }

    private static synchronized DatabaseHelper getInstance(){
        if (instance == null){
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