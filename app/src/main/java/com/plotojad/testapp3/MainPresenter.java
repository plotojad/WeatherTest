package com.plotojad.testapp3;

import android.annotation.SuppressLint;
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

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        this.mRepository = new MainRepository();
    }

    @Override
    public ArrayList<String> loadCityList() {
        return mRepository.loadCityListNames();
    }

    @Override
    public void onCityWasSelected(String name, String season) {
        cityInfoMap = mRepository.loadCityInfo(name, season);
        this.name = name;
        this.type = (String) cityInfoMap.get("type");
        this.season = season;
        this.middleTemp = meanCalculate((int)cityInfoMap.get("firstTemp"),
                (int)cityInfoMap.get("secondTemp"),
                (int)cityInfoMap.get("thirdTemp"));
        mView.showResult(this.name, this.type, this.season, this.middleTemp);
    }

    @Override
    public void onDestroy() {

    }

    @SuppressLint("DefaultLocale")
    private String meanCalculate(int first, int second, int third) {
        String middlet;
        float mean = (first + second + third) / 3.0f;
        Log.d(TAG, "double: " + mean);
        middlet = String.format("%.1f", mean);
        return middlet;
    }
}
