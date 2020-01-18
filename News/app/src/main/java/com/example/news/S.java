package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

public class S extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Articles> articles = new ArrayList<>();
    final String apikey="9c952917eb4c4d20ac26b29cd474bc07";
    String country,category;
    ProgressBar progressBar;
    busadp bap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s);
        Intent intent =getIntent();
        String id = intent.getStringExtra("source");

        country = "in";
        category="business";
        recyclerView =findViewById(R.id.recycle);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getApplicationContext());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);


        progressBar = findViewById(R.id.simpleProgressBar);
        progressBar.setVisibility(ProgressBar.VISIBLE);
        ApiInterface apiInterface = Client.getRetrofit().create(ApiInterface.class);
        Call<Headlines> call = apiInterface.getSource(id,apikey);

        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                //  Toast.makeText(Business.this, response.body().getStatus(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful() && response.body().getArticles()!=null)
                {
                    articles=response.body().getArticles();
                    bap = new busadp(S.this,articles);
                    recyclerView.setAdapter(bap);
                    progressBar.setVisibility(progressBar.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                Toast.makeText(S.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
