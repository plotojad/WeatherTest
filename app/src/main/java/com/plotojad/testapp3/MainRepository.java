package com.plotojad.testapp3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainRepository implements MainContract.Repository {

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private ContentValues contentValues;
    private Cursor cursor;

    @Override
    public void init(Context context) {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        contentValues = new ContentValues();
    }

    private MainRepository(){}
    private static MainRepository instance;
    public static MainRepository getInstance(){
        if (instance == null){
            instance = new MainRepository();
        }
        return instance;
    }

    @Override
    public ArrayList<String> loadCityListNames() {
        ArrayList<String> listOfNames = new ArrayList<>();
        cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, DBHelper.KEY_NAME);
        if (cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            do {
                listOfNames.add(cursor.getString(nameIndex));
            } while (cursor.moveToNext());
            cursor.close();
            return listOfNames;
        } else {
            cursor.close();
            return null;
        }
    }

    @Override
    public Map<String, Object> loadCityInfo(String name, String season) {
        Map<String, Object> cityInfo = new HashMap<>();

        cursor = database.query(DBHelper.TABLE_NAME, null, DBHelper.KEY_NAME + " = ?", new String[]{name}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            int typeIndex = cursor.getColumnIndex(DBHelper.KEY_TYPE);
            int tempIndex;
            switch (season) {
                case "Зима":
                    tempIndex = cursor.getColumnIndex(DBHelper.KEY_WIN);
                    break;
                case "Весна":
                    tempIndex = cursor.getColumnIndex(DBHelper.KEY_SPR);
                    break;
                case "Лето":
                    tempIndex = cursor.getColumnIndex(DBHelper.KEY_SUM);
                    break;
                case "Осень":
                    tempIndex = cursor.getColumnIndex(DBHelper.KEY_AUT);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + season);
            }

            cityInfo.put("name", cursor.getString(nameIndex));
            cityInfo.put("type", cursor.getString(typeIndex));
            cityInfo.put("season", season);
            cityInfo.put("midTemp", cursor.getFloat(tempIndex));
            cursor.close();
        }
        return cityInfo;
    }

    @Override
    public void writeCityInfo(String nameCity, String typeCity, float winterT, float springT, float summerT, float autumnT) {
        boolean isEq = false;
        if (contentValues.size() > 0) {
            contentValues.clear();
        }
        contentValues.put(DBHelper.KEY_NAME, nameCity);
        contentValues.put(DBHelper.KEY_TYPE, typeCity);
        contentValues.put(DBHelper.KEY_WIN, winterT);
        contentValues.put(DBHelper.KEY_SPR, springT);
        contentValues.put(DBHelper.KEY_SUM, summerT);
        contentValues.put(DBHelper.KEY_AUT, autumnT);

        cursor = database.query(DBHelper.TABLE_NAME, null, DBHelper.KEY_NAME + " = ?", new String[]{nameCity}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
            isEq = cursor.getString(nameIndex).equals(nameCity);
            cursor.close();
        }
        if (isEq) {
            database.update(DBHelper.TABLE_NAME, contentValues, DBHelper.KEY_NAME + " = ?", new String[]{nameCity});
        } else {
            database.insert(DBHelper.TABLE_NAME, null, contentValues);
        }

    }

    @Override
    public void onDestroy() {
        dbHelper = null;
        database.close();
        database = null;
        contentValues.clear();
        contentValues = null;
        if (!cursor.isClosed()) {
            cursor.close();
        }
        cursor = null;
    }
}
