package com.adipurnomo.moviecatalogapi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class MainActivity extends AppCompatActivity{

    /*ViewPager*/
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerMovieAdapter adapterMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout =findViewById(R.id.tablayout_id);
        viewPager = findViewById(R.id.viewpager_id);
        adapterMovie =new ViewPagerMovieAdapter(getSupportFragmentManager());
        adapterMovie.addFragmentMovie(new MoviesFragment(),getResources().getString(R.string.movie));
        adapterMovie.addFragmentMovie(new TvshowsFragment(),getResources().getString(R.string.tvshow));
        viewPager.setAdapter(adapterMovie);
        tabLayout.setupWithViewPager(viewPager);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_utama,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    /*  @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       outState.putInt("SelectedItemId",bottomNavigationView.getSelectedItemId());
    }

   @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int selectedItemId = savedInstanceState.getInt("SelectedItemId");
        bottomNavigationView.setSelectedItemId(selectedItemId);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment=null;
        switch (menuItem.getItemId()){
            case R.id.home_menu:
                fragment=new HomeFragment();
                break;
            case R.id.movie_menu:
                fragment=new MoviesFragment();
                break;
            case R.id.tvshow_menu:
                fragment=new TvshowsFragment();
                break;
        }
        return loadFragment(fragment);
    }*/
}
