package com.adipurnomo.moviecatalogapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.adipurnomo.moviecatalogapi.R;
import com.adipurnomo.moviecatalogapi.DetailMoviesActivity;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

//import submission3.hasancaem.com.moviecatalog3.modelmovie.Result;
import com.adipurnomo.moviecatalogapi.modelmovie.Result;

/**
 * Created by capricorn on 15/05/2019.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private Context context;
    private List<Result> listMovie;

    /*public MovieAdapter(List<Result> listMovie) {
        this.listMovie = listMovie;
    }*/

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie_row, parent, false);
        return new MovieHolder(v);
    }

    @Override
    public void onBindViewHolder(final MovieHolder holder, final int position) {
        holder.tv_Title_Movie.setText(listMovie.get(position).getOriginalTitle());
        if (listMovie.get(position).getOverview().isEmpty())
        {
            holder.tv_Overvie_Movie.setText("Format Bahasa Indonesia tidak ada..");
        }else {
            holder.tv_Overvie_Movie.setText(listMovie.get(position).getOverview());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Result data = listMovie.get(position);
                Intent intent = new Intent(holder.itemView.getContext(), DetailMoviesActivity.class);
                intent.putExtra("movie",new GsonBuilder().create().toJson(data));
                holder.itemView.getContext().startActivity(intent);
            }
        });
        Picasso.with(holder.itemView.getContext()).
                load("https://image.tmdb.org/t/p/w185/" + listMovie.get(position).getPosterPath())
                .error(R.drawable.no_image)
                .placeholder(R.drawable.progress_animation)
                .into(holder.imageViewMovie);
    }

    //membuat metode untuk ambil list movie
    public void setData(List<Result> listMovie) {
        this.listMovie = listMovie;
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        ImageView imageViewMovie;
        TextView tv_Title_Movie, tv_Overvie_Movie;

        public MovieHolder(View itemView) {
            super(itemView);
            imageViewMovie = itemView.findViewById(R.id.image_movie);
            tv_Title_Movie = itemView.findViewById(R.id.tv_title_movie);
            tv_Overvie_Movie = itemView.findViewById(R.id.tv_description_movie);
        }
    }
}
