package com.example.user.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.weather.R;
import com.example.user.weather.Weather;
import com.example.user.weather.models.forecastModels.Forecast;
import com.example.user.weather.network.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiveDaysForecast extends ActivityBase {
    ListView listView;
    private RetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        service = ((Weather) getApplication()).getService();
        listView = findViewById(R.id.showMoreList);
        getWeatherMore();


    }

    private void getWeatherMore() {
        showProgressBar();
        Intent intent = getIntent();
        service.getWeatherForecast(intent.getStringExtra("locationKey"), getString(R.string.apikey2), "ru-RU", true, true)
                .enqueue(new Callback<Forecast>() {
                    @Override
                    public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Forecast model = response.body();

                            AdapterForDailyForecast adapter = new AdapterForDailyForecast(getApplicationContext(),
                                    model.getDailyForecasts());
                            listView.setAdapter(adapter);
                            Log.d("getWeatherMore", adapter.toString());
                        } else {
                            Toast.makeText(getApplicationContext(), "Данные не найдены ", Toast.LENGTH_LONG).show();

                        }
                        dismissProgressBar();
                    }

                    @Override
                    public void onFailure(Call<Forecast> call, Throwable t) {

                    }
                });
    }

}




