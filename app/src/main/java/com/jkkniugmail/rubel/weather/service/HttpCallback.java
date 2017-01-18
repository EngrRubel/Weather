package com.jkkniugmail.rubel.weather.service;


import com.jkkniugmail.rubel.weather.models.pojo.current.CurrentWeather;
import com.jkkniugmail.rubel.weather.models.pojo.forecast.ForecastWeather;

/**
 * Created by islan on 12/17/2016.
 */

public interface HttpCallback {

    public void onSuccessForecast(ForecastWeather response);
    public void onSuccessCurrent(CurrentWeather response);
    public void onFailure(String error);

}