package com.example.user.weather.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.example.user.weather.R;
import com.example.user.weather.models.curWeatherModels.CurrentWeather;
import com.example.user.weather.models.locationModels.ExampleLocation;
import com.example.user.weather.network.RetrofitService;
import com.example.user.weather.utils.PermissionUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FiveDaysForecast extends Activity {
    private RetrofitService service;
    private TextView locationForFiveDays, firstDayMax, firstDayMin, firstDayNight;
    private ExampleLocation model;
    private FusedLocationProviderClient mFusedLocationClient;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_activity);
        locationForFiveDays.findViewById(R.id.fiveDaysLocation);
        if (PermissionUtils.Companion.isLocationEnable(this)){
            getDailyForecastLocation();
        }
    }

    public void getLocationForDailyForecast (String lat, String lng) {
        service.getCurrentLocation(String.format("%1s,%2s", lat, lng), getString(R.string.apikey2), "en-En")
                .enqueue(new Callback<ExampleLocation>() {
                    @Override
                    public void onResponse(Call<ExampleLocation> call, Response<ExampleLocation> response) {
                            locationForFiveDays.setText(model.getLocalizedName());
                    }

                    @Override
                    public void onFailure(Call<ExampleLocation> call, Throwable throwable) {
                        Toast.makeText(FiveDaysForecast.this, "Подключение к интернету отсутсвует", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @SuppressLint("MissingPermission")
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
    }
}
