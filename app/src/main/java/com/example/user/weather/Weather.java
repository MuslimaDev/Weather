package com.example.user.weather;

import android.app.Application;

import com.example.user.weather.network.RetrofitClient;
import com.example.user.weather.network.RetrofitService;

public class Weather extends Application {
    private RetrofitService service;

    @Override
    public void onCreate() {
        super.onCreate();
        service = RetrofitClient.getApiService();
    }

    public RetrofitService getService() {
        return service;
    }
}
