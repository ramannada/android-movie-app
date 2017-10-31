package com.blogspot.ramannada.movieapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.container_fragment);

        if (SharedData.getSharedData().getLoginStatus()) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content, new MainFragment())
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content, new LoginFragment())
                    .commit();
        }
    }

    public static void hideKeyboard(Context c) {
        View v = ((Activity) c).getCurrentFocus();
        InputMethodManager im = (InputMethodManager)
                c.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (v != null) im.hideSoftInputFromWindow(v.getWindowToken(),0);
    }
}
