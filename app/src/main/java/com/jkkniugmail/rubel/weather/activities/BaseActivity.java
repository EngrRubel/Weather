package com.jkkniugmail.rubel.weather.activities;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.jkkniugmail.rubel.weather.R;
import com.jkkniugmail.rubel.weather.fragments.CurrentWeatherFragment;

import static com.jkkniugmail.rubel.weather.activities.MainActivity.city_name;
import static com.jkkniugmail.rubel.weather.activities.MainActivity.frg_1_flag;
import static com.jkkniugmail.rubel.weather.activities.MainActivity.frg_2_flag;

/**
 * Created by islan on 12/15/2016.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                city_name = query;
                frg_1_flag = true;
                frg_2_flag = true;
                new CurrentWeatherFragment();
                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}