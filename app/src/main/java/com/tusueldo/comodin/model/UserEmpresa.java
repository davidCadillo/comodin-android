package com.tusueldo.comodin.model;


import android.os.Parcel;

public class UserEmpresa extends User {

    private String ruc;
    private boolean validate_ruc;
    private String razon_social;
    private String direccion;

    public UserEmpresa() {

    }

    public UserEmpresa(String ubigeo_id, String email, String celular, String password, boolean news, String ruc, boolean validate_ruc, String razon_social, String direccion) {
        super(ubigeo_id, email, celular, password, 2, news);
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.ruc);
        dest.writeByte(this.validate_ruc ? (byte) 1 : (byte) 0);
        dest.writeString(this.razon_social);
        dest.writeString(this.direccion);
    }

    protected UserEmpresa(Parcel in) {
        super(in);
        this.ruc = in.readString();
        this.validate_ruc = in.readByte() != 0;
        this.razon_social = in.readString();
        this.direccion = in.readString();
    }

    public static final Creator<UserEmpresa> CREATOR = new Creator<UserEmpresa>() {
        @Override
        public UserEmpresa createFromParcel(Parcel source) {
            return new UserEmpresa(source);
        }

        @Override
        public UserEmpresa[] newArray(int size) {
            return new UserEmpresa[size];
        }
    };
}
