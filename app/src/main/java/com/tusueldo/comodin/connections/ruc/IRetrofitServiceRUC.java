package com.tusueldo.comodin.connections.ruc;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by USUARIO on 29/07/2017.
 *
 * @author David Cadillo Blas
 */

public interface IRetrofitServiceRUC {

    @Headers("Content-Type:application/json")
    @POST("api/beta/ruc")
    Call<InformationRUC> loadInfoRuc(@Body RequestRUC requestRUC);

}
