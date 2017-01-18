package com.jkkniugmail.rubel.weather.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.jkkniugmail.rubel.weather.R;
import com.jkkniugmail.rubel.weather.adapters.ForeastAdapter;
import com.jkkniugmail.rubel.weather.models.pojo.forecast.ForecastWeather;
import com.jkkniugmail.rubel.weather.models.pojo.forecast.List;
import com.jkkniugmail.rubel.weather.service.APIManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.jkkniugmail.rubel.weather.Constant.API_KEY;
import static com.jkkniugmail.rubel.weather.Constant.UNIT_METRIC;
import static com.jkkniugmail.rubel.weather.activities.MainActivity.city_name;
import static com.jkkniugmail.rubel.weather.activities.MainActivity.frg_2_flag;

/**
 * Created by islan on 12/14/2016.
 */

public class ForecastWeatherFragment extends Fragment {
    Call<ForecastWeather> call;
    ListView listView;
    ForeastAdapter adapter;
    ForecastWeather forecastWeather;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_forecast, container, false);
        listView = (ListView) view.findViewById(R.id.forecast_list);
        getForecastReport(city_name, UNIT_METRIC,7,API_KEY);
        frg_2_flag=false;
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        if(frg_2_flag==true){
            getForecastReport(city_name, UNIT_METRIC,7,API_KEY);
            frg_2_flag=false;
        }
    }

    public void setAdapterOnListView(java.util.List<List> forecastList){
        adapter = new ForeastAdapter(getContext(), forecastList);
        listView.setAdapter(adapter);

    }

    public void getForecastReport(String city, String unit, int forcastDay, String api_key){
        call = APIManager.getForecastWeatherAPIService().getForecastWheatherReport(city, unit, forcastDay, api_key);
        call.enqueue(new Callback<ForecastWeather>() {
            @Override
            public void onResponse(Call<ForecastWeather> call, Response<ForecastWeather> response) {
                if (response != null && response.errorBody() == null) {
                    forecastWeather = response.body();
                    setAdapterOnListView(forecastWeather.getList());
                }
                else
                    Toast.makeText(getContext(), response.errorBody().toString(), Toast.LENGTH_LONG);

            }

            @Override
            public void onFailure(Call<ForecastWeather> call, Throwable t) {
                Toast.makeText(getContext(), "failed", Toast.LENGTH_LONG);

            }
        });


    }


}
