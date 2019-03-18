package com.example.mtwastewater;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mtwastewater.Constant.*;

public class SQLite extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Peerapong.db";
    public static final int DATABASE_VERSION = 3;
    public SQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE = "CREATE TABLE " +
               _STATIC.TABLE_NAME + "("+
                _STATIC._ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                _STATIC.COL_DATA + " TEXT NOT NULL);";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL(" DROP TABLE IF EXISTS " + _STATIC.TABLE_NAME);
       onCreate(db);
    }
}
