package com.tusueldo.comodin.model;


import android.os.Parcel;

public class UserIndependiente extends User {
    private String nombresyapellidos;
    private boolean gender;
    private String fecha_nac;
    private String ruc;
    private boolean validate_ruc;
    private String direccion;
    private String nombre_comercial;

    public UserIndependiente() {

    }

    public UserIndependiente(String ubigeo_id, String email, String celular, String password, boolean news, String nombresyapellidos, boolean gender, String fecha_nac, String ruc, boolean validate_ruc, String direccion) {
        super(ubigeo_id, email, celular, password, 1, news);
        this.nombresyapellidos = nombresyapellidos;
        this.gender = gender;
        this.fecha_nac = fecha_nac;
        this.ruc = ruc;
        this.validate_ruc = validate_ruc;
        this.direccion = direccion;
    }

    public String getNombresyapellidos() {
        return nombresyapellidos;
    }

    public void setNombresyapellidos(String nombresyapellidos) {
        this.nombresyapellidos = nombresyapellidos;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
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

    public String getNombre_comercial() {
        return nombre_comercial;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombre_comercial = nombre_comercial;
    }

    @Override
    public String toString() {
        return "UserIndependiente{" + super.toString() +
                "nombresyapellidos='" + nombresyapellidos + '\'' +
                ", gender=" + gender +
                ", fecha_nac='" + fecha_nac + '\'' +
                ", ruc='" + ruc + '\'' +
                ", validate_ruc=" + validate_ruc +
                ", direccion='" + direccion + '\'' +
                '}';
    }

}
