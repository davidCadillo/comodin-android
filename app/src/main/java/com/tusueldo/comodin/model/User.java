
package com.tusueldo.comodin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("tipo_usuario_id")
    @Expose
    private int tipoUsuarioId;
    @SerializedName("nombresyapellidos")
    @Expose
    private String nombresyapellidos;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("celular")
    @Expose
    private String celular;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("ubigeo_id")
    @Expose
    private String ubigeoId;
    @SerializedName("fecha_nac")
    @Expose
    private String fechaNac;
    @SerializedName("news")
    @Expose
    private boolean news;
    @SerializedName("ruc")
    @Expose
    private String ruc;
    @SerializedName("validate_ruc")
    @Expose
    private boolean validateRuc;
    @SerializedName("gender")
    @Expose
    private boolean gender;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("nombre_comercial")
    @Expose
    private String nombreComercial;
    @SerializedName("razon_social")
    @Expose
    private String razonSocial;

    /**
     * No args constructor for use in serialization
     */
    public User() {
    }

    /**
     * @param tipoUsuarioId
     * @param nombreComercial
     * @param direccion
     * @param validateRuc
     * @param razonSocial
     * @param fechaNac
     * @param news
     * @param ruc
     * @param ubigeoId
     * @param password
     * @param nombresyapellidos
     * @param email
     * @param gender
     * @param celular
     */
    public User(int tipoUsuarioId, String nombresyapellidos, String email, String celular, String password, String ubigeoId, String fechaNac, boolean news, String ruc, boolean validateRuc, boolean gender, String direccion, String nombreComercial, String razonSocial) {
        super();
        this.tipoUsuarioId = tipoUsuarioId;
        this.nombresyapellidos = nombresyapellidos;
        this.email = email;
        this.celular = celular;
        this.password = password;
        this.ubigeoId = ubigeoId;
        this.fechaNac = fechaNac;
        this.news = news;
        this.ruc = ruc;
        this.validateRuc = validateRuc;
        this.gender = gender;
        this.direccion = direccion;
        this.nombreComercial = nombreComercial;
        this.razonSocial = razonSocial;
    }

    public int getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    public void setTipoUsuarioId(int tipoUsuarioId) {
        this.tipoUsuarioId = tipoUsuarioId;
    }

    public String getNombresyapellidos() {
        return nombresyapellidos;
    }

    public void setNombresyapellidos(String nombresyapellidos) {
        this.nombresyapellidos = nombresyapellidos;
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

    public String getUbigeoId() {
        return ubigeoId;
    }

    public void setUbigeoId(String ubigeoId) {
        this.ubigeoId = ubigeoId;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public boolean isNews() {
        return news;
    }

    public void setNews(boolean news) {
        this.news = news;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public boolean isValidateRuc() {
        return validateRuc;
    }

    public void setValidateRuc(boolean validateRuc) {
        this.validateRuc = validateRuc;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

}