package com.example.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Spojo {
    @SerializedName("Dist")
    @Expose
    private String dist;

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }
    @SerializedName("Sub")
    @Expose
    private String sub;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
    @SerializedName("response")
    @Expose
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    //tirsub
    @SerializedName("sti")
    @Expose
    private String sti;

    public String getSti() {
        return sti;
    }

    public void setSti(String sti) {
        this.sti = sti;
    }
    //csub
    @SerializedName("csub")
    @Expose
    private String csub;

    public String getCsub() {
        return csub;
    }

    public void setCsub(String csub) {
        this.csub = csub;
    }
    //coisub
    @SerializedName("coisub")
    @Expose
    private String coisub;

    public String getCoisub() {
        return coisub;
    }

    public void setCoisub(String coisub) {
        this.coisub = coisub;
    }

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("mobileno")
    @Expose
    private String mobileno;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
