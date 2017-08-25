package com.tusueldo.comodin.model;


public class UserIndependienteRUC extends UserIndependiente {

    private String ruc;
    private boolean validate_ruc;
    private String direccion;

    public UserIndependienteRUC() {

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "UserIndependienteRUC{" + super.toString() +
                "ruc='" + ruc + '\'' +
                ", validate_ruc=" + validate_ruc +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
