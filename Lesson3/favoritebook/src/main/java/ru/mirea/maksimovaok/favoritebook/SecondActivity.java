package ru.mirea.maksimovaok.favoritebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView favBook = findViewById(R.id.textView2);
        String bookName = getIntent().getStringExtra(MainActivity.KEY);
        favBook.setText(String.format("Любимая книга разработчика: %s", bookName));
    }

    public void onSend(View view) {
        EditText edit = findViewById(R.id.editTextText);
        String bookName = edit.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(MainActivity.USER_MESSAGE, bookName);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}