package com.example.user.weather.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import com.example.user.weather.R;
import com.example.user.weather.models.locationModels.ExampleLocation;
import com.example.user.weather.network.RetrofitService;
import com.example.user.weather.utils.PermissionUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FiveDaysForecast extends Activity {
    private RetrofitService service;
    private TextView locationForFiveDays;
    private ExampleLocation model;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_five_days);
        locationForFiveDays.findViewById(R.id.fiveDaysLocation);
        if (PermissionUtils.Companion.isLocationEnable(this)) {

        }
    }

    public void getLocationForFiveDays (String lat, String lng) {
        service.getCurrentLocation(String.format("%1s,%2s", lat, lng), "zrwVOZwuTlXRj6CpdEsT1MWqqvAZAEAE", "en-En")
                .enqueue(new Callback<ExampleLocation>() {
                    @Override
                    public void onResponse(Call<ExampleLocation> call, Response<ExampleLocation> response) {
                            locationForFiveDays.setText(model.getLocalizedName());
                    }

                    @Override
                    public void onFailure(Call<ExampleLocation> call, Throwable throwable) {
                        Toast.makeText(FiveDaysForecast.this, "Подключение к интернету отсутсвует", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
