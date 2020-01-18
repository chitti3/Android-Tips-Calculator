package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.news.Model.Articles;
import com.example.news.Model.Headlines;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Technology extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Articles> articles = new ArrayList<>();
    final String apikey="9c952917eb4c4d20ac26b29cd474bc07";
    String country,category;
    ProgressBar progressBar;
    busadp bap;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_technology);
        swipeRefreshLayout=findViewById(R.id.swiper);
        country = "in";
        category="technology";
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                technology(country,category,apikey);
            }
        });

        recyclerView =findViewById(R.id.recycle);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        progressBar = findViewById(R.id.simpleProgressBar);
        progressBar.setVisibility(ProgressBar.VISIBLE);
        technology(country,category,apikey);

    }
    private void technology(String country, String category, String apikey) {
        swipeRefreshLayout.setRefreshing(true);
        ApiInterface apiInterface = Client.getRetrofit().create(ApiInterface.class);
        Call<Headlines> call = apiInterface.getBusiness(country,category,apikey);

        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                //  Toast.makeText(Business.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful() && response.body().getArticles()!=null)
                {
                    swipeRefreshLayout.setRefreshing(true);
                    articles=response.body().getArticles();
                    bap = new busadp(Technology.this,articles);
                    recyclerView.setAdapter(bap);
                    progressBar.setVisibility(progressBar.INVISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(Technology.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
