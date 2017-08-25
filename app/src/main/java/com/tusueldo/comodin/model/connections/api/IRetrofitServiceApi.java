package com.tusueldo.comodin.model.connections.api;

import com.tusueldo.comodin.model.UserEmpresa;
import com.tusueldo.comodin.model.UserIndependiente;
import com.tusueldo.comodin.model.UserIndependienteRUC;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface IRetrofitServiceApi {

    @Headers("Content-Type:application/json")
    @POST("api/v1/register")
    Call<ResponseBody> regiserUserIndependiente(@Body UserIndependiente userIndependiente);

    @Headers("Content-Type:application/json")
    @POST("api/v1/register")
    Call<ResponseBody> regiserUserIndependienteRuc(@Body UserIndependienteRUC userIndependienteRUC);


    @Headers("Content-Type:application/json")
    @POST("api/v1/register")
    Call<ResponseBody> regiserUserEmpresa(@Body UserEmpresa userEmpresa);
}
