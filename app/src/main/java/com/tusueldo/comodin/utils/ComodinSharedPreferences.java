package com.tusueldo.comodin.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ComodinSharedPreferences {

    private Activity activity;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public ComodinSharedPreferences(Activity activity, String name) {
        this.activity = activity;
        sharedPreferences = this.activity.getSharedPreferences(name, Context.MODE_PRIVATE);

    }

    public void write(String key, String value) {
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void write(String key, int value) {
        editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void write(String key, boolean value) {
        editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void write(String key, float value) {
        editor = sharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public String read(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public int read(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }


    public boolean read(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }


    public float read(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    public void remove(String key) {
        editor = sharedPreferences.edit();
        editor.remove(key).apply();
    }


    public void clear() {
        editor.clear().commit();
    }
}
