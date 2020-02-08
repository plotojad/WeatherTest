package com.plotojad.testapp3;

import java.util.ArrayList;
import java.util.Map;

public class MainRepository implements MainContract.Repository {
    @Override
    public ArrayList<String> loadCityListNames() {
        return null;
    }

    @Override
    public Map<String, Object> loadCityInfo(String name) {
        return null;
    }

    @Override
    public void writeCityInfo(String nameCity, String typeCity, String season, int firstMonthTemp, int secondMonthTemp, int thirdMonthTemp) {

    }
}
