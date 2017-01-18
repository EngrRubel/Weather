package com.jkkniugmail.rubel.weather.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.jkkniugmail.rubel.weather.models.pojo.current.CurrentWeather;
import com.jkkniugmail.rubel.weather.models.pojo.forecast.ForecastWeather;

import static android.content.Context.MODE_PRIVATE;
import static com.jkkniugmail.rubel.weather.Constant.CURRENT_WEATHER;
import static com.jkkniugmail.rubel.weather.Constant.FORECAST_WEATHER;

/**
 * Created by islan on 12/20/2016.
 */

public class MySharedPreferences {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    MySharedPreferences(Context context){
        sharedPreferences = context.getSharedPreferences("user", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveCurrentWeatherData(CurrentWeather currentWeather){
        Gson gson = new Gson();
        String json = gson.toJson(currentWeather);
        editor.putString(CURRENT_WEATHER, json);
        editor.commit();
    }

    public void saveForecastWeatherData(ForecastWeather forecastWeather){
        Gson gson = new Gson();
        String json = gson.toJson(forecastWeather);
        editor.putString(FORECAST_WEATHER, json);
        editor.commit();
    }

    public CurrentWeather getSavedCurrentWeatherData(){
        CurrentWeather currentWeather;
        Gson gson = new Gson();
        String json = sharedPreferences.getString(CURRENT_WEATHER, "x");
        currentWeather = gson.fromJson(json, CurrentWeather.class);
        return currentWeather;
    }

    public ForecastWeather getSavedForecastWeatherData(){
        ForecastWeather forecastWeather;
        Gson gson = new Gson();
        String json = sharedPreferences.getString(FORECAST_WEATHER, "x");
        forecastWeather = gson.fromJson(json, ForecastWeather.class);
        return forecastWeather;
    }
}
