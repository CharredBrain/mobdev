package ru.mirea.maksimovaok.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Profile extends Fragment {

    private EditText name;
    private EditText favColor;
    private EditText favSeason;

    private Button yes;
    private Button no;

    private final String USER_NAME = "USER_NAME";
    private final String USER_COLOR = "USER_COLOR";
    private final String USER_SEASON = "USER_SEASON";
    private final String USER_LOTR = "USER_LOTR";

    public String statusLoTR = "Did not watch";

    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button yes = view.findViewById(R.id.yes);
        yes.setOnClickListener(this::saveButton);
        Button no =  view.findViewById(R.id.no);
        no.setOnClickListener(this::saveButton);

        name = view.findViewById(R.id.name);
        favColor = view.findViewById(R.id.favColor);
        favSeason = view.findViewById(R.id.favSeason);

        SharedPreferences sharedPref = getContext().getSharedPreferences("profile", Context.MODE_PRIVATE);
        name.setText(sharedPref.getString(USER_NAME, ""));
        favColor.setText(sharedPref.getString(USER_COLOR, ""));
        favSeason.setText(sharedPref.getString(USER_SEASON, ""));

    }

    public void saveButton(View view){
        SharedPreferences sharedPref = getContext().getSharedPreferences("profile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(USER_NAME, name.getText().toString());
        editor.putString(USER_COLOR, favColor.getText().toString());
        editor.putString(USER_SEASON, favSeason.getText().toString());

        if (view.getId() == R.id.yes) {
            statusLoTR = "Watched";
        } else if (view.getId() == R.id.no){
            statusLoTR = "Did not watch";
        }
        editor.putString(USER_LOTR, statusLoTR);
        editor.apply();
    }
}