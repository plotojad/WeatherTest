package com.plotojad.testapp3.dialogs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.plotojad.testapp3.R;

public class SettingsDialogFragment extends DialogFragment {
    Spinner spinnTempFormat;
    String[] tempsVariants = {"Цельсий", "Фаренгейт", "Кельвин"};
    ArrayAdapter<String> adapter;


    public SettingsDialogFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.settingsdialog, null);
        spinnTempFormat = v.findViewById(R.id.spinnTempFormat);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, tempsVariants);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnTempFormat.setAdapter(adapter);
        spinnTempFormat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return v;
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        dismiss();
        super.onCancel(dialog);
    }


}
