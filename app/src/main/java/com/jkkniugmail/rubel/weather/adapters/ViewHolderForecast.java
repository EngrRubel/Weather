package com.jkkniugmail.rubel.weather.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jkkniugmail.rubel.weather.R;

/**
 * Created by islan on 12/19/2016.
 */

public class ViewHolderForecast {

    TextView date;
    TextView dscr;
    TextView temp_day;
    TextView wind;
    TextView press;
    TextView temp_night;
    TextView hum;
    TextView cloud;
    ImageView icn;


    ViewHolderForecast(View view){
        date = (TextView) view.findViewById(R.id.fc_date);
        dscr = (TextView) view.findViewById(R.id.fc_dsc);
        temp_day = (TextView) view.findViewById(R.id.fc_tmp_day);
        temp_night = (TextView) view.findViewById(R.id.fc_tmp_night);
        wind = (TextView) view.findViewById(R.id.fc_wind);
        press = (TextView) view.findViewById(R.id.fc_prss);
        hum = (TextView) view.findViewById(R.id.fc_hum);
        cloud = (TextView) view.findViewById(R.id.fc_cloud);
        icn = (ImageView) view.findViewById(R.id.fc_icn);

    }
}
