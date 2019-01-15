package com.example.user.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.example.user.weather.R;
import com.example.user.weather.Weather;
import com.example.user.weather.models.searchPlaceModels.SearchPlaceModel;
import com.example.user.weather.network.RetrofitService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPlaceActivity extends ActivityBase implements View.OnClickListener, AdapterView.OnItemClickListener{
    private RetrofitService service;
    private EditText editText;
    private ListView listView;
    private Button button;
    private List<SearchPlaceModel> searchPlaceModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        service = ((Weather) getApplication()).getService();
        editText = findViewById(R.id.edit_textSearch);
        listView = findViewById(R.id.list_town);
        listView.setOnItemClickListener(this);
        button = findViewById(R.id.searchButton);
        button.setOnClickListener(this);
    }


    private void forSearchPlace() {
        showProgressBar();
        service.searchingPlace(editText.getText().toString(), getString(R.string.apikey4), "ru-Ru")
                .enqueue(new Callback<List<SearchPlaceModel>>() {
                    @Override
                    public void onResponse(Call<List<SearchPlaceModel>> call, Response<List<SearchPlaceModel>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            searchPlaceModel = response.body();
                            ArrayList<String> arrayList = new ArrayList();
                            for (int i = 0; i < searchPlaceModel.size(); i++) {
                                arrayList.add(searchPlaceModel.get(i).getLocalizedName() + "\n" +
                                        searchPlaceModel.get(i).getAdministrativeArea().getLocalizedName() + ", " + searchPlaceModel.get(i).getCountry().getLocalizedName());
                            }
                            ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
                            listView.setAdapter(adapter);
                        } else {
                            Toast.makeText(getApplicationContext(), "Сервер не отвечает", Toast.LENGTH_LONG).show();
                        }
                        if (searchPlaceModel.size() == 0 && response.body() == null) {
                            Toast.makeText(getApplicationContext(), "Не найдено", Toast.LENGTH_LONG).show();
                        }

                        dismissProgressBar();
                    }

                    @Override
                    public void onFailure(Call<List<SearchPlaceModel>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        forSearchPlace();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (searchPlaceModel.get(position).getKey()==null&& searchPlaceModel.get(position).getLocalizedName()==null)return;
        Intent intent = new Intent();

        intent.putExtra("locationKey", searchPlaceModel.get(position).getKey());
        intent.putExtra("CityName", searchPlaceModel.get(position).getLocalizedName());
        setResult(RESULT_OK, intent);
        finish();
    }
}