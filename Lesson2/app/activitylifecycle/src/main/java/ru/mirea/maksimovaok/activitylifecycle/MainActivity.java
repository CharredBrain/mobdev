package ru.mirea.maksimovaok.activitylifecycle;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    private String TAG = "Oh my God, look at this tag!";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.indicator);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG, "onStart()");
        editText.setText("OnStart");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState()");
        editText.setText("OnSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState()");
        editText.setText("OnRestoreInstanceState");
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        Log.i(TAG, "onPostCreate()");
        editText.setText("OnPostCreate");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG, "onResume()");
        editText.setText("OnResume");
    }

    @Override
    protected void onPostResume(){
        super.onPostResume();
        Log.i(TAG, "onPostResume()");
        editText.setText("OnPostResume");
    }

    @Override
    public void onAttachedToWindow(){
        super.onAttachedToWindow();
        Log.i(TAG, "onAttachedToWindow()");
        editText.setText("OnAttachedToWindow");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG, "onPause()");
        editText.setText("OnPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG, "onStop()");
        editText.setText("OnStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
        editText.setText("OnDestroy");
    }

    @Override
    public void onDetachedFromWindow(){
        super.onDetachedFromWindow();
        Log.i(TAG, "onDetachedFromWindow()");
        editText.setText("OnDetachedFromWindow");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.i(TAG, "onRestart()");
        editText.setText("OnRestart");
    }

}