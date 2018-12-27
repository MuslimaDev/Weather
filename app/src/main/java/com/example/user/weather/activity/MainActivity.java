package com.example.user.weather.activity;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.example.user.weather.R;
import com.example.user.weather.Weather;
import com.example.user.weather.models.location.Example;
import com.example.user.weather.network.RetrofitService;
import com.example.user.weather.utils.PermissionUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private FusedLocationProviderClient mFusedLocationClient;
    private TextView currentlocation;
    private TextView temperature;
    private TextView night;
    private TextView maximum;
    private RetrofitService service;
    private Example model;
    private String locationKey;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentlocation = findViewById(R.id.currentlocation);
        temperature = findViewById(R.id.temperature);
        night = findViewById(R.id.nightTempInf);
        maximum = findViewById(R.id.maxTempInf);
        TextView minimum = findViewById(R.id.minimumTemp);
        service = ((Weather)getApplication()).getService();

        if (PermissionUtils.Companion.isLocationEnable(this)) {
            getCurrentLocation();
        }
    }

        public void getLocationForWeather(String lat,String lng) {
            service.getCurrentLocation(String.format("%1s,%2s", lat, lng), getString(R.string.apikey), "en-En")
                    .enqueue(new Callback<Example>() {
                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {
                    if (response.isSuccessful() && response.body() != null) {
                            model = response.body();
                            currentlocation.setText(model.getLocalizedName());
                            locationKey = model.getKey();

                            Toast.makeText(getApplicationContext(), "My key: " + model.getKey().toString(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Сервер не отвечает", Toast.LENGTH_LONG).show();
                        }
                    }

                @Override
                public void onFailure(Call<Example> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Подключенияе к интернету отсутсвует", Toast.LENGTH_SHORT).show();
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

    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            Log.d(TAG,"onLocationChanged: " + locationResult.getLastLocation().getLongitude() + " " + locationResult.getLastLocation().getLatitude());
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
    public void getCurrentLocation(){
        mFusedLocationClient =  LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {

            public void onSuccess(Location location) {
                Toast.makeText(getApplicationContext(),String.valueOf(location.getLatitude() + " | " + String.valueOf(location.getLongitude())),
                        Toast.LENGTH_LONG).show();
                    getLocationForWeather(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));

            }
        });
    }
}
