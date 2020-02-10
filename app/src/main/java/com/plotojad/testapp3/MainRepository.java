package com.plotojad.testapp3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainRepository implements MainContract.Repository {

    Context context;
    DBHelper dbHelper;
    SQLiteDatabase database;
    ContentValues contentValues;
    Cursor cursor;

    public MainRepository(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        contentValues = new ContentValues();

    }

    @Override
    public ArrayList<String> loadCityListNames() {
        ArrayList<String> listOfNames = new ArrayList<>();
        cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
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
        return capOfLoadCityInfo(name, season);
    }

    @Override
    public void writeCityInfo(String nameCity, String typeCity, float winterT, float springT, float summerT, float autumnT) {
        if (contentValues.size()>0){
            contentValues.clear();
        }
        contentValues.put(DBHelper.KEY_NAME, nameCity);
        contentValues.put(DBHelper.KEY_TYPE, typeCity);
        contentValues.put(DBHelper.KEY_WIN, winterT);
        contentValues.put(DBHelper.KEY_SPR, springT);
        contentValues.put(DBHelper.KEY_SUM, summerT);
        contentValues.put(DBHelper.KEY_AUT, autumnT);
        database.insert(DBHelper.TABLE_NAME, null, contentValues);
    }





    ArrayList<String> capOfLoadCityListNames (){
        ArrayList<String> cities = new ArrayList<>();
        cities.add("Москва");
        cities.add("Екатеринбург");
        cities.add("Донецк");
        cities.add("Луганск");
        cities.add("Сочи");

        return cities;
    }

    Map<String, Object> capOfLoadCityInfo(String name, String season) {
        Map<String, Object> cityInfo = new HashMap<>();
        cityInfo.put("name", name);
        cityInfo.put("type", "Крупный");
        cityInfo.put("season", season);
        cityInfo.put("midTemp", 21.333f);
        return cityInfo;
    }
}
