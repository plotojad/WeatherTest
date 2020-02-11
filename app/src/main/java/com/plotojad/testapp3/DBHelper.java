package com.plotojad.testapp3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "weatherDb";
    public static final String TABLE_NAME = "cityinfo";
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_TYPE = "type";
    public static final String KEY_WIN = "winter";
    public static final String KEY_SPR = "spring";
    public static final String KEY_SUM = "summer";
    public static final String KEY_AUT = "autumn";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(" + KEY_ID + " integer primary key, " + KEY_NAME
                + " text, " + KEY_TYPE + " text, " + KEY_WIN + " real, " + KEY_SPR + " real, " + KEY_SUM + " real, "
                + KEY_AUT + " real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
