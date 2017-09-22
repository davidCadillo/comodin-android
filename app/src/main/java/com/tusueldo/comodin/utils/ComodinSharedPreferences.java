package com.tusueldo.comodin.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.*;

class ComodinSharedPreferences {

    private Activity activity;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    ComodinSharedPreferences(Activity activity, String name) {
        this.activity = activity;
        sharedPreferences = this.activity.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    void write(String key, String value) {
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    void write(String key, int value) {
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

    String read(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    int read(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }


    public boolean read(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }


    public float read(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    void remove(String key) {
        editor = sharedPreferences.edit();
        editor.remove(key).apply();
    }

    public List<String> getAllValuesWhitoutRepeated() {
        Map<String, ?> keys = sharedPreferences.getAll();
        List<String> values;
        Set<String> rawValues = new HashSet<>();
        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            if (!entry.getKey().equals("contador"))
                rawValues.add(entry.getValue().toString());
        }
        values = new ArrayList<>(rawValues);
        return values;
    }

    public void clear() {
        editor.clear().commit();
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
