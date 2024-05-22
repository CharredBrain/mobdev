package ru.mirea.maksimovaok.yandexdriver;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        String MAPKIT_API_KEY = "8272eeb4-6a94-49e9-a85b-842658b0eb15";
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
    }
}