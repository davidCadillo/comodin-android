package com.tusueldo.comodin.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserJson implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("nombresyapellidos")
    @Expose
    private String nombresyapellidos;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("fecha_nac")
    @Expose
    private String fechaNac;
    @SerializedName("dni")
    @Expose
    private String dni;
    @SerializedName("direccion_user")
    @Expose
    private String direccionUser;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("celular")
    @Expose
    private String celular;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("ubigeo_id")
    @Expose
    private String ubigeoId;
    @SerializedName("situacion_usuario_id")
    @Expose
    private int situacionUsuarioId;
    @SerializedName("tipo_usuario_id")
    @Expose
    private int tipoUsuarioId;
    @SerializedName("tipo_formalidad_id")
    @Expose
    private int tipoFormalidadId;
    @SerializedName("gender")
    @Expose
    private int gender;
    @SerializedName("msj_welcome")
    @Expose
    private int msjWelcome;
    @SerializedName("news")
    @Expose
    private int news;
    @SerializedName("fb_id")
    @Expose
    private String fbId;
    @SerializedName("g_id")
    @Expose
    private String gId;
    @SerializedName("validate_ruc")
    @Expose
    private int validateRuc;
    @SerializedName("company_id")
    @Expose
    private int companyId;
    @SerializedName("ruc")
    @Expose
    private String ruc;
    @SerializedName("nombre_comercial")
    @Expose
    private String nombreComercial;
    @SerializedName("razon_social")
    @Expose
    private String razonSocial;
    @SerializedName("direccion_company")
    @Expose
    private String direccionCompany;
    @SerializedName("num_acc")
    @Expose
    private int numAcc;
    @SerializedName("rubro_id")
    @Expose
    private int rubroId;

    /**
     * No args constructor for use in serialization
     */
    public UserJson() {
    }

    /**
     * @param imageUrl
     * @param ruc
     * @param id
     * @param tipoFormalidadId
     * @param gId
     * @param rubroId
     * @param nombresyapellidos
     * @param direccionCompany
     * @param situacionUsuarioId
     * @param gender
     * @param dni
     * @param tipoUsuarioId
     * @param fbId
     * @param nombreComercial
     * @param validateRuc
     * @param fechaNac
     * @param news
     * @param razonSocial
     * @param descripcion
     * @param ubigeoId
     * @param direccionUser
     * @param email
     * @param companyId
     * @param numAcc
     * @param msjWelcome
     * @param celular
     */
    public UserJson(int id, String nombresyapellidos, String email, String fechaNac, String dni, String direccionUser, String descripcion, String celular, String imageUrl, String ubigeoId, int situacionUsuarioId, int tipoUsuarioId, int tipoFormalidadId, int gender, int msjWelcome, int news, String fbId, String gId, int validateRuc, int companyId, String ruc, String nombreComercial, String razonSocial, String direccionCompany, int numAcc, int rubroId) {
        super();
        this.id = id;
        this.nombresyapellidos = nombresyapellidos;
        this.email = email;
        this.fechaNac = fechaNac;
        this.dni = dni;
        this.direccionUser = direccionUser;
        this.descripcion = descripcion;
        this.celular = celular;
        this.imageUrl = imageUrl;
        this.ubigeoId = ubigeoId;
        this.situacionUsuarioId = situacionUsuarioId;
        this.tipoUsuarioId = tipoUsuarioId;
        this.tipoFormalidadId = tipoFormalidadId;
        this.gender = gender;
        this.msjWelcome = msjWelcome;
        this.news = news;
        this.fbId = fbId;
        this.gId = gId;
        this.validateRuc = validateRuc;
        this.companyId = companyId;
        this.ruc = ruc;
        this.nombreComercial = nombreComercial;
        this.razonSocial = razonSocial;
        this.direccionCompany = direccionCompany;
        this.numAcc = numAcc;
        this.rubroId = rubroId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccionUser() {
        return direccionUser;
    }

    public void setDireccionUser(String direccionUser) {
        this.direccionUser = direccionUser;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUbigeoId() {
        return ubigeoId;
    }

    public void setUbigeoId(String ubigeoId) {
        this.ubigeoId = ubigeoId;
    }

    public int getSituacionUsuarioId() {
        return situacionUsuarioId;
    }

    public void setSituacionUsuarioId(int situacionUsuarioId) {
        this.situacionUsuarioId = situacionUsuarioId;
    }

    public int getTipoUsuarioId() {
        return tipoUsuarioId;
    }

    public void setTipoUsuarioId(int tipoUsuarioId) {
        this.tipoUsuarioId = tipoUsuarioId;
    }

    public int getTipoFormalidadId() {
        return tipoFormalidadId;
    }

    public void setTipoFormalidadId(int tipoFormalidadId) {
        this.tipoFormalidadId = tipoFormalidadId;
    }

    public int isGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int isMsjWelcome() {
        return msjWelcome;
    }

    public void setMsjWelcome(int msjWelcome) {
        this.msjWelcome = msjWelcome;
    }

    public int isNews() {
        return news;
    }

    public void setNews(int news) {
        this.news = news;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getGId() {
        return gId;
    }

    public void setGId(String gId) {
        this.gId = gId;
    }

    public int isValidateRuc() {
        return validateRuc;
    }

    public void setValidateRuc(int validateRuc) {
        this.validateRuc = validateRuc;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
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

    public String getDireccionCompany() {
        return direccionCompany;
    }

    public void setDireccionCompany(String direccionCompany) {
        this.direccionCompany = direccionCompany;
    }

    public int getNumAcc() {
        return numAcc;
    }

    public void setNumAcc(int numAcc) {
        this.numAcc = numAcc;
    }

    public int getRubroId() {
        return rubroId;
    }

    public void setRubroId(int rubroId) {
        this.rubroId = rubroId;
    }

    @Override
    public String toString() {
        return "UserJson{" +
                "id=" + id +
                ", nombresyapellidos='" + nombresyapellidos + '\'' +
                ", email='" + email + '\'' +
                ", fechaNac='" + fechaNac + '\'' +
                ", dni='" + dni + '\'' +
                ", direccionUser='" + direccionUser + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", celular='" + celular + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", ubigeoId='" + ubigeoId + '\'' +
                ", situacionUsuarioId=" + situacionUsuarioId +
                ", tipoUsuarioId=" + tipoUsuarioId +
                ", tipoFormalidadId=" + tipoFormalidadId +
                ", gender=" + gender +
                ", msjWelcome=" + msjWelcome +
                ", news=" + news +
                ", fbId='" + fbId + '\'' +
                ", gId='" + gId + '\'' +
                ", validateRuc=" + validateRuc +
                ", companyId=" + companyId +
                ", ruc='" + ruc + '\'' +
                ", nombreComercial='" + nombreComercial + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", direccionCompany='" + direccionCompany + '\'' +
                ", numAcc=" + numAcc +
                ", rubroId=" + rubroId +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombresyapellidos);
        dest.writeString(this.email);
        dest.writeString(this.fechaNac);
        dest.writeString(this.dni);
        dest.writeString(this.direccionUser);
        dest.writeString(this.descripcion);
        dest.writeString(this.celular);
        dest.writeString(this.imageUrl);
        dest.writeString(this.ubigeoId);
        dest.writeInt(this.situacionUsuarioId);
        dest.writeInt(this.tipoUsuarioId);
        dest.writeInt(this.tipoFormalidadId);
        dest.writeInt(this.gender);
        dest.writeInt(this.msjWelcome);
        dest.writeInt(this.news);
        dest.writeString(this.fbId);
        dest.writeString(this.gId);
        dest.writeInt(this.validateRuc);
        dest.writeInt(this.companyId);
        dest.writeString(this.ruc);
        dest.writeString(this.nombreComercial);
        dest.writeString(this.razonSocial);
        dest.writeString(this.direccionCompany);
        dest.writeInt(this.numAcc);
        dest.writeInt(this.rubroId);
    }

    protected UserJson(Parcel in) {
        this.id = in.readInt();
        this.nombresyapellidos = in.readString();
        this.email = in.readString();
        this.fechaNac = in.readString();
        this.dni = in.readString();
        this.direccionUser = in.readString();
        this.descripcion = in.readString();
        this.celular = in.readString();
        this.imageUrl = in.readString();
        this.ubigeoId = in.readString();
        this.situacionUsuarioId = in.readInt();
        this.tipoUsuarioId = in.readInt();
        this.tipoFormalidadId = in.readInt();
        this.gender = in.readInt();
        this.msjWelcome = in.readInt();
        this.news = in.readInt();
        this.fbId = in.readString();
        this.gId = in.readString();
        this.validateRuc = in.readInt();
        this.companyId = in.readInt();
        this.ruc = in.readString();
        this.nombreComercial = in.readString();
        this.razonSocial = in.readString();
        this.direccionCompany = in.readString();
        this.numAcc = in.readInt();
        this.rubroId = in.readInt();
    }

    public static final Parcelable.Creator<UserJson> CREATOR = new Parcelable.Creator<UserJson>() {
        @Override
        public UserJson createFromParcel(Parcel source) {
            return new UserJson(source);
        }

        @Override
        public UserJson[] newArray(int size) {
            return new UserJson[size];
        }
    };
}
