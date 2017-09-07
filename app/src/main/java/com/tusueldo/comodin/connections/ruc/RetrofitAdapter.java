package com.tusueldo.comodin.connections.ruc;

import android.content.Context;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author David Cadillo Blas
 */

public class RetrofitAdapter {

    private Retrofit retrofit;
    public RetrofitAdapter() {

    }

    public Retrofit getAdapater(String baseURL, Context context) {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ConnectivityInterceptor(context))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }
}
