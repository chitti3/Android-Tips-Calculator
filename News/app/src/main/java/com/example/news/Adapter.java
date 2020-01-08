package com.example.news;

import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.Model.Articles;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.format.SimpleTimeFormat;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    List<Articles> articles;
    public Adapter(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xml,parent,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Articles a=articles.get(position);
        holder.tvTitle.setText(a.getTitle());
        //get source ke saath .getname lagana hai 32.36 part 1
        holder.tvSource.setText(a.getSource().getName());
        holder.tvDate.setText(a.getPublishedAt());
     //holder.tvDate.setText(datetime(a.getPublishedAt()));
        final String imageUrl=a.getUrlToImage();
        //picasso ka syntax new 33.07 part1
        Picasso.get().load(imageUrl).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),Detailed.class);
                intent.putExtra("Title",a.getTitle());
                intent.putExtra("Name",a.getSource().getName());
          //intent.putExtra("Date",datetime(a.getPublishedAt()));
              intent.putExtra("Date",a.getPublishedAt());
                intent.putExtra("image",imageUrl);
                intent.putExtra("desc",a.getDescription());
             intent.putExtra("url",a.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvSource,tvDate;
        ImageView imageView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle=itemView.findViewById(R.id.tvId);
            tvDate=itemView.findViewById(R.id.tvDate);
            tvSource=itemView.findViewById(R.id.tvSource);
            imageView=itemView.findViewById(R.id.image);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
    public String getCountry()
    {
        Locale locale=Locale.ENGLISH;
        String country=locale.getCountry();
        return country.toLowerCase();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String datetime(String t)
    {
        PrettyTime prettyTime = new PrettyTime(new Locale(getCountry()));
        Locale us = Locale.US;
        Locale locale=Locale.ENGLISH;
       // prettyTime.format(t.toDate());
        String time = null;
        try {

            //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:");
           SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("yyyy-MM-dd 'T':HH:mm:",locale);
            Date date = simpleTimeFormat.parse(t);
            time=prettyTime.format(date);
        }catch (ParseException e)
        {
            e.printStackTrace();
        }
        return  time;
    }
}

