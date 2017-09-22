package com.tusueldo.comodin.utils;

import android.util.Base64;
import com.google.gson.Gson;
import com.tusueldo.comodin.model.Payload;
import com.tusueldo.comodin.model.UserJson;

import java.io.UnsupportedEncodingException;

 class ComodinJWTUtils {

    private String header;
    private String body;
    private String signature;
    private Payload payload;

     ComodinJWTUtils(String token) {
        try {
            String[] split = token.split("\\.");
            this.header = getJson(split[0]);
            this.body = getJson(split[1]);
            this.signature = getJson(split[2]);
            Gson gson = new Gson();
            this.payload = gson.fromJson(body, Payload.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public Payload getPaylodad() {
        return payload;
    }

     UserJson getUser() {
        return payload.getUserJson();
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBody() {
        return body;
    }
}