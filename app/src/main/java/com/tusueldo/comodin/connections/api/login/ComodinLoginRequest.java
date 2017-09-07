package com.tusueldo.comodin.connections.api.login;


public class ComodinLoginRequest {

    private String type_date_login;
    private String email;
    private String password;
    private String celular;
    private String id;

    public ComodinLoginRequest() {

    }


    public ComodinLoginRequest(String type_date_login, String email, String password, String celular, String id) {
        this.type_date_login = type_date_login;
        this.email = email;
        this.password = password;
        this.celular = celular;
        this.id = id;
    }

    public String getType_date_login() {
        return type_date_login;
    }

    public void setType_date_login(String type_date_login) {
        this.type_date_login = type_date_login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
