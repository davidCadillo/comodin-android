package com.tusueldo.comodin.model.connections.facebook;

public class UserFacebook {

    private String id;
    private String name;
    private String gender;
    private String email;
    private String birthay;

    public UserFacebook() {

    }

    public UserFacebook(String id, String name, String gender, String email, String birthay) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.birthay = birthay;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthay() {
        return birthay;
    }

    public void setBirthay(String birthay) {
        this.birthay = birthay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
