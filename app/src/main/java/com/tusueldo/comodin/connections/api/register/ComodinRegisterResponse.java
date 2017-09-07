
package com.tusueldo.comodin.connections.api.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComodinRegisterResponse {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("code")
    @Expose
    private int code;

    /**
     * No args constructor for use in serialization
     */
    public ComodinRegisterResponse() {
    }

    public ComodinRegisterResponse(Data data, int code) {
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
        return "ComodinRegisterResponse{" +
                "data=" + data +
                ", code=" + code +
                '}';
    }
}
