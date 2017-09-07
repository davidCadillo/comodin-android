package com.tusueldo.comodin.connections.api.login;

/**
 * Created by USUARIO on 28/08/2017.
 */

public class ComodinLoginResponse {

    private int code;
    private String message;


    public ComodinLoginResponse() {
    }

    public ComodinLoginResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
