
package com.tusueldo.comodin.connections.api.login;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ComodinLoginRequest implements Parcelable {

    @SerializedName("type_date_login")
    @Expose
    private String typeDateLogin;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("celular")
    @Expose
    private String celular;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("password")
    @Expose
    private String password;

    /**
     * No args constructor for use in serialization
     */
    public ComodinLoginRequest() {
    }

    /**
     * @param id
     * @param email
     * @param typeDateLogin
     * @param password
     * @param celular
     */
    public ComodinLoginRequest(String typeDateLogin, String email, String celular, String id, String password) {
        super();
        this.typeDateLogin = typeDateLogin;
        this.email = email;
        this.celular = celular;
        this.id = id;
        this.password = password;
    }

    public ComodinLoginRequest(String typeDateLogin, String email, String password) {
        this.typeDateLogin = typeDateLogin;
        this.email = email;
        this.password = password;
    }

    public String getTypeDateLogin() {
        return typeDateLogin;
    }

    public void setTypeDateLogin(String typeDateLogin) {
        this.typeDateLogin = typeDateLogin;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.typeDateLogin);
        dest.writeString(this.email);
        dest.writeString(this.password);
    }

    protected ComodinLoginRequest(Parcel in) {
        this.typeDateLogin = in.readString();
        this.email = in.readString();
        this.password = in.readString();
    }

    public static final Parcelable.Creator<ComodinLoginRequest> CREATOR = new Parcelable.Creator<ComodinLoginRequest>() {
        @Override
        public ComodinLoginRequest createFromParcel(Parcel source) {
            return new ComodinLoginRequest(source);
        }

        @Override
        public ComodinLoginRequest[] newArray(int size) {
            return new ComodinLoginRequest[size];
        }
    };
}