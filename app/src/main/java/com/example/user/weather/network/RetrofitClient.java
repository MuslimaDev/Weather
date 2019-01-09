package com.example.user.weather.network;

import com.example.user.weather.utils.Constans;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL="http://dataservice.accuweather.com/";

    public static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(Constans.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitService getApiService(){
        return getRetrofitInstance().create(RetrofitService.class);
    }

}
