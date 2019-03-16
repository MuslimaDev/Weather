package com.example.user.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.weather.R;
import com.example.user.weather.Weather;
import com.example.user.weather.model.forecastModels.Forecast;
import com.example.user.weather.network.RetrofitService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiveDaysForecast extends ActivityBase {
    private ListView listView;
    private RetrofitService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        service = ((Weather) getApplication()).getService();
        listView = findViewById(R.id.showMoreList);
        getFiveDaysForecast();
    }

    private void getFiveDaysForecast() {
        showProgressBar();
        Intent intent = getIntent();
        service.getWeatherForecast(intent.getStringExtra("locationKey"), getString(R.string.apikey3), "en", true, true)
                .enqueue(new Callback<Forecast>() {
                    @Override
                    public void onResponse(@NotNull Call<Forecast> call, @NotNull Response<Forecast> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            Forecast model = response.body();
                            AdapterForDailyForecast adapter = new AdapterForDailyForecast(getApplicationContext(), model.getDailyForecasts());
                            listView.setAdapter(adapter);
                        } else {
                            Toast.makeText(getApplicationContext(), "Data not found", Toast.LENGTH_LONG).show();
                        }
                        dismissProgressBar();
                    }

                    @Override
                    public void onFailure(@NotNull Call<Forecast> call, @NotNull Throwable t) {
                    }
                });
    }
}