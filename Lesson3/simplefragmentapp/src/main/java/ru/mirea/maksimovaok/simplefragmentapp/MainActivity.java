package ru.mirea.maksimovaok.simplefragmentapp;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    Fragment fragment1, fragment2;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragment1 = new BlankFragment1();
        fragment2 = new BlankFragment2();
    }
    public void onClick(View view) {
        fragmentManager = getSupportFragmentManager();
        int id = view.getId();
        if (id == R.id.btnFragment1){
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment1).commit();
        } else if (id == R.id.btnFragment2) {
            fragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment2).commit();
        }
    }
}