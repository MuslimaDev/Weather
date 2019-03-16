package com.example.user.weather.network;

import com.example.user.weather.model.currentWeatherModels.CurrentModel;
import com.example.user.weather.model.Example;
import com.example.user.weather.model.forecastModels.Forecast;
import com.example.user.weather.model.searchPlaceModels.SearchPlaceModel;
import com.example.user.weather.utils.Constans;

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

    @GET(Constans.URL_FORECAST + "{name}")
    Call<Forecast> getWeatherForecast(@Path("name") String name,
                                      @Query("apikey") String apikey,
                                      @Query("language") String language,
                                      @Query("details") boolean details,
                                      @Query("metric") boolean metric);


}