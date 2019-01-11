package com.example.user.weather.network;

import com.example.user.weather.models.currentWeatherModels.CurrentModel;
import com.example.user.weather.models.Example;
import com.example.user.weather.models.searchPlaceModels.SearchPlaceModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("locations/v1/cities/geoposition/search.json")
    Call<Example> getCurrentLocation(@Query("q") String q,
                                     @Query("apikey") String apikey,
                                     @Query("language") String language);

    @GET("currentconditions/v1/{name}")
    Call<List<CurrentModel>> getCurrentWeather(@Path("name") String name,
                                               @Query("apikey") String apikey,
                                               @Query("language") String language,
                                               @Query("details") boolean details);

    @GET("/locations/v1/cities/autocomplete")
    Call<List<SearchPlaceModel>> searchingPlace(@Query("q") String q,
                                                @Query("apikey") String apikey,
                                                @Query("language") String language);


}