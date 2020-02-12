package com.plotojad.testapp3.dialogs;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.plotojad.testapp3.MainContract;
import com.plotojad.testapp3.MainPresenter;
import com.plotojad.testapp3.R;

import java.util.HashMap;
import java.util.Map;

public class AddInfoDialogFragment extends DialogFragment {

    private final String KEY_FORMAT = "keyTFormat";
    private final String NAME_SETTINGS = "mSettings";
    private final String KEY_C = "Цельсий";
    private final String KEY_F = "Фаренгейт";
    private final String KEY_K = "Кельвин";

    private MainContract.Presenter mPresenter;
    private MainContract.View mView;

    private SharedPreferences sharedPreferences;

    private Spinner spinnTypeAdd;
    private EditText etCityNameAdd, etDecTempAdd, etJanTempAdd, etFebTempAdd, etMarTempAdd, etAprTempAdd,
            etMayTempAdd, etJunTempAdd, etJulTempAdd, etAugTempAdd, etSeptTempAdd, etOctTempAdd,
            etNovTempAdd;
    private Button btAddInfo;
    private String[] types = {"Крупный", "Средний", "Малый"};
    private Map<String, String> data;
    private String typeName;


    public AddInfoDialogFragment() {
    }

    public AddInfoDialogFragment(MainContract.View view) {
        this.mView = view;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.addinfodialog, null);

        sharedPreferences = getContext().getSharedPreferences(NAME_SETTINGS, Context.MODE_PRIVATE);

        spinnTypeAdd = v.findViewById(R.id.spinnTypeAdd);
        etCityNameAdd = v.findViewById(R.id.etCityNameAdd);
        etDecTempAdd = v.findViewById(R.id.etDecTempAdd);
        etJanTempAdd = v.findViewById(R.id.etJanTempAdd);
        etFebTempAdd = v.findViewById(R.id.etFebTempAdd);
        etMarTempAdd = v.findViewById(R.id.etMarTempAdd);
        etAprTempAdd = v.findViewById(R.id.etAprTempAdd);
        etMayTempAdd = v.findViewById(R.id.etMayTempAdd);
        etJunTempAdd = v.findViewById(R.id.etJunTempAdd);
        etJulTempAdd = v.findViewById(R.id.etJulTempAdd);
        etAugTempAdd = v.findViewById(R.id.etAugTempAdd);
        etSeptTempAdd = v.findViewById(R.id.etSeptTempAdd);
        etOctTempAdd = v.findViewById(R.id.etOctTempAdd);
        etNovTempAdd = v.findViewById(R.id.etNovTempAdd);
        btAddInfo = v.findViewById(R.id.btAddInfo);

        data = new HashMap<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnTypeAdd.setAdapter(adapter);
        spinnTypeAdd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeName = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btAddInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter = new MainPresenter(mView, getContext());

                if (etCityNameAdd.getText() == null || etCityNameAdd.getText().toString().equals("")) {
                    Toast.makeText(getContext(), getResources().getString(R.string.enterTheNameToast), Toast.LENGTH_SHORT).show();
                    etCityNameAdd.setFocusable(true);
                    return;
                } else {
                    data.put("name", etCityNameAdd.getText().toString());
                }

                data.put("type", typeName);

                if (etDecTempAdd.getText().length() == 0) {
                    Toast.makeText(getContext(), getResources().getString(R.string.enterTheInfoToast), Toast.LENGTH_SHORT).show();
                    etDecTempAdd.setFocusable(true);
                    etDecTempAdd.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                    return;
                } else {
                    etDecTempAdd.setBackgroundColor(getResources().getColor(android.R.color.white));
                    data.put("decTemp", etDecTempAdd.getText().toString());
                }

                if (etJanTempAdd.getText().length() == 0) {
                    Toast.makeText(getContext(), getResources().getString(R.string.enterTheInfoToast), Toast.LENGTH_SHORT).show();
                    etJanTempAdd.setFocusable(true);
                    etJanTempAdd.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                    return;
                } else {
                    etJanTempAdd.setBackgroundColor(getResources().getColor(android.R.color.white));
                    data.put("janTemp", etJanTempAdd.getText().toString());
                }

                if (etFebTempAdd.getText().length() == 0) {
                    Toast.makeText(getContext(), getResources().getString(R.string.enterTheInfoToast), Toast.LENGTH_SHORT).show();
                    etFebTempAdd.setFocusable(true);
                    etFebTempAdd.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                    return;
                } else {
                    etFebTempAdd.setBackgroundColor(getResources().getColor(android.R.color.white));
                    data.put("febTemp", etFebTempAdd.getText().toString());
                }

                if (etMarTempAdd.getText().length() == 0) {
                    Toast.makeText(getContext(), getResources().getString(R.string.enterTheInfoToast), Toast.LENGTH_SHORT).show();
                    etMarTempAdd.setFocusable(true);
                    etMarTempAdd.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                    return;
                } else {
                    etMarTempAdd.setBackgroundColor(getResources().getColor(android.R.color.white));
                    data.put("marTemp", etMarTempAdd.getText().toString());
                }

