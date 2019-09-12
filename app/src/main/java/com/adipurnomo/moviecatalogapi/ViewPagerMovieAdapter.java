package com.adipurnomo.moviecatalogapi;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by capricorn on 10/05/2019.
 */

public class ViewPagerMovieAdapter extends FragmentPagerAdapter {

    private final List<Fragment> listFragment = new ArrayList<>();
    private final List<String> listTitle = new ArrayList<>();
    public ViewPagerMovieAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return listTitle.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }
    public void addFragmentMovie(Fragment fragment, String title){
        listFragment.add(fragment);
        listTitle.add(title);
    }
}
