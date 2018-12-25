package com.example.user.weather.network;

import com.example.user.weather.models.location.AdministrativeArea;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by User on 24.12.2018.
 */

public interface ApiService {
    @GET("http://dataservice.accuweather.com/locations/v1/cities/geoposition/search?")
    Call<List<AdministrativeArea>> getMyLocation();
}
