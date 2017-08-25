
package com.tusueldo.comodin.model.connections.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("code")
    @Expose
    private int code;

    /**
     * No args constructor for use in serialization
     */
    public ErrorResponse() {
    }

    public ErrorResponse(Data data, int code) {
        super();
        this.data = data;
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "data=" + data +
                ", code=" + code +
                '}';
    }
}
