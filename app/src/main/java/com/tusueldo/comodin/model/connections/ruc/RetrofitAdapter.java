package com.tusueldo.comodin.model.connections.ruc;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author David Cadillo Blas
 */

public class RetrofitAdapter {

    private Retrofit retrofit;

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
