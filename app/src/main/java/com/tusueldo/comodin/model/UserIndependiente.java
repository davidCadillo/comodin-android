package com.tusueldo.comodin.model;

public class UserIndependiente extends User {

    private String nombresyapellidos;
    private boolean gender;
    private String fecha_nac;

    public UserIndependiente() {

    }

    public UserIndependiente(String nombresyapellidos, boolean gender, String fecha_nac) {
        this.nombresyapellidos = nombresyapellidos;
        this.gender = gender;
        this.fecha_nac = fecha_nac;
    }

    public UserIndependiente(String ubigeo_id, String email, String celular, String password, String nombresyapellidos, boolean gender, String fecha_nac) {
        super(ubigeo_id, email, celular, password, 1);
        this.nombresyapellidos = nombresyapellidos;
        this.gender = gender;
        this.fecha_nac = fecha_nac;
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

    @Override
    public String toString() {
        return "UserIndependiente{" + super.toString() +
                "nombresyapellidos='" + nombresyapellidos + '\'' +
                ", gender=" + gender +
                ", fecha_nac='" + fecha_nac + '\'' +
                '}';
    }
}
