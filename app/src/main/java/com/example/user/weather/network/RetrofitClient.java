package com.example.user.weather.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 24.12.2018.
 */

public class RetrofitClient {
    private static final String BASE_URL = "http://dataservice.accuweather.com/";

    public static Retrofit getRetrofitInstance(){
        return  new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    private static ApiService getApiService(){
        return getRetrofitInstance().create(ApiService.class);
    }
}
