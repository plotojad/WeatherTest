package com.plotojad.testapp3;


import android.content.Context;

import java.util.ArrayList;
import java.util.Map;

public interface MainContract {
    interface View {
        void showResult(String nameCity, String typeCity, String season, String midTemp);

        void initAdapters();

        void updateAdapters();
    }

    interface Presenter {
        ArrayList<String> loadCityList();

        void addInfo(Map<String, String> data);

        void onCityWasSelected(String name, String season);

        void onDestroy();
    }

    interface Repository {
        void init(Context context);

        ArrayList<String> loadCityListNames();

        Map<String, Object> loadCityInfo(String name, String season);

        void writeCityInfo(String nameCity, String typeCity, float winterT, float springT, float summerT, float autumnT);

        void onDestroy();
    }
}
