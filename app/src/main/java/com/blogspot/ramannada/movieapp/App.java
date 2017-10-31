package com.blogspot.ramannada.movieapp;

import android.content.ClipData;

import com.activeandroid.ActiveAndroid;

/**
 * Created by ramannada on 10/23/2017.
 */

public class App extends com.activeandroid.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        SharedData.init(this);
    }
}
