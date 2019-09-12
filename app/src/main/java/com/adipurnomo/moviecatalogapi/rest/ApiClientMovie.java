package com.adipurnomo.moviecatalogapi.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by capricorn on 15/05/2019.
 */

public class ApiClientMovie {
    public static Retrofit retrofit = null;


    public static final String BASE_URL = "https://api.themoviedb.org/3/";

   public static Retrofit getRetrofit(){
       if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                   .baseUrl(BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
       }
        return retrofit;
    }


}
