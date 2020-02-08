package com.plotojad.testapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private MainContract.Presenter mPresenter;
    private Spinner spinnCity, spinnSeason;
    private TextView tvCityName, tvType, tvSeason, tvTemp;

    String[] seasons = {"Зима", "Весна", "Лето", "Осень"};
    ArrayList<String> cities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(this);
        spinnCity = findViewById(R.id.spinnCity);
        spinnSeason = findViewById(R.id.spinnSeason);
        tvCityName = findViewById(R.id.tvCityName);
        tvType = findViewById(R.id.tvType);
        tvSeason = findViewById(R.id.tvSeason);
        tvTemp = findViewById(R.id.tvTemp);
    }

    @Override
    public void showResult(String nameCity, String typeCity, String season, String midTemp) {
        tvCityName.setText(nameCity);
        tvType.setText(typeCity);
        tvSeason.setText(season);
        tvTemp.setText(midTemp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
