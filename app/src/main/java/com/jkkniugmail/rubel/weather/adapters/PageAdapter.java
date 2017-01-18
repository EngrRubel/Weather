package com.jkkniugmail.rubel.weather.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jkkniugmail.rubel.weather.fragments.CurrentWeatherFragment;
import com.jkkniugmail.rubel.weather.fragments.ForecastWeatherFragment;

/**
 * Created by islan on 12/14/2016.
 */

public class PageAdapter  extends FragmentStatePagerAdapter {

    int tabCount;

    public PageAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                CurrentWeatherFragment currentWeatherFragment = new CurrentWeatherFragment();
                return currentWeatherFragment;
            case 1 :
                ForecastWeatherFragment forecastWeatherFragment = new ForecastWeatherFragment();
                return forecastWeatherFragment;
            default :
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
