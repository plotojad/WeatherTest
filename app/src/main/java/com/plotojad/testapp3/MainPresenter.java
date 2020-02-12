package com.plotojad.testapp3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Map;

public class MainPresenter implements MainContract.Presenter {


    private final String KEY_FORMAT = "keyTFormat";
    private final String NAME_SETTINGS = "mSettings";
    private final int KEY_ADD_CONVERT = 0;
    private final int KEY_SHOW_CONVERT = 1;
    private SharedPreferences sharedPreferences;
    private MainContract.View mView;
    private MainContract.Repository mRepository;

    private String name;
    private String type;
    private String season;
    private String middleTemp;
    private Map<String, Object> cityInfoMap;

    public MainPresenter(MainContract.View mView, Context context) {
        this.mView = mView;
        this.mRepository = MainRepository.getInstance();
        mRepository.init(context);
        sharedPreferences = context.getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
    }

    @Override
    public ArrayList<String> loadCityList() {
        return mRepository.loadCityListNames();
    }

    @Override
    public void addInfo(Map<String, String> data) {
        if (data != null) {
            String name = data.get("name");
            String type = data.get("type");
            float winterT = calculateToMiddle(
                    Float.parseFloat(data.get("decTemp")),
                    Float.parseFloat(data.get("janTemp")),
                    Float.parseFloat(data.get("febTemp")));
            float springT = calculateToMiddle(
                    Float.parseFloat(data.get("marTemp")),
                    Float.parseFloat(data.get("aprTemp")),
                    Float.parseFloat(data.get("mayTemp")));
            float summerT = calculateToMiddle(
                    Float.parseFloat(data.get("junTemp")),
                    Float.parseFloat(data.get("julTemp")),
                    Float.parseFloat(data.get("augTemp")));
            float autumnT = calculateToMiddle(
                    Float.parseFloat(data.get("sepTemp")),
                    Float.parseFloat(data.get("ocTemp")),
                    Float.parseFloat(data.get("novTemp")));
            mRepository.writeCityInfo(name, type,
                    converterTemp(winterT, KEY_ADD_CONVERT),
                    converterTemp(springT, KEY_ADD_CONVERT),
                    converterTemp(summerT, KEY_ADD_CONVERT),
                    converterTemp(autumnT, KEY_ADD_CONVERT));
            mView.initAdapters();
            mView.updateAdapters();
        }
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onCityWasSelected(String name, String season) {
        cityInfoMap = mRepository.loadCityInfo(name, season);
        this.name = name;
        this.type = (String) cityInfoMap.get("type");
        this.season = season;
        this.middleTemp = convertTempToString((float) cityInfoMap.get("midTemp"));
        mView.showResult(this.name, this.type, this.season, this.middleTemp);
    }

    @Override
    public void onDestroy() {
        mView = null;
        mRepository.onDestroy();
        mRepository = null;
        name = null;
        type = null;
        season = null;
        middleTemp = null;
        cityInfoMap = null;
    }

    @SuppressLint("DefaultLocale")
    private float calculateToMiddle(float first, float second, float third) {
        return (first + second + third) / 3.0f;
    }

    private String convertTempToString(float arg) {
        String tempStr = "No data";
        switch (sharedPreferences.getString(KEY_FORMAT, "")) {
            case "Цельсий":
                tempStr = (String.format("%.1f", arg) + "°C");
                break;
            case "Фаренгейт":
                tempStr = (String.format("%.1f", converterTemp(arg, KEY_SHOW_CONVERT))) + "°F";
                break;
            case "Кельвин":
                tempStr = (String.format("%.1f", converterTemp(arg, KEY_SHOW_CONVERT))) + "°K";
                break;
        }
        return tempStr;
    }

    private float converterTemp(float argt, int reverse) {
        float conversTemp = 0;
        switch (reverse) {
            case KEY_SHOW_CONVERT:
                switch (sharedPreferences.getString(KEY_FORMAT, "")) {
                    case "Цельсий":
                        conversTemp = argt;
                        break;
                    case "Фаренгейт":
                        conversTemp = (argt * 1.8f) + 32.0f;
                        break;
                    case "Кельвин":
                        conversTemp = argt + 273.15f;
                        break;
                }
                break;
            case KEY_ADD_CONVERT:
                switch (sharedPreferences.getString(KEY_FORMAT, "")) {
                    case "Цельсий":
                        conversTemp = argt;
                        break;
                    case "Фаренгейт":
                        conversTemp = (argt - 32.0f) / 1.8f;
                        break;
                    case "Кельвин":
                        conversTemp = argt - 273.15f;
                        break;
                }
                break;
        }
        return conversTemp;
    }
}
