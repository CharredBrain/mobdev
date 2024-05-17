package ru.mirea.maksimovaok.lesson6;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText groupNumber;
    private EditText numberInGroup;
    private EditText favoriteMovie;
    private final String  GROUP_NUMBER = "GROUP_NUMBER";
    private final String  NUMBER_IN_GROUP = "NUMBER_IN_GROUP";
    private final String  FAVORITE_MOVIE = "FAVORITE_MOVIE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupNumber = findViewById(R.id.editTextText);
        numberInGroup = findViewById(R.id.editTextText2);
        favoriteMovie = findViewById(R.id.editTextText3);
        SharedPreferences shPref = getSharedPreferences("mirea_settings", Context.MODE_PRIVATE);

        groupNumber.setText(shPref.getString(GROUP_NUMBER, ""));
        numberInGroup.setText(shPref.getString(NUMBER_IN_GROUP, ""));
        favoriteMovie.setText(shPref.getString(FAVORITE_MOVIE, ""));
    }

    public void savePreferences(View view){
        SharedPreferences	sharedPref	= getSharedPreferences("mirea_settings",	Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(GROUP_NUMBER, groupNumber.getText().toString());
        editor.putString(NUMBER_IN_GROUP, numberInGroup.getText().toString());
        editor.putString(FAVORITE_MOVIE, favoriteMovie.getText().toString());
        editor.apply();
    }
}