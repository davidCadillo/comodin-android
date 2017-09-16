
package com.tusueldo.comodin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("0")
    @Expose
    private UserJson userJson;
    @SerializedName("sub")
    @Expose
    private int sub;
    @SerializedName("iss")
    @Expose
    private String iss;
    @SerializedName("iat")
    @Expose
    private int iat;
    @SerializedName("exp")
    @Expose
    private int exp;
    @SerializedName("nbf")
    @Expose
    private int nbf;
    @SerializedName("jti")
    @Expose
    private String jti;

    /**
     * No args constructor for use in serialization
     */
    public Payload() {
    }

    /**
     * @param exp
     * @param sub
     * @param nbf
     * @param iss
     * @param jti
     * @param iat
     * @param userJson
     */
    public Payload(UserJson userJson, int sub, String iss, int iat, int exp, int nbf, String jti) {
        super();
        this.userJson = userJson;
        this.sub = sub;
        this.iss = iss;
        this.iat = iat;
        this.exp = exp;
        this.nbf = nbf;
        this.jti = jti;
    }

    public UserJson getUserJson() {
        return userJson;
    }

    public void setUserJson(UserJson userJson) {
        this.userJson = userJson;
    }

    public int getSub() {
        return sub;
    }

    public void setSub(int sub) {
        this.sub = sub;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public int getIat() {
        return iat;
    }

    public void setIat(int iat) {
        this.iat = iat;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getNbf() {
        return nbf;
    }

    public void setNbf(int nbf) {
        this.nbf = nbf;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }


}



