package com.example.lagranjaapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.lagranjaapp.Adapter.PlantingAdapter;
import com.example.lagranjaapp.Adapter.SubzonaAdapter;
import com.example.lagranjaapp.Adapter.ZonaAdapter;
import com.example.lagranjaapp.model.Data;
import com.example.lagranjaapp.model.DataPlanting;
import com.example.lagranjaapp.model.DataSubzone;
import com.example.lagranjaapp.model.DataZonas;
import com.example.lagranjaapp.model.Subzone;
import com.example.lagranjaapp.model.Zona;
import com.example.lagranjaapp.webService.WebServiceClient;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Plantings extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PlantingAdapter adapter;
    private List<DataPlanting> plantings;
    private Retrofit retrofit;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private WebServiceClient client;
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planting);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            id = extras.getString("id");
        }
        setUpView();
        lanzarPeticion();
    }

    private void lanzarPeticion(){
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://granjapp2.appspot.com/subzone/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();

        WebServiceClient client = retrofit.create(WebServiceClient.class);
        Call<Subzone> call = client.getPlantings(id);
        call.enqueue(new Callback<Subzone>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Subzone> call, Response<Subzone> response) {
                Subzone tmp = response.body();
                ArrayList <DataPlanting> data = tmp.getPlantings();
                adapter.setData(data);
            }

            @Override
            public void onFailure(Call<Subzone> call, Throwable t) {
                Log.d("TAG1", "Error: " + t.getMessage());
            }
        });
    }



    private void setUpView(){
        plantings = new ArrayList<>();
        adapter = new PlantingAdapter(plantings);
        recyclerView = findViewById(R.id.recyclerViewPlanting);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(lim);
        recyclerView.setAdapter(adapter);
    }
}
