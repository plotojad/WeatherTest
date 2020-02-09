package com.plotojad.testapp3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainRepository implements MainContract.Repository {
    @Override
    public ArrayList<String> loadCityListNames() {
        return capOfLoadCityListNames();
    }

    @Override
    public Map<String, Object> loadCityInfo(String name, String season) {
        return capOfLoadCityInfo(name, season);
    }

    @Override
    public void writeCityInfo(String nameCity, String typeCity, String season, int firstMonthTemp, int secondMonthTemp, int thirdMonthTemp) {

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
        cityInfo.put("firstTemp", 21);
        cityInfo.put("secondTemp", 22);
        cityInfo.put("thirdTemp", 24);
        return cityInfo;
    }
}
