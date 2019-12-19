package com.example.lagranjaapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lagranjaapp.Adapter.PlantingAdapter;
import com.example.lagranjaapp.Adapter.SubzonaAdapter;
import com.example.lagranjaapp.Adapter.ZonaAdapter;
import com.example.lagranjaapp.model.Cultivo;
import com.example.lagranjaapp.model.Data;
import com.example.lagranjaapp.model.DataCultivos;
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
    private Cultivo cultivo;
    private String name;
    private ActionBar actBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planting);
        Bundle extras = getIntent().getExtras();
        if (isNoEmpty(extras)) {
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
        if (isNoNull(client.getPlantings(id))) {
            Call<Subzone> call = client.getPlantings(id);
            call.enqueue(new Callback<Subzone>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call<Subzone> call, Response<Subzone> response) {
                    if (isCreated(response.body())) {
                        Subzone tmp = response.body();
                        ArrayList<DataPlanting> data = tmp.getPlantings();
                        actBar.setTitle(tmp.getName());
                        lanzarPeticion2(data);
                    }
                }

                @Override
                public void onFailure(Call<Subzone> call, Throwable t) {
                    Log.d("TAG1", "Error: " + t.getMessage());
                }
            });
        }
    }

    private void lanzarPeticion2(List<DataPlanting> j){
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://granjapp2.appspot.com/crops/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();

        for(int i = 0; i <j.size(); i++) {
            int counter = i;

            WebServiceClient client = retrofit.create(WebServiceClient.class);
            if (isNoNullCultivo(client.getCultivo(String.valueOf(j.get(i).getCultivo())))) {
                Call<Cultivo> call = client.getCultivo(String.valueOf(j.get(i).getCultivo()));
                call.enqueue(new Callback<Cultivo>() {
                    @Override
                    public void onResponse(Call<Cultivo> call, Response<Cultivo> response) {
                        if (isCreatedCultivo(response.body())) {
                            cultivo = response.body();
                            j.get(counter).setCultivo(cultivo.getName());
                            adapter.setData(j);
                        }
                    }

                    @Override
                    public void onFailure(Call<Cultivo> call, Throwable t) {
                        Log.d("TAG1", "Error: " + t.getMessage());
                    }
                });
            }
        }


    }

    private void setUpView(){
        plantings = new ArrayList<>();
        adapter = new PlantingAdapter(plantings);
        recyclerView = findViewById(R.id.recyclerViewPlanting);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(lim);
        recyclerView.setAdapter(adapter);
        actBar = getSupportActionBar();
    }

    public static boolean isNoEmpty(Bundle extras){
        if (extras != null)
            return true;
        else
            return false;
    }

    public static boolean isNoNull(Call<Subzone> call){
        if (call != null)
            return true;
        else
            return false;
    }

    public static boolean isNoNullCultivo(Call<Cultivo> call){
        if (call != null)
            return true;
        else
            return false;
    }

    public static boolean isCreated(Subzone tmp){
        if (tmp != null)
            return true;
        else
            return false;
    }

    public static boolean isCreatedCultivo(Cultivo tmp){
        if (tmp != null)
            return true;
        else
            return false;
    }

    public void agregarPlanting (View v){
        Intent intent = new Intent(this, AgregarPlanting.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
