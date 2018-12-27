package com.example.user.weather.network;

import com.example.user.weather.models.location.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("locations/v1/cities/geoposition/search.json")
    Call<Example> getCurrentLocation(@Query("q") String q,
                                     @Query("apikey") String apikey,
                                     @Query("language") String language);


}
