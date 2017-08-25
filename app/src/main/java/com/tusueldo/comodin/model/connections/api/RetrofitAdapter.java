package com.tusueldo.comodin.model.connections.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAdapter {

    private Retrofit retrofit;

    public RetrofitAdapter() {

    }

    public Retrofit getAdapater() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://comodinapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }
}
