package com.adipurnomo.moviecatalogapi;

import android.os.Bundle;

import com.adipurnomo.moviecatalogapi.modelmovie.Result;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.ImageView;
import android.widget.TextView;

public class DetailMoviesActivity extends AppCompatActivity {
    /**/
    Result result;
    ImageView imageViewDetail;
    TextView detailMovie;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        /**/
        imageViewDetail =findViewById(R.id.img_movie_detail);
        toolbar =findViewById(R.id.toolbar);
        detailMovie = findViewById(R.id.tvDetailMovie);
        result =new GsonBuilder()
                .create()
                .fromJson(getIntent().getStringExtra("movie"), Result.class);
        //result = getIntent().getStringExtra("movie",Result.class);
        /*picasso*/
        Picasso.with(DetailMoviesActivity.this)
                .load("https://image.tmdb.org/t/p/w185/"+result.getPosterPath())
                .into(imageViewDetail);
        /**/
        toolbar.setTitle(result.getTitle());
        detailMovie.setText(result.getOverview());
        /**/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
}
