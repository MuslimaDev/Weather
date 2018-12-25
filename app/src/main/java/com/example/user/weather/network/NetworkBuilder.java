package com.example.user.weather.network;

import com.example.user.weather.utils.Constans;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 24.12.2018.
 */

public class NetworkBuilder {
    public static NetworkBuilder instance = new NetworkBuilder();
    public Retrofit retrofit;

    private  static RetrofitService  service = null;
    public  static RetrofitService initService (){
        if (service==null) {
            service = new Retrofit.Builder()
                    .baseUrl(Constans.BASE_URL)
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofitService.class);
        }
        return service;
    }
    private static OkHttpClient getClient(){
        return new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request.Builder ongoing = chain.request()
                                .newBuilder()
                                .addHeader("Accept", "application/json;versions=1");
                        return chain.proceed(ongoing.build());

                    }
                }).build();
    }
    public void getLocation(double lat, double lng){

    }

}
