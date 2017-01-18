package com.jkkniugmail.rubel.weather.service;

import com.jkkniugmail.rubel.weather.models.pojo.current.CurrentWeather;
import com.jkkniugmail.rubel.weather.models.pojo.forecast.ForecastWeather;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.jkkniugmail.rubel.weather.Constant.BASE_URL;


public class APIManager {

    private static CurrentWeatherAPI currentWeatherAPI;


    public interface CurrentWeatherAPI {
        @GET("data/2.5/weather?")
        Call<CurrentWeather> getWheatherReport(
                @Query("q") String city,
                @Query("units") String unit,
                @Query("appid") String appid);

        //lat=35&lon=139&appid=621d60d4900ccc5a8f1b36351a2ba4c5
    }

    public static CurrentWeatherAPI getCurrentWeatherAPIService () {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(CurrentWeatherAPI.class);


    }


    public interface ForecastWeatherAPI {
        @GET("data/2.5/forecast/daily?")
        Call<ForecastWeather> getForecastWheatherReport(@Query("q") String city,
                                                        @Query("units") String unit,
                                                        @Query("cnt") int day,
                                                        @Query("appid") String appid);
    }

    public static ForecastWeatherAPI getForecastWeatherAPIService (){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ForecastWeatherAPI.class);
    }

}