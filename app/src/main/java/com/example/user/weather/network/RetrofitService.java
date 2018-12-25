package com.example.user.weather.network;

import com.example.user.weather.models.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by User on 24.12.2018.
 */

public abstract class RetrofitService {
    @GET("locations/v1/cities/geoposition/search.json")
    abstract Call<Example> getCurrentLocation(@Query("q") String q,
                                              @Query("apikey") String apikey,
                                              @Query("language") String language);
}