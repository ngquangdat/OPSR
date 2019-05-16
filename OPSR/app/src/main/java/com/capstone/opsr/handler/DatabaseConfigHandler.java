package com.capstone.opsr.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseConfigHandler extends SQLiteOpenHelper {

    public DatabaseConfigHandler(Context context, String dbName, int version) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql ="CREATE TABLE config(" +
                "id integer PRIMARY KEY AUTOINCREMENT," +
                "language text," +
                "country text" +
                ")";
        db.execSQL(sql);
        ContentValues values = new ContentValues();
        values.put("language", "vi");
        values.put("country", "VN");
        db.insert("config",null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion != oldVersion){
            String sql="DROP TABLE IF EXISTS config";
            db.execSQL(sql);
            onCreate(db);
        }
    }
}
