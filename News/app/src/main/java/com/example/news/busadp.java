package com.example.news;

import android.content.Context;
import android.content.Intent;
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

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class busadp extends RecyclerView.Adapter<busadp.ViewHolder> {
    List<Articles> articlesList;
    Context context;

    public busadp(Context context,List<Articles> articlesList) {
        this.articlesList = articlesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.busitem,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         final Articles articles = articlesList.get(position);
         holder.name.setText(articles.getSource().getName());
         holder.tvTitle.setText(articles.getTitle());
        holder.tvSource.setText(articles.getSource().getName());
      //  holder.tvDate.setText(articles.getPublishedAt());
      holder.tvDate.setText(datetime(articles.getPublishedAt()));
        final String imageUrl=articles.getUrlToImage();
        //picasso ka syntax new 33.07 part1
        Picasso.get().load(imageUrl).into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),busdetailed.class);
                intent.putExtra("Title",articles.getTitle());
                intent.putExtra("Name",articles.getSource().getName());
                intent.putExtra("Date",datetime(articles.getPublishedAt()));
                //    intent.putExtra("Date",a.getPublishedAt());
                intent.putExtra("image",imageUrl);
                intent.putExtra("desc",articles.getDescription());
                intent.putExtra("url",articles.getUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvSource,tvDate,name;
        ImageView imageView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            tvTitle=itemView.findViewById(R.id.tvId);
            tvDate=itemView.findViewById(R.id.tvDate);
            tvSource=itemView.findViewById(R.id.tvSource);
            imageView=itemView.findViewById(R.id.image);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String datetime(String t)
    {
        PrettyTime prettyTime = new PrettyTime(new Locale("us"));
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
