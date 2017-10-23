package com.blogspot.ramannada.movieapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ramannada on 10/21/2017.
 */

public class SharedData {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editorSharedPreferences;
    private static SharedData sharedData;
    private static final String SHARED_DATA_NAME = "com.blogspot.ramannada.movieapp.shareddata";
    private static final String LOGIN_STORAGE = "login";
    private static final String USER_STORAGE_USERNAME = "user.username";
    private static final String USER_STORAGE_EMAIL = "user.email";
    private static final String USER_STORAGE_PASSWORD = "user.password";
    private static final String USER_STORAGE_GENDER = "user.gender";

    public SharedData(Context c) {
        this.sharedPreferences = c.getSharedPreferences(SHARED_DATA_NAME, Context.MODE_PRIVATE);
        this.editorSharedPreferences = sharedPreferences.edit();
    }

    public static void init(Context c) {
        sharedData = new SharedData(c);
    }

    public synchronized static SharedData getSharedData() {
        return sharedData;
    }

    public void saveLoginStatus(Boolean loginStatus) {
        editorSharedPreferences.putBoolean(LOGIN_STORAGE, loginStatus);
        editorSharedPreferences.commit();
    }

    public Boolean getLoginStatus(){
        return sharedPreferences.getBoolean(LOGIN_STORAGE, false);
    }

    public void clearLoginStatus() {
        editorSharedPreferences.remove(LOGIN_STORAGE);
        editorSharedPreferences.commit();
    }

    public void saveUserStorageUsername(String username) {
        editorSharedPreferences.putString(USER_STORAGE_USERNAME, username);
        editorSharedPreferences.commit();
    }

    public void saveUserStorageEmail(String email) {
        editorSharedPreferences.putString(USER_STORAGE_EMAIL, email);
        editorSharedPreferences.commit();
    }

    public void saveUserStoragePassword(String password) {
        editorSharedPreferences.putString(USER_STORAGE_PASSWORD, password);
        editorSharedPreferences.commit();
    }

    public void saveUserStorageGender(String gender) {
        editorSharedPreferences.putString(USER_STORAGE_GENDER, gender);
        editorSharedPreferences.commit();
    }

    public String getUserStorageUsername() {
        return sharedPreferences.getString(USER_STORAGE_USERNAME, "");
    }

    public String getUserStorageEmail() {
        return sharedPreferences.getString(USER_STORAGE_EMAIL, "");
    }

    public String getUserStoragePassword() {
        return sharedPreferences.getString(USER_STORAGE_PASSWORD, "");
    }

    public String getUserStorageGender() {
        return sharedPreferences.getString(USER_STORAGE_GENDER, "");
    }
}
