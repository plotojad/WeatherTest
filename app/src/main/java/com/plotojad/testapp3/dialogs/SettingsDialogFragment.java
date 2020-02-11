package com.plotojad.testapp3.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.plotojad.testapp3.MainActivity;
import com.plotojad.testapp3.R;

public class SettingsDialogFragment extends DialogFragment {
    private final String KEY_FORMAT = "keyTFormat";
    private final String NAME_SETTINGS = "mSettings";
    private SharedPreferences sharedPreferences;
    private Spinner spinnTempFormat;
    private Button btnSelect;
    private String[] tempsVariants = {"Цельсий", "Фаренгейт", "Кельвин"};
    private ArrayAdapter<String> adapter;

    private Activity activity;


    public SettingsDialogFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.settingsdialog, null);
        sharedPreferences = getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);
        final SharedPreferences.Editor sEd = sharedPreferences.edit();
        spinnTempFormat = v.findViewById(R.id.spinnTempFormat);
        btnSelect = v.findViewById(R.id.btnSelect);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, tempsVariants);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnTempFormat.setAdapter(adapter);
        spinnTempFormat.setSelection(adapter.getPosition(sharedPreferences.getString(KEY_FORMAT, "")));
        spinnTempFormat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sEd.putString(KEY_FORMAT, spinnTempFormat.getSelectedItem().toString());
                sEd.apply();
                ((MainActivity) activity).getmPresenter().onCityWasSelected(
                        ((MainActivity) activity).getSpinnCity().getSelectedItem().toString(),
                        ((MainActivity) activity).getSpinnSeason().getSelectedItem().toString());
                dismiss();
            }
        });
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            activity = (Activity) context;
        }
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        dismiss();
        super.onCancel(dialog);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
