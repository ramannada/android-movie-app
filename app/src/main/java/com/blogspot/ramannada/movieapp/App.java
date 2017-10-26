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

//        ActiveAndroid.beginTransaction();
//        try {
//            for (int i = 0; i < 100; i++) {
//                Item item = new ClipData.Item();
//                item.name = "Example " + i;
//                item.save();
//            }
//            ActiveAndroid.setTransactionSuccessful();
//        }
//        finally {
//            ActiveAndroid.endTransaction();
//        }
    }
}
