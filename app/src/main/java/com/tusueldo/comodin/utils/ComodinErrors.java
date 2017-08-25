package com.tusueldo.comodin.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tusueldo.comodin.model.connections.api.ErrorResponse;
import okhttp3.ResponseBody;
import retrofit2.Response;

import java.io.IOException;


public class ComodinErrors {

    private ErrorResponse errorResponse;

    public ComodinErrors(Response<ResponseBody> response) {
        try {
            Gson errorJson = new GsonBuilder().create();
            ResponseBody responseBody = response.errorBody();
            if (responseBody != null)
                this.errorResponse = errorJson.fromJson(responseBody.string(), ErrorResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isErrorCorreo() {
        return errorResponse.getData().getEmail() != null;
    }

    public boolean isErrorRuc() {
        return errorResponse.getData().getRuc() != null;
    }

    public boolean isErrorCelular() {
        return errorResponse.getData().getCelular() != null;
    }


}
