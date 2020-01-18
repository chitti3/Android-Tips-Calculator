package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toast;

import com.example.news.Model.Articles;
import com.example.news.Model.Headlines;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Media extends AppCompatActivity {
RecyclerView recyclerView;
    final String apikey="9c952917eb4c4d20ac26b29cd474bc07";
    String country,business;
List<Articles> articles = new ArrayList<>();
ApiInterface apiInterface ;
SwipeRefreshLayout swipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
        recyclerView = findViewById(R.id.narecy);
        country = "in";
        business="business";
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

        //GridLayoutManager  linearLayoutManager = new GridLayoutManager  (getApplicationContext(),2);
     //   linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        apiInterface = Client.getRetrofit().create(ApiInterface.class);
        Call<Headlines> call = apiInterface.getHeadlines(country,apikey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() || response.body().getArticles()!=null)
                {
                    articles=response.body().getArticles();
                  mediaada m = new mediaada(Media.this,articles);
                   recyclerView.setAdapter(m);
                  //  Toast.makeText(Media.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {

            }
        });
    }
}
