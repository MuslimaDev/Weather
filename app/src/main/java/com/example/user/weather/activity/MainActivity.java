package com.example.user.weather.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.weather.R;
import com.example.user.weather.Weather;
import com.example.user.weather.models.Example;
import com.example.user.weather.models.currentWeatherModels.CurrentModel;
import com.example.user.weather.network.RetrofitService;
import com.example.user.weather.utils.Constans;
import com.example.user.weather.utils.PermissionUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LocationListener, View.OnClickListener {
    private FusedLocationProviderClient mFusedLocationClient;
    private TextView currentLocation, temperature, feelsLike, wind, visibility;
    private ImageView imageView;
    private RetrofitService service;
    private Example model;
    private String locationKey;
    private CurrentModel currentModel;
    private Button button;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentLocation = findViewById(R.id.currentlocation);
        temperature = findViewById(R.id.temperature);
        feelsLike = findViewById(R.id.feelsLike);
        wind = findViewById(R.id.wind);
        visibility = findViewById(R.id.visibility);
        imageView = findViewById(R.id.weatherIcon);
        service = ((Weather) getApplication()).getService();
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        if (PermissionUtils.Companion.isLocationEnable(this)) {
            getCurrentLocation();
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, FiveDaysForecast.class);
        intent.putExtra("locationKey", locationKey);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item:
                Intent intent = new Intent(this, SearchPlaceActivity.class);
                startActivityForResult(intent, 0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getLocationForWeather(String lat, String lng) {
        service.getCurrentLocation(String.format("%1s,%2s", lat, lng), getString(R.string.apikey3), "en")
                .enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            model = response.body();
                            currentLocation.setText(model.getLocalizedName());
                            locationKey = model.getKey();
                            getCurrentWeather(locationKey);
                        } else {
                            Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable throwable) {
                        Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void getCurrentWeather(String locationKey) {
        service.getCurrentWeather(locationKey, getString(R.string.apikey3), "en", true).
                enqueue(new Callback<List<CurrentModel>>() {
                    @Override
                    public void onResponse(Call<List<CurrentModel>> call, Response<List<CurrentModel>> response) {
                        Log.d("Response ", response.toString());
                        List<CurrentModel> currentWeather = response.body();
                        currentModel = currentWeather.get(0);
                        if (response.isSuccessful() && response.body() != null) {
                            temperature.setText(currentModel.getTemperature().getMetric().getValue().toString() + "°" + currentModel.getTemperature().getMetric().getUnit());
                            feelsLike.setText(currentModel.getRealFeelTemperature().getMetric().getValue().toString() + "°" + currentModel.getRealFeelTemperature().getMetric().getUnit());
                            wind.setText(currentModel.getWind().getSpeed().getMetric().getValue().toString() + " " + currentModel.getWind().getSpeed().getMetric().getUnit());
                            visibility.setText(currentModel.getVisibility().getMetric().getValue().toString() + " " + currentModel.getVisibility().getMetric().getUnit());
                            int icon = currentModel.getWeatherIcon();
                            String imageUrl;
                            if (icon < 10) {
                                imageUrl = String.format(Constans.ICONS_URL, icon);
                            } else {
                                imageUrl = String.format(Constans.ICONS_URLMORE, icon);
                            }
                            Picasso.get().load(imageUrl).into(imageView);
                        } else {
                            Toast.makeText(getApplicationContext(), "Server is not responding", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CurrentModel>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "No internet connection", Toast.LENGTH_LONG).show();
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
        }
    };

    @SuppressLint("MissingPermission")
    public void getCurrentLocation() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {

            public void onSuccess(Location location) {
                if (location != null) {
                    getLocationForWeather(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        locationKey = data.getStringExtra("locationKey");
        currentLocation.setText(data.getStringExtra("cityName"));
        getCurrentWeather(data.getStringExtra("locationKey"));
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
