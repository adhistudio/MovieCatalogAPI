package com.adipurnomo.moviecatalogapi.rest;


import com.adipurnomo.moviecatalogapi.modeltv.Tvshow;
import com.adipurnomo.moviecatalogapi.modelmovie.Movie;
import retrofit2.Call;
import retrofit2.http.GET;



/**
 * Created by capricorn on 15/05/2019.
 */

public interface ApiMovieInterface {
    public static String DB_API = "31dfb142859a839875e5fa969dc53965";

    @GET("movie/popular?api_key="+DB_API)
    Call<Movie> getPopular();

    @GET("movie/top_rated?api_key="+DB_API)
    Call<Movie> getTopRated();

    @GET("movie/top_rated?api_key="+DB_API+"&language=id")
    Call<Movie> getMovieIndonesia();

    @GET("movie/top_rated?api_key="+DB_API+"&language=en-US")
    Call<Movie> getMovieInggris();

    @GET("tv/popular?api_key="+DB_API+"&language=id")
    Call<Tvshow> getTvIndonesia();

    @GET("tv/popular?api_key="+DB_API+"&language=en-US")
    Call<Tvshow> getTvInggris();




}
