package com.adipurnomo.moviecatalogapi;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.adipurnomo.moviecatalogapi.modeltv.Result;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

public class DetailTvActivity extends AppCompatActivity {
    /**/
    Result result;
    ImageView imageViewDetail;
    TextView detailMovie;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailtv);
        /**/
        imageViewDetail =findViewById(R.id.img_movie_detail);
        toolbar =findViewById(R.id.toolbar_tv);
        detailMovie = findViewById(R.id.tvDetailTv);
        result =new GsonBuilder()
                .create()
                .fromJson(getIntent().getStringExtra("movie"), Result.class);
        //result = getIntent().getStringExtra("movie",Result.class);
        /*picasso*/
        Picasso.with(DetailTvActivity.this)
                .load("https://image.tmdb.org/t/p/w185/"+result.getPosterPath())
                .into(imageViewDetail);
        /**/
        Toast.makeText(this, ""+result.getName(), Toast.LENGTH_SHORT).show();
        toolbar.setTitle(result.getName());
        detailMovie.setText(result.getOverview());
        /**/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_tv);
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
