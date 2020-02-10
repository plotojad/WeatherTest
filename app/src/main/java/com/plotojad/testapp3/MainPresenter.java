package com.plotojad.testapp3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Map;

public class MainPresenter implements MainContract.Presenter {

    final String TAG = "myLog";

    private MainContract.View mView;
    private MainContract.Repository mRepository;

    private String name;
    private String type;
    private String season;
    private String middleTemp;
    private Map<String, Object> cityInfoMap;

    public MainPresenter(MainContract.View mView, Context context) {
        this.mView = mView;
        this.mRepository = new MainRepository(context);
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
            float winterT = meanCalculateToFloat(Integer.parseInt(data.get("decTemp")), Integer.parseInt(data.get("janTemp")), Integer.parseInt(data.get("febTemp")));
            float springT = meanCalculateToFloat(Integer.parseInt(data.get("marTemp")), Integer.parseInt(data.get("aprTemp")), Integer.parseInt(data.get("mayTemp")));
            float summerT = meanCalculateToFloat(Integer.parseInt(data.get("junTemp")), Integer.parseInt(data.get("julTemp")), Integer.parseInt(data.get("augTemp")));
            float autumnT = meanCalculateToFloat(Integer.parseInt(data.get("sepTemp")), Integer.parseInt(data.get("ocTemp")), Integer.parseInt(data.get("novTemp")));
            mRepository.writeCityInfo(name, type, winterT, springT, summerT, autumnT);
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
        this.middleTemp = (String.format("%.1f", cityInfoMap.get("midTemp")));
        mView.showResult(this.name, this.type, this.season, this.middleTemp);
    }

    @Override
    public void onDestroy() {
        mView = null;
        mRepository = null;
        name = null;
        type = null;
        season = null;
        middleTemp = null;
        cityInfoMap = null;
    }

    @SuppressLint("DefaultLocale")
    private synchronized float meanCalculateToFloat(int first, int second, int third) {
        float mean = (first + second + third) / 3.0f;
        Log.d(TAG, "double: " + mean);
        return mean;
    }
}
