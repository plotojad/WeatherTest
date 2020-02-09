package com.plotojad.testapp3;

import java.util.ArrayList;
import java.util.Map;

public interface MainContract {
    interface View{
        void showResult(String nameCity, String typeCity, String season, String midTemp);
    }

    interface Presenter {
        ArrayList<String> loadCityList();
        void onCityWasSelected(String name, String season);
        void onDestroy();
    }

    interface Repository {
        ArrayList<String> loadCityListNames();
        Map<String, Object>  loadCityInfo(String name, String season);
        void writeCityInfo(String nameCity, String typeCity, String season, int firstMonthTemp, int secondMonthTemp, int thirdMonthTemp);
    }
}
