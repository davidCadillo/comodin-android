package com.tusueldo.comodin.model;

import com.google.gson.annotations.SerializedName;

public abstract class User {

    @SerializedName("ubigeo_id")
    private String ubigeo_id;

    @SerializedName("email")
    private String email;

    @SerializedName("celular")
    private String celular;

    @SerializedName("password")
    private String password;

    @SerializedName("tipo_usuario_id")
    private int tipo_usuario_id;

    @SerializedName("news")
    private boolean news;

    User() {

    }

    public User(String ubigeo_id, String email, String celular, String password, int tipo_usuario_id, boolean news) {
        this.ubigeo_id = ubigeo_id;
        this.email = email;
        this.celular = celular;
        this.password = password;
        this.tipo_usuario_id = tipo_usuario_id;
        this.news = news;
    }

    public boolean isNews() {
        return news;
    }

    public void setNews(boolean news) {
        this.news = news;
    }

    public String getUbigeo_id() {
        return ubigeo_id;
    }

    public void setUbigeo_id(String ubigeo_id) {
        this.ubigeo_id = ubigeo_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTipo_usuario_id() {
        return tipo_usuario_id;
    }

    public void setTipo_usuario_id(int tipo_usuario_id) {
        this.tipo_usuario_id = tipo_usuario_id;
    }

    @Override
    public String toString() {
        return
                "ubigeo_id='" + ubigeo_id + '\'' +
                        ", email='" + email + '\'' +
                        ", celular='" + celular + '\'' +
                        ", password='" + password + '\'' +
                        ", tipo_usuario_id=" + tipo_usuario_id;
    }



}


