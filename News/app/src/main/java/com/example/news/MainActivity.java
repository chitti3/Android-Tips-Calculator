package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
TextView signup;
EditText email,pass;
Button loginn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup=findViewById(R.id.create);
      email=findViewById(R.id.emailtext);
      pass=findViewById(R.id.epass);
      loginn=findViewById(R.id.log);
        final ProgressBar simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Signup.class);
                startActivity(intent);
            }
        });

        loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emaill=email.getText().toString().trim();
                String passs=pass.getText().toString().trim();
                simpleProgressBar.setVisibility(ProgressBar.VISIBLE);
                if (emaill.isEmpty())
                {
                    email.setError("Please Fill");
                }else if (passs.isEmpty())
                {
                    pass.setError("Please Fill");

                } else {
                         Interface anterface = LClient.getRetrofit().create(Interface.class);
                 Call<Spojo> call = anterface.Login(emaill,passs);
                 call.enqueue(new Callback<Spojo>() {
                     @Override
                     public void onResponse(Call<Spojo> call, Response<Spojo> response) {
                         //String hey = response.body().getResponse();
                         if (response.body().getResponse().equals("successfully"))
                         {
                             Intent logiii = new Intent(MainActivity.this,Home.class);
                             startActivity(logiii);
                             Intent pass = new Intent(MainActivity.this,Profile.class);
                             pass.putExtra("email",emaill);
                            simpleProgressBar.setVisibility(ProgressBar.INVISIBLE);
                         }else if (response.body().getResponse().equals("Failed"))
                         {
                             Toast.makeText(MainActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                             simpleProgressBar.setVisibility(ProgressBar.INVISIBLE);
                         }

                     }

                     @Override
                     public void onFailure(Call<Spojo> call, Throwable t) {

                     }
                 });
                }
            }
        });
    }
}
