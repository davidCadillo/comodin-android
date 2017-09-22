
package com.tusueldo.comodin.connections.api.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

 class Data {

    @SerializedName("ruc")
    @Expose
    private List<String> ruc = null;
    @SerializedName("email")
    @Expose
    private List<String> email = null;
    @SerializedName("celular")
    @Expose
    private List<String> celular = null;

    public Data() {
    }


    public Data(List<String> ruc, List<String> email, List<String> celular) {
        super();
        this.ruc = ruc;
        this.email = email;
        this.celular = celular;
    }

    public List<String> getRuc() {
        return ruc;
    }

    public void setRuc(List<String> ruc) {
        this.ruc = ruc;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getCelular() {
        return celular;
    }

    public void setCelular(List<String> celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "Data{" +
                "ruc=" + ruc +
                ", email=" + email +
                ", celular=" + celular +
                '}';
    }
}
