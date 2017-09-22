package com.tusueldo.comodin.connections.api;

import com.tusueldo.comodin.connections.api.login.ComodinLoginRequest;
import com.tusueldo.comodin.connections.api.login.ComodinLoginResponse;
import com.tusueldo.comodin.model.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface IRetrofitServiceApi {

    @Headers("Content-Type:application/json")
    @POST("api/v1/register")
    Call<ResponseBody> registerUser(@Body User user);


    @Headers("Content-Type:application/json")
    @POST("api/v1/login")
    Call<ComodinLoginResponse> loginUser(@Body ComodinLoginRequest request);
}
