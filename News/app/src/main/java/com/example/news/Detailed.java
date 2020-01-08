package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Detailed extends AppCompatActivity {
    TextView tvTitle,tvSource,tvDate,Desc;
    ImageView imageView;
    WebView webiew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        tvTitle=findViewById(R.id.tvId);
        tvDate=findViewById(R.id.tvDate);
        tvSource=findViewById(R.id.tvSource);
        Desc=findViewById(R.id.desc);
        imageView=findViewById(R.id.image);
        webiew=findViewById(R.id.webView);

        Intent intent = getIntent();
        String Title = intent.getStringExtra("Title");
        String Name = intent.getStringExtra("Name");
        String Date = intent.getStringExtra("Date");
        String image = intent.getStringExtra("image");
        String Descr = intent.getStringExtra("desc");
       String url = intent.getStringExtra("url");
        Log.e("url",url);
       // Toast.makeText(this, url, Toast.LENGTH_SHORT).show();

        tvTitle.setText(Title);
        tvDate.setText(Name);
        tvSource.setText(Date);
        Desc.setText(Descr);
        Picasso.get().load(image).into(imageView);
    //Picasso.get().load(imageUrl).into(holder.imageView);
        webiew.getSettings().setDomStorageEnabled(true);
        webiew.getSettings().setJavaScriptEnabled(true);
        webiew.getSettings().setLoadsImagesAutomatically(true);
        webiew.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webiew.setWebViewClient(new WebViewClient());
        webiew.loadUrl(url);

       /* webiew.getSettings().setJavaScriptEnabled(true);
        webiew.loadUrl(url);
        webiew.setHorizontalScrollBarEnabled(false);*/

    }
}
