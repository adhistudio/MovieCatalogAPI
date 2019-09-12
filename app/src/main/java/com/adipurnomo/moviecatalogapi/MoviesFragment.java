package com.adipurnomo.moviecatalogapi;



import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.adipurnomo.moviecatalogapi.adapter.MovieAdapter;
import com.adipurnomo.moviecatalogapi.modelmovie.Movie;
import com.adipurnomo.moviecatalogapi.modelmovie.Result;
import com.adipurnomo.moviecatalogapi.rest.ApiMovieInterface;

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
public class MoviesFragment extends Fragment {

    private Movie movie;
    //inisialisasi recycleview
    View v;
    private RecyclerView recyclerViewMovie;
   /* private MahasiswaAdapter mahasiswaAdapter;
    private List<Mahasiswa> mahasiswaArrayList;*/
    private MovieAdapter movieAdapter;
    private List<Result> listMovie;
    private List<Movie> movieList;
    /*CACHE*/
    int cacheSize = 10 * 1024 * 1024;
    /*Swipe refresh*/
    SwipeRefreshLayout pullToRefresh;
    private RecyclerView.LayoutManager  linearLayoutManager;
    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putParcelableArrayList("movies",movie);

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_movies,container,false);

        pullToRefresh =v.findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerViewMovie.setAdapter(movieAdapter);
                pullToRefresh.setRefreshing(false);
            }
        });

        movieLoad("inggris");


        return v;
    }

    public void movieLoad(String nilai) {

        recyclerViewMovie = v.findViewById(R.id.rv_movies);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerViewMovie.setLayoutManager(linearLayoutManager);
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
        Call<Movie> call = null;
        if (nilai.equals("indonesia")) {
            call = api.getMovieIndonesia();
        } else if (nilai.equals("inggris")) {
            call = api.getMovieInggris();
        }
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
               /* final ProgressDialog progDailog = ProgressDialog.show(getContext(),
                        "Loading",
                        "please wait....", true);*/
                new Thread() {
                    public void run() {
                        try {
                            // sleep the thread, whatever time you want.
                            sleep(1000);
                        } catch (Exception e) {
                        }
                        //progDailog.dismiss();
                    }
                }.start();
                movie = response.body();
                movieAdapter = new MovieAdapter();
                movieAdapter.setData(movie.getResults());
                recyclerViewMovie.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Toast.makeText(getContext(), "maaf gagal...", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
