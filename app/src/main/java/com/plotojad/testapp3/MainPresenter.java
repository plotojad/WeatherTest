package com.plotojad.testapp3;

import java.util.Map;

public class MainPresenter implements MainContract.Presenter {

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
    public void loadCityList() {

    }

    @Override
    public void onCityWasSelected(String name) {
        cityInfoMap = mRepository.loadCityInfo(name);
        this.name = (String) cityInfoMap.get("name");
        this.type = (String) cityInfoMap.get("type");
        this.season = (String) cityInfoMap.get("season");
        this.middleTemp = meanCalculate((int)cityInfoMap.get("firstTemp"),
                (int)cityInfoMap.get("secondTemp"),
                (int)cityInfoMap.get("thirdTemp"));
    }

    @Override
    public void onDestroy() {

    }

    private String meanCalculate(int first, int second, int third) {
        String middlet;
        float mean = (first + second + third) / 3;
        middlet = String.valueOf(mean);
        return middlet;
    }
}
