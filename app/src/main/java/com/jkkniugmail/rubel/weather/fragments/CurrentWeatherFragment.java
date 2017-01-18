package com.jkkniugmail.rubel.weather.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jkkniugmail.rubel.weather.R;
import com.jkkniugmail.rubel.weather.models.pojo.current.CurrentWeather;
import com.jkkniugmail.rubel.weather.models.pojo.current.Weather;
import com.jkkniugmail.rubel.weather.service.APIManager;
import com.jkkniugmail.rubel.weather.utils.TimeStamp;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jkkniugmail.rubel.weather.Constant.API_KEY;
import static com.jkkniugmail.rubel.weather.Constant.UNIT_METRIC;
import static com.jkkniugmail.rubel.weather.activities.MainActivity.city_name;
import static com.jkkniugmail.rubel.weather.activities.MainActivity.frg_1_flag;

/**
 * Created by islan on 12/14/2016.
 */

public class CurrentWeatherFragment extends Fragment {

    String city;

    ViewHolderCurrent viewHolderCurrent;
    CurrentWeather currentWeather;
    Context context;
    Call<CurrentWeather> call;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_current, container, false);
        context = container.getContext();
        viewHolderCurrent = new ViewHolderCurrent(view);
        getCurrentWeatherReport(city_name, UNIT_METRIC, API_KEY);
        frg_1_flag = false;
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        if(frg_1_flag==true){
            getCurrentWeatherReport(city_name, UNIT_METRIC,API_KEY);
            frg_1_flag=false;
        }
    }



    public void setCurrentWeatherValues(CurrentWeather currentWeather){

        TimeStamp t = new TimeStamp();

        java.util.List<Weather> weathers = currentWeather.getWeather();
        Weather weather = weathers.get(0);


        String icon = "http://openweathermap.org/img/w/"+weather.getIcon().toString()+".png";

        viewHolderCurrent.city.setText(currentWeather.getName().toString());
        viewHolderCurrent.date.setText(t.getDateString(currentWeather.getDt()));
        viewHolderCurrent.cloud.setText(currentWeather.getClouds().getAll()+"%");
        viewHolderCurrent.humidity.setText(currentWeather.getMain().getHumidity().toString()+"%");
        viewHolderCurrent.desc.setText(currentWeather.getWeather().get(0).getDescription().toString());
        viewHolderCurrent.pressure.setText(currentWeather.getMain().getPressure().toString()+"hPa");
        viewHolderCurrent.temp.setText(currentWeather.getMain().getTemp().toString()+"°C");
        viewHolderCurrent.wind.setText(currentWeather.getWind().getSpeed().toString()+"m/s");
        viewHolderCurrent.sunset.setText(t.getTimeString(currentWeather.getSys().getSunset()));
        viewHolderCurrent.sunrise.setText(t.getTimeString(currentWeather.getSys().getSunrise()));
        Picasso.with(context).load(icon).resize(150,150).into(viewHolderCurrent.icon);

    }

    public class ViewHolderCurrent {

        TextView city;
        TextView date;
        TextView desc;
        TextView temp;
        TextView wind;
        TextView cloud;
        TextView pressure;
        TextView humidity;
        TextView sunrise;
        TextView sunset;
        ImageView icon;


        public ViewHolderCurrent(View view){
            city = (TextView) view.findViewById(R.id.city_name);
            date = (TextView) view.findViewById(R.id.date);
            desc = (TextView) view.findViewById(R.id.description);
            temp = (TextView) view.findViewById(R.id.temp);
            wind = (TextView) view.findViewById(R.id.wind);
            cloud = (TextView) view.findViewById(R.id.cloud);
            pressure = (TextView) view.findViewById(R.id.pressure);
            humidity = (TextView) view.findViewById(R.id.humidity);
            sunrise = (TextView) view.findViewById(R.id.sunrise);
            sunset = (TextView) view.findViewById(R.id.sunset);
            icon = (ImageView) view.findViewById(R.id.icon);
        }
    }

    public void getCurrentWeatherReport(String city, String unit, String api_key){
        call = APIManager.getCurrentWeatherAPIService().getWheatherReport(city, unit, api_key);
        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if (response != null && response.errorBody() == null) {
                    currentWeather = response.body();
                    setCurrentWeatherValues(currentWeather);
                }
                else
                    Toast.makeText(getContext(), response.errorBody().toString(), Toast.LENGTH_LONG);

            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                Toast.makeText(getContext(), "failed", Toast.LENGTH_LONG);

            }
        });

    }
}