                if (etAprTempAdd.getText().length() == 0) {
                    Toast.makeText(getContext(), getResources().getString(R.string.enterTheInfoToast), Toast.LENGTH_SHORT).show();
                    etAprTempAdd.setFocusable(true);
                    etAprTempAdd.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                    return;
                } else {
                    etAprTempAdd.setBackgroundColor(getResources().getColor(android.R.color.white));
                    data.put("aprTemp", etAprTempAdd.getText().toString());
                }

                if (etMayTempAdd.getText().length() == 0) {
                    Toast.makeText(getContext(), getResources().getString(R.string.enterTheInfoToast), Toast.LENGTH_SHORT).show();
                    etMayTempAdd.setFocusable(true);
                    etMayTempAdd.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                    return;
                } else {
                    etMayTempAdd.setBackgroundColor(getResources().getColor(android.R.color.white));
                    data.put("mayTemp", etMayTempAdd.getText().toString());
                }

                if (etJunTempAdd.getText().length() == 0) {
                    Toast.makeText(getContext(), getResources().getString(R.string.enterTheInfoToast), Toast.LENGTH_SHORT).show();
                    etJunTempAdd.setFocusable(true);
                    etJunTempAdd.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                    return;
                } else {
                    etJunTempAdd.setBackgroundColor(getResources().getColor(android.R.color.white));
                    data.put("junTemp", etJunTempAdd.getText().toString());
                }

                if (etJulTempAdd.getText().length() == 0) {
                    Toast.makeText(getContext(), getResources().getString(R.string.enterTheInfoToast), Toast.LENGTH_SHORT).show();
                    etJulTempAdd.setFocusable(true);
                    etJulTempAdd.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                    return;
                } else {
                    etJulTempAdd.setBackgroundColor(getResources().getColor(android.R.color.white));
                    data.put("julTemp", etJulTempAdd.getText().toString());
                }

                if (etAugTempAdd.getText().length() == 0) {
                    Toast.makeText(getContext(), getResources().getString(R.string.enterTheInfoToast), Toast.LENGTH_SHORT).show();
                    etAugTempAdd.setFocusable(true);
                    etAugTempAdd.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                    return;
                } else {
                    etAugTempAdd.setBackgroundColor(getResources().getColor(android.R.color.white));
                    data.put("augTemp", etAugTempAdd.getText().toString());
                }

                if (etSeptTempAdd.getText().length() == 0) {
                    Toast.makeText(getContext(), getResources().getString(R.string.enterTheInfoToast), Toast.LENGTH_SHORT).show();
                    etSeptTempAdd.setFocusable(true);
                    etSeptTempAdd.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                    return;
                } else {
                    etSeptTempAdd.setBackgroundColor(getResources().getColor(android.R.color.white));
                    data.put("sepTemp", etSeptTempAdd.getText().toString());
                }

                if (etOctTempAdd.getText().length() == 0) {
                    Toast.makeText(getContext(), getResources().getString(R.string.enterTheInfoToast), Toast.LENGTH_SHORT).show();
                    etOctTempAdd.setFocusable(true);
                    etOctTempAdd.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                    return;
                } else {
                    etOctTempAdd.setBackgroundColor(getResources().getColor(android.R.color.white));
                    data.put("ocTemp", etOctTempAdd.getText().toString());
                }

                if (etNovTempAdd.getText().length() == 0) {
                    Toast.makeText(getContext(), getResources().getString(R.string.enterTheInfoToast), Toast.LENGTH_SHORT).show();
                    etNovTempAdd.setFocusable(true);
                    etNovTempAdd.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                    return;
                } else {
                    etNovTempAdd.setBackgroundColor(getResources().getColor(android.R.color.white));
                    data.put("novTemp", etNovTempAdd.getText().toString());
                }
                mPresenter.addInfo(data);
                Toast.makeText(getContext(), getResources().getString(R.string.cityIsAdded), Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        String hint = "";
        switch (sharedPreferences.getString(KEY_FORMAT, "")) {
            case KEY_C:
                hint = "°C";
                break;
            case KEY_F:
                hint = "°F";
                break;
            case KEY_K:
                hint = "°K";
                break;
        }
        etDecTempAdd.setHint(hint);
        etJanTempAdd.setHint(hint);
        etFebTempAdd.setHint(hint);
        etMarTempAdd.setHint(hint);
        etAprTempAdd.setHint(hint);
        etMayTempAdd.setHint(hint);
        etJunTempAdd.setHint(hint);
        etJulTempAdd.setHint(hint);
        etAugTempAdd.setHint(hint);
        etSeptTempAdd.setHint(hint);
        etOctTempAdd.setHint(hint);
        etNovTempAdd.setHint(hint);
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        dismiss();
        super.onCancel(dialog);
    }

    @Override
    public void onStop() {
        super.onStop();
        etCityNameAdd.setText("");
        etDecTempAdd.setText("");
        etJanTempAdd.setText("");
        etFebTempAdd.setText("");
        etMarTempAdd.setText("");
        etAprTempAdd.setText("");
        etMayTempAdd.setText("");
        etJunTempAdd.setText("");
        etJulTempAdd.setText("");
        etAugTempAdd.setText("");
        etSeptTempAdd.setText("");
        etOctTempAdd.setText("");
        etNovTempAdd.setText("");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        spinnTypeAdd = null;
        btAddInfo = null;
    }
}
