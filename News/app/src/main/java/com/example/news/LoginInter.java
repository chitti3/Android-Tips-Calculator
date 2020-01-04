package com.example.news;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginInter {

    @FormUrlEncoded
    @POST("/Senthil/signup.php")
    Call<ResponseBody> saveNote(
            @Field("fname") String fname,
            @Field("lname") String lname,
            @Field("mobileno") String mobileno,
            @Field("dob") String dob,
            @Field("district") String district,
            @Field("sub") String sub,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("/Senthil/Login.php")
    Call<Spojo> login( @Field("email") String email,
                       @Field("password") String password

    );
}
