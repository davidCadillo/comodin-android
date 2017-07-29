package com.tusueldo.comodin.connections.ruc;

import com.tusueldo.comodin.utils.ComodinValues;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author David Cadillo Blas
 */

public class RetrofitAdapter {

    Retrofit retrofit;

    public RetrofitAdapter() {

    }

    public Retrofit getAdapater() {

        retrofit = new Retrofit.Builder()
                .baseUrl(ComodinValues.BASE_URL_RUC)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }
}
