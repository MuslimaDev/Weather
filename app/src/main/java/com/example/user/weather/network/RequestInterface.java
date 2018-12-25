package com.example.user.weather.network;

import com.example.user.weather.models.location.AdministrativeArea;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by User on 24.12.2018.
 */

public interface RequestInterface {
    @GET("v1/articles?source=mashable&sortBy=top&apiKey=A3YqUmDKFilXCF9JRia7xHsSVtkiGOsv")
    Call<AdministrativeArea> getMyLocation();
}
