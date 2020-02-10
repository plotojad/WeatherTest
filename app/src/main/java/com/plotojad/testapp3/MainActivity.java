package com.plotojad.testapp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.plotojad.testapp3.dialogs.AddInfoDialogFragment;
import com.plotojad.testapp3.dialogs.SettingsDialogFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    final String TAG = "myLog";

    private MainContract.Presenter mPresenter;
    private Spinner spinnCity, spinnSeason;
    private TextView tvCityName, tvType, tvSeason, tvTemp;
    ArrayAdapter<String> adapterCityList, adapterSeasonList;

    String[] seasons = {"Зима", "Весна", "Лето", "Осень"};
    ArrayList<String> cities = new ArrayList<>();

    SettingsDialogFragment mSettingsDialogFragment;
    AddInfoDialogFragment mAddInfoDialogFragment;

    boolean isSlected = false;
    String cityName;
    String seasonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSettingsDialogFragment = new SettingsDialogFragment();
        mAddInfoDialogFragment = new AddInfoDialogFragment(this);
        mPresenter = new MainPresenter(this, getBaseContext());
        cities = mPresenter.loadCityList();
        spinnCity = findViewById(R.id.spinnCity);
        spinnSeason = findViewById(R.id.spinnSeason);
        tvCityName = findViewById(R.id.tvCityName);
        tvType = findViewById(R.id.tvType);
        tvSeason = findViewById(R.id.tvSeason);
        tvTemp = findViewById(R.id.tvTemp);

        if (cities == null){
//            Toast.makeText(this, getResources().getString(R.string.addDataAlert), Toast.LENGTH_LONG).show();
            mAddInfoDialogFragment.show(getSupportFragmentManager(), mAddInfoDialogFragment.getClass().getName());
//            return;
        } else {
            initAdapters();
        }

    }

    @Override
    public void showResult(String nameCity, String typeCity, String season, String midTemp) {
        tvCityName.setText(nameCity);
        tvType.setText(typeCity);
        tvSeason.setText(season);
        tvTemp.setText(midTemp);
    }

    @Override
    public void initAdapters() {
        if (cities != null) {
            adapterCityList = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
            adapterCityList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnCity.setAdapter(adapterCityList);
            spinnCity.setPrompt("Выберите город:");
            spinnCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    cityName = adapterView.getItemAtPosition(i).toString();
                    if (isSlected && seasonName != null) {
                        mPresenter.onCityWasSelected(cityName, seasonName);
                    }
                    isSlected = true;
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            adapterSeasonList = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, seasons);
            adapterSeasonList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnSeason.setAdapter(adapterSeasonList);
            spinnSeason.setPrompt("Выберите сезон:");
            spinnSeason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    seasonName = adapterView.getItemAtPosition(i).toString();
                    if (isSlected && cityName != null) {
                        mPresenter.onCityWasSelected(cityName, seasonName);
                    }
                    isSlected = true;
                    Log.d(TAG, "Season name: " + seasonName);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Toast.makeText(getBaseContext(), "выберите сезон!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            cities = mPresenter.loadCityList();
        }
    }

    @Override
    public void updateAdapters() {
        if (adapterCityList != null) {
            adapterCityList.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSettings:
                mSettingsDialogFragment.show(getSupportFragmentManager(), mSettingsDialogFragment.getClass().getName());
                break;
            case R.id.itemAddInfo:
                mAddInfoDialogFragment.show(getSupportFragmentManager(), mAddInfoDialogFragment.getClass().getName());
                break;
        }
        return true;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mPresenter = null;
        mSettingsDialogFragment = null;
        mAddInfoDialogFragment = null;
    }
}
