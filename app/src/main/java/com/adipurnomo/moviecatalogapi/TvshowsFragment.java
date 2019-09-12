package com.adipurnomo.moviecatalogapi;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.adipurnomo.moviecatalogapi.adapter.MovieAdapter;
import com.adipurnomo.moviecatalogapi.adapter.TvshowsAdapter;
import com.adipurnomo.moviecatalogapi.modelmovie.Movie;
import com.adipurnomo.moviecatalogapi.modeltv.Tvshow;
import com.adipurnomo.moviecatalogapi.rest.ApiClientMovie;
import com.adipurnomo.moviecatalogapi.rest.ApiMovieInterface;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvshowsFragment extends Fragment {
    /**/
    private Tvshow tvshow;
    private RecyclerView recyclerViewTv;
    private TvshowsAdapter tvshowsAdapter;
    /**/
    private SwipeRefreshLayout pullToRefresh;
    private LinearLayoutManager linearLayoutManager;
    View view;
    /*CACHE*/
    int cacheSize = 10 * 1024 * 1024;
    /**/

    public TvshowsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tvshows,container,false);
        recyclerViewTv=view.findViewById(R.id.rv_tvshow);
        recyclerViewTv.setHasFixedSize(true);
        recyclerViewTv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerViewTv.setAdapter(tvshowsAdapter);
        movieLoad("inggris");
        pullToRefresh=view.findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerViewTv.setAdapter(tvshowsAdapter);
                pullToRefresh.setRefreshing(false);
            }
        });
        return view;
    }
    public void movieLoad(String nilai) {
        Cache cache = new Cache(getContext().getCacheDir(), cacheSize);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        //Service apiService = (Service) retrofit.create(ApiMovieInterface.class);
        ApiMovieInterface api = retrofit.create(ApiMovieInterface.class);
        Call<Tvshow> call = null;
        if (nilai.equals("indonesia")) {
            call = api.getTvIndonesia();
        } else if (nilai.equals("inggris")) {
            call = api.getTvInggris();
        }
        call.enqueue(new Callback<Tvshow>() {
            @Override
            public void onResponse(Call<Tvshow> call, Response<Tvshow> response) {

                Tvshow tvshow = response.body();
                tvshowsAdapter = new TvshowsAdapter();
                tvshowsAdapter.setData(tvshow.getResults());
                recyclerViewTv.setAdapter(tvshowsAdapter);
            }

            @Override
            public void onFailure(Call<Tvshow> call, Throwable t) {
                Toast.makeText(getContext(), "maaf gagal...", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
