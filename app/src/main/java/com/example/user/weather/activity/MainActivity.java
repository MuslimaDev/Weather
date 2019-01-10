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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.weather.R;
import com.example.user.weather.Weather;
import com.example.user.weather.models.curWeatherModels.CurrentWeather;
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
    private TextView currentLocation, temperature, feelsLike, wind, visibility;
    private ImageView icon;
    private RetrofitService service;
    private ExampleLocation model;
    private String locationKey;
    private CurrentWeather currentModel;
    private Button button;
    private DailyForecast dailyForecast;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentLocation = findViewById(R.id.currentlocation);
        temperature = findViewById(R.id.temperature);
        feelsLike = findViewById(R.id.feelsLike);
        wind = findViewById(R.id.wind);
        visibility = findViewById(R.id.visibility);
        service = ((Weather) getApplication()).getService();

        if (PermissionUtils.Companion.isLocationEnable(this)) {
            getCurrentLocation();
        }

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openFiveDaysForecastActivity();
            }
        });

    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, FiveDaysForecast.class);
        startActivity(intent);
    }

        private void openFiveDaysForecastActivity() {
        Intent intent = new Intent(this, FiveDaysForecast.class);
        startActivity(intent);
    }

    public void getLocationForWeather(String lat, String lng) {
        service.getCurrentLocation(String.format("%1s,%2s", lat, lng), getString(R.string.apikey2), "en-En")
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
                        Toast.makeText(MainActivity.this, "Подключение к интернету отсутсвует 1", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void getCurrentWeather(String locationKey) {
        service.getCurrentWeather(locationKey, getString(R.string.apikey2), "en",true).
                enqueue(new Callback<List<CurrentWeather>>() {
                    @Override
                    public void onResponse(Call<List<CurrentWeather>> call, Response<List<CurrentWeather>> response) {
                        Log.d("Response ", response.toString());
                        Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
                        List<CurrentWeather> currentWeather = response.body();
                        currentModel = currentWeather.get(0);
                        if (response.isSuccessful() && response.body() != null) {
                            temperature.setText(currentModel.getTemperature().getMetric().getValue().toString() + "°" + currentModel.getTemperature().getMetric().getUnit());
                         //   feelsLike.setText(currentModel.getRealFeelTemperature().getMetric().getValue().toString());
                         //   wind.setText(currentModel.getWind().getSpeed().getMetric().getValue().toString() + currentModel.getWind().getSpeed().getMetric().getUnit());
                           // visibility.setText(currentModel.getVisibility().getMetric().getValue().toString() + currentModel.getVisibility().getMetric().getUnit());
                        } else {
                            Toast.makeText(getApplicationContext(), "Сервер не отвечает", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CurrentWeather>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Подключения к интернету отсутсвует 2", Toast.LENGTH_LONG).show();
                        Log.d("Throwable",t.toString());
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
                /*Toast.makeText(getApplicationContext(), String.valueOf("Feel like: " + currentModel.getRealFeelTemperature().getMetric().getValue().toString()),
                        Toast.LENGTH_LONG).show();*/
                if (location != null) {
                    getLocationForWeather(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
                Intent intent = new Intent(this, SearchPlaceActivity.class);
                startActivityForResult(intent, 0);
        return super.onOptionsItemSelected(item);
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