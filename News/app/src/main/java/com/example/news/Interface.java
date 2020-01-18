package com.example.news;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Interface {

    @GET("/Senthil/District.php")
    Call<List<Spojo>> spinner();

    @FormUrlEncoded
    @POST("/Senthil/signup.php")
    Call<Spojo> Signup(
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("mobileno") String mobileno,
            @Field("dob")   String dob,
            @Field("district") String district,
            @Field("sub") String sub,
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("/Senthil/Sub.php")
    Call<List<Spojo>> ssub();


    @GET("Senthil/tisub.php")
    Call<List<Spojo>> tisub();

    @GET("Senthil/csub.php")
    Call<List<Spojo>> csub();

    @GET("Senthil/coisub.php")
    Call<List<Spojo>> coisub();


    @FormUrlEncoded
    @POST("/Senthil/user.php")
    Call<user> user(
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("/Senthil/Login.php")
    Call<Spojo> Login(
            @Field("email") String email,
            @Field("password") String password
    );
}
