package com.tusueldo.comodin.connections.ruc;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author David Cadillo Blas
 */

public class RetrofitAdapter {

    Retrofit retrofit;

    public RetrofitAdapter() {

    }

    public Retrofit getAdapater(String baseURL) {

        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }
}
