package com.tusueldo.comodin.connections.ruc;

/**
 * Created by David Cadillo Blas on 29/07/2017.
 *
 * @author David Cadillo Blas
 */

public class RequestRUC {

    private String token;
    private String ruc;


    public RequestRUC() {

    }

    public RequestRUC(String token, String ruc) {
        this.token = token;
        this.ruc = ruc;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }
}
