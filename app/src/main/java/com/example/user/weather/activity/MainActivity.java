package com.example.user.weather.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.user.weather.R;
import com.example.user.weather.Weather;
import com.example.user.weather.models.сurrentWeatherModels.CurrentModel;
import com.example.user.weather.models.сurrentWeatherModels.Maximum;
import com.example.user.weather.models.сurrentWeatherModels.Minimum;
import com.example.user.weather.models.DailyForecast;
import com.example.user.weather.models.locationModels.ExampleLocation;
import com.example.user.weather.network.RetrofitService;
import com.example.user.weather.utils.PermissionUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private FusedLocationProviderClient mFusedLocationClient;
    private TextView currentLocation, temperature, maximum, minimum;
    private RetrofitService service;
    private ExampleLocation model;
    private String locationKey;
    private CurrentModel currentModel;
    private Button button;
    private DailyForecast dailyForecast;
    private Maximum max;
    private Minimum min;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentLocation = findViewById(R.id.currentlocation);
        temperature = findViewById(R.id.temperature);
        maximum = findViewById(R.id.maxTempInf);
        minimum = findViewById(R.id.minTempInf);
        service = ((Weather) getApplication()).getService();

        if (PermissionUtils.Companion.isLocationEnable(this)) {
            getCurrentLocation();
        }
/*
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFiveDaysForecastActivity();
            }
        });*/

    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, FiveDaysForecast.class);
        startActivity(intent);
    }

    /*    private void openFiveDaysForecastActivity() {
        Intent intent = new Intent(this, FiveDaysForecast.class);
        startActivity(intent);
    }*/

    public void getLocationForWeather(String lat, String lng) {
        service.getCurrentLocation(String.format("%1s,%2s", lat, lng),getString(R.string.apikey), "en-En")
                .enqueue(new Callback<ExampleLocation>() {
                    @Override
                    public void onResponse(Call<ExampleLocation> call, Response<ExampleLocation> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            model = response.body();
                            currentLocation.setText(model.getLocalizedName());
                            locationKey = model.getKey();
                            getCurrentWeather(locationKey);
                            Toast.makeText(getApplicationContext(), "My key: " + model.getKey().toString(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ExampleLocation> call, Throwable throwable) {
                        Toast.makeText(MainActivity.this, "Подключение к интернету отсутсвует", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void getCurrentWeather(String locationKey) {
        service.getCurrentWeather(locationKey, getString(R.string.apikey), "en").
                enqueue(new Callback<List<CurrentModel>>() {
                    @Override
                    public void onResponse(Call<List<CurrentModel>> call, Response<List<CurrentModel>> response) {
                        Log.d("Response ", response.toString());
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                        List<CurrentModel> dayForecast = response.body();
                        currentModel = dayForecast.get(0);
                       /*max = dayForecast.get(0).getTemperatureSummary().getPast12HourRange().getMaximum();
                       min = dayForecast.get(0).getTemperatureSummary().getPast12HourRange().getMinimum();*/
                        if (response.isSuccessful() && response.body() != null) {
                            temperature.setText(currentModel.getTemperature().getMetric().getValue().toString() + "°");
                        /* maximum.setText(max.toString());
                           minimum.setText(min.toString());*/
                        } else {
                            Toast.makeText(getApplicationContext(), "Сервер не отвечает", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CurrentModel>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Подключения к интернету отсутсвует", Toast.LENGTH_LONG).show();
                    }
                });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {
            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();
                }
            }
        }
    }

    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            Log.d(TAG, "onLocationChanged: " + locationResult.getLastLocation().getLongitude() + " " + locationResult.getLastLocation().getLatitude());
        }
    };

    @SuppressLint("MissingPermission")
    public void startLocationUpdates() {
        int INTERVAL_UPDATES = 5000;
        int MINIMUM_INTERVAL_UPDATES = 1000;

        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(INTERVAL_UPDATES);
        locationRequest.setFastestInterval(MINIMUM_INTERVAL_UPDATES);
        mFusedLocationClient.requestLocationUpdates(locationRequest, mLocationCallback,
                Looper.myLooper());
    }

    @SuppressLint("MissingPermission")
    public void getCurrentLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {

            public void onSuccess(Location location) {
                Toast.makeText(getApplicationContext(), String.valueOf(location.getLatitude() + " | " + String.valueOf(location.getLongitude())),
                        Toast.LENGTH_LONG).show();
                if (location != null) {
                    getLocationForWeather(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
                }
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }
}