package com.tusueldo.comodin.model;


public class UserEmpresa extends User {

    private String ruc;
    private boolean validate_ruc;
    private String razon_social;
    private String direccion;

    public UserEmpresa() {

    }

    public UserEmpresa(String ruc, boolean validate_ruc, String razon_social, String direccion) {
        this.ruc = ruc;
        this.validate_ruc = validate_ruc;
        this.razon_social = razon_social;
        this.direccion = direccion;
    }

    public UserEmpresa(String ubigeo_id, String email, String celular, String password, String ruc, boolean validate_ruc, String razon_social, String direccion) {
        super(ubigeo_id, email, celular, password, 2);
        this.ruc = ruc;
        this.validate_ruc = validate_ruc;
        this.razon_social = razon_social;
        this.direccion = direccion;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public boolean isValidate_ruc() {
        return validate_ruc;
    }

    public void setValidate_ruc(boolean validate_ruc) {
        this.validate_ruc = validate_ruc;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "UserEmpresa{" + super.toString() +
                "ruc='" + ruc + '\'' +
                ", validate_ruc=" + validate_ruc +
                ", razon_social='" + razon_social + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
