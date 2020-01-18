package com.example.news;

import com.example.news.Model.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Call<Headlines> getHeadlines(
            @Query("country") String country,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<Headlines> geteeverything(
            @Query("q") String q,
            @Query("apiKey") String apiKey
    );

    @GET("top-headlines")
    Call<Headlines> getBusiness(
            @Query("country") String country,
            @Query("category") String business,
            @Query("apiKey") String apiKey
    );
    @GET("top-headlines")
    Call<Headlines> getSource(
            @Query("sources") String sources,
            @Query("apiKey") String apiKey
    );

    @GET("everything")
    Call<Headlines> getDate(
            @Query("q") String q,
            @Query("from") String from,
            @Query("to") String to,
            @Query("sortBy") String sortBy,
            @Query("apiKey") String apiKey
    );
}
