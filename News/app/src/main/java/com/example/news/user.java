package com.example.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class user {
    @SerializedName("fname")
    @Expose
    private String fname;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @SerializedName("mobileno")
    @Expose
    private String mobileno;

    @SerializedName("email")
    @Expose
    private String email;
}
