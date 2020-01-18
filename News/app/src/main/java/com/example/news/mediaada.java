package com.example.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.Model.Articles;
import com.squareup.picasso.Picasso;

import java.util.List;

public class mediaada extends RecyclerView.Adapter<mediaada.ViewHolder> {
    Context context;
    List<Articles> articles;

    public mediaada(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tem,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
          final Articles a = articles.get(position);
        final String s = a.getUrlToImage();
          if (a.getSource().getId()==null)
          {
              holder.textView.setVisibility(View.GONE);
             holder.imageView.setVisibility(View.GONE);
            //  Picasso.get().load(s).into(holder.imageView);

          }else
          if (a.getSource().getId()!=null) {
              holder.textView.setText(a.getSource().getName());

             Picasso.get().load(s).into(holder.imageView);
              //Picasso.get().load(imageUrl).into(holder.imageView);
              holder.imageView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Articles a1 = articles.get(position);
                      String sid=a1.getSource().getId();
                      Intent intent = new Intent(context,S.class);
                      intent.putExtra("source",sid);
                      context.startActivity(intent);
                      Toast.makeText(context,sid, Toast.LENGTH_SHORT).show();
                  }
              });
          }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.H);
            imageView=itemView.findViewById(R.id.image);
        }
    }
}
