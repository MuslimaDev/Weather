package com.example.user.weather.network;

import com.example.user.weather.models.curWeatherModels.CurrentWeather;
import com.example.user.weather.models.locationModels.ExampleLocation;
import com.example.user.weather.models.searchingModels.SearchPlaceModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("locations/v1/cities/geoposition/search.json")
    Call<ExampleLocation> getCurrentLocation(@Query("q") String q,
                                             @Query("apikey") String apikey,
                                             @Query("language") String language);

    @GET("currentconditions/v1/{name}")
    Call<List<CurrentWeather>> getCurrentWeather(@Path("name") String name,
                                               @Query("apikey") String apikey,
                                               @Query("language") String language,
                                               @Query("details") boolean details);

    @GET("/locations/v1/cities/autocomplete")
    Call<List<SearchPlaceModel>> searchingPlace(@Query("q") String q,
                                                @Query("apikey") String apikey,
                                                @Query("language") String language);


}