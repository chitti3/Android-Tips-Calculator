package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
textView = findViewById(R.id.text);
        Interface inter = LClient.getRetrofit().create(Interface.class);
        Call<user> call=inter.user(email);

     call.enqueue(new Callback<user>() {
         @Override
         public void onResponse(Call<user> call, Response<user> response) {
             Toast.makeText(Profile.this, response.body().getFname(), Toast.LENGTH_SHORT).show();
         }

         @Override
         public void onFailure(Call<user> call, Throwable t) {
             Toast.makeText(Profile.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
         }
     });

    }
}
