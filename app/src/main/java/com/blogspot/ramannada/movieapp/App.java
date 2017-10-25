package com.blogspot.ramannada.movieapp;

import android.app.Application;

/**
 * Created by ramannada on 10/23/2017.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        DatabaseHandler.init(this);
        SharedData.init(this);
    }
}
