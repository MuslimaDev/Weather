package com.example.user.weather.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.example.user.weather.R;
import com.example.user.weather.models.Example;
import com.example.user.weather.network.RetrofitService;
import com.example.user.weather.utils.PermissionUtils;
import com.google.android.gms.location.FusedLocationProviderClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FiveDaysForecast extends Activity {
    private RetrofitService service;
    private TextView locationForFiveDays, firstDayMax, firstDayMin, firstDayNight;
    private Example model;
    private FusedLocationProviderClient mFusedLocationClient;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_activity);
        locationForFiveDays.findViewById(R.id.fiveDaysLocation);
        if (PermissionUtils.Companion.isLocationEnable(this)){
           /* getDailyForecastLocation();*/
        }
    }

    public void getLocationForDailyForecast (String lat, String lng) {
        service.getCurrentLocation(String.format("%1s,%2s", lat, lng), getString(R.string.apikey3), "en-En")
                .enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                            locationForFiveDays.setText(model.getLocalizedName());
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable throwable) {
                        Toast.makeText(FiveDaysForecast.this, "Подключение к интернету отсутсвует", Toast.LENGTH_SHORT).show();
                    }
                });
    }

  /*  @SuppressLint("MissingPermission")
    public void getDailyForecastLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {

            public void onSuccess(Location location) {
                Toast.makeText(getApplicationContext(), String.valueOf(location.getLatitude() + " | " + String.valueOf(location.getLongitude())),
                        Toast.LENGTH_LONG).show();
                if (location != null) {
                    getLocationForDailyForecast(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
                    getDailyWeather("222844");
                }
            }
        });
    }

    public void getDailyWeather(String locationKey) {
        service.getCurrentWeather(locationKey, getString(R.string.apikey2), "en",true).
                enqueue(new Callback<List<CurrentWeather>>() {
                    @Override
                    public void onResponse(Call<List<CurrentWeather>> call, Response<List<CurrentWeather>> response) {
                        Log.d("Response ", response.toString());
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                        List<CurrentWeather> dailyForecast = response.body();
                        if (response.isSuccessful() && response.body() != null) {

                        } else {
                            Toast.makeText(getApplicationContext(), "Сервер не отвечает", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CurrentWeather>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Подключения к интернету отсутсвует", Toast.LENGTH_LONG).show();
                    }
                });
    }*/
}
