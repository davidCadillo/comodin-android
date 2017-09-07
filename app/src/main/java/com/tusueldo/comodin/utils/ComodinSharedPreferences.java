package com.tusueldo.comodin.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by USUARIO on 30/08/2017.
 */

public class ComodinSharedPreferences {

    private Activity activity;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public ComodinSharedPreferences(Activity activity) {
        this.activity = activity;
        sharedPreferences = this.activity.getSharedPreferences("login", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void write(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void write(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void write(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void write(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
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
        editor.remove(key).commit();
    }


    public void clear() {
        editor.clear().commit();
    }
}
