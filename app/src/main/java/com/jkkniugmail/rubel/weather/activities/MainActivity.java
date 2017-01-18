package com.jkkniugmail.rubel.weather.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.jkkniugmail.rubel.weather.R;
import com.jkkniugmail.rubel.weather.Style.ZoomOutPageTransformer;
import com.jkkniugmail.rubel.weather.adapters.PageAdapter;

import static com.jkkniugmail.rubel.weather.Constant.DEFAULT_CITY;

public class MainActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    //This is our tablayout
    private TabLayout tabLayout;
    //This is our viewPager
    private ViewPager viewPager;
    //This is our toolbar
    private Toolbar toolbar;


    public static String city_name;
    public static boolean frg_1_flag;
    public static boolean frg_2_flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        frg_1_flag = true;
        frg_2_flag = true;
        city_name = DEFAULT_CITY;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adding toolbar to the activity
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Current Weather"));
        tabLayout.addTab(tabLayout.newTab().setText("Forecast Weather"));
        //tabLayout.addTab(tabLayout.newTab().setText("xxx"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Creating our pager adapter
        PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);
    }




    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
        toolbar.setTitle(tab.getText().toString());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
