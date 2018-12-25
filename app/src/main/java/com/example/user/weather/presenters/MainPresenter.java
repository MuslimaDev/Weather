package com.example.user.weather.presenters;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;


/**
 * Created by User on 18.12.2018.
 */

public class MainPresenter implements LocationListener {
    private FusedLocationProviderClient fusedLocationProviderClient;



    @Override
    public void onLocationChanged(Location location) {

    }

    public void getCurrentLocation(Activity activity){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);

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
