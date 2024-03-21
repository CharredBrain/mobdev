package ru.mirea.maksimovaok.toastapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String text;
    public void Notifier(View view){
        EditText editText = (EditText)findViewById(R.id.editText);
        int c;
        text = editText.getText().toString();
        c = text.length();
        Toast toast = Toast.makeText(getApplicationContext(),
                "СТУДЕНТ 13 ГРУППА 10 СИМВОЛОВ - "+c,
                Toast.LENGTH_LONG);
        toast.show();
    }
}