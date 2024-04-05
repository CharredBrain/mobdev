package ru.mirea.maksimovaok.intentapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        String dateString = intent.getStringExtra("date");
        Integer number = (int)Math.pow(13, 2);
        String result = "КВАДРАТ ЗНАЧЕНИЯ МОЕГО НОМЕРА ПО СПИСКУ В ГРУППЕ СОСТАВЛЯЕТ " + number + " , а текущее время ";
        textView.setText(result + dateString);

    }
}