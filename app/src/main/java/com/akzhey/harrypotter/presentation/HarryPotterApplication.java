package com.akzhey.harrypotter.presentation;

import android.app.Application;
import android.util.Log;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class HarryPotterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Application", "Launch");
    }
}
