package com.tusueldo.comodin.connections.api.register;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.ResponseBody;
import retrofit2.Response;

import java.io.IOException;


public class ComodinRegisterErrors {

    private ComodinRegisterResponse comodinRegisterResponse;

    public ComodinRegisterErrors(Response<ResponseBody> response) {
        try {
            Gson errorJson = new GsonBuilder().create();
            ResponseBody responseBody = response.errorBody();
            if (responseBody != null)
                this.comodinRegisterResponse = errorJson.fromJson(responseBody.string(), ComodinRegisterResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isErrorCorreo() {
        return comodinRegisterResponse.getData().getEmail() != null;
    }

    public boolean isErrorRuc() {
        return comodinRegisterResponse.getData().getRuc() != null;
    }

    public boolean isErrorCelular() {
        return comodinRegisterResponse.getData().getCelular() != null;
    }


}
