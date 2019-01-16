package com.example.user.weather;

import android.app.Application;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.example.user.weather.network.RetrofitClient;
import com.example.user.weather.network.RetrofitService;

public class Weather extends MultiDexApplication {
    private RetrofitService service;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Weather", " OnCreate");
        service = RetrofitClient.getApiService();
    }

    public RetrofitService getService(){
        return service;
    }

}
