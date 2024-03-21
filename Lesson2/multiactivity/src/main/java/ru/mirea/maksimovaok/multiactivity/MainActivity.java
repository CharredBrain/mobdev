package ru.mirea.maksimovaok.multiactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected String input;
    public void onClickNewActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        EditText editText = (EditText)findViewById(R.id.editText);
        input = editText.getText().toString();
        intent.putExtra("key", input);
        startActivity(intent);
    }

}