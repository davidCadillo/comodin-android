
package com.tusueldo.comodin.connections.api.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComodinLoginResponse {

    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     */
    public ComodinLoginResponse() {
    }

    /**
     * @param message
     * @param code
     */
    public ComodinLoginResponse(int code, String message) {
        super();
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