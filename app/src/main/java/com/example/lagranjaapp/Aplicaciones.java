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

import com.example.lagranjaapp.Adapter.AplicacionAdapter;
import com.example.lagranjaapp.Adapter.PlantingAdapter;
import com.example.lagranjaapp.Adapter.SubzonaAdapter;
import com.example.lagranjaapp.Adapter.ZonaAdapter;
import com.example.lagranjaapp.model.Cultivo;
import com.example.lagranjaapp.model.Data;
import com.example.lagranjaapp.model.DataAplicacion;
import com.example.lagranjaapp.model.DataCultivos;
import com.example.lagranjaapp.model.DataPlanting;
import com.example.lagranjaapp.model.DataSubzone;
import com.example.lagranjaapp.model.DataZonas;
import com.example.lagranjaapp.model.Recurso;
import com.example.lagranjaapp.model.Subzone;
import com.example.lagranjaapp.model.TipoRecurso;
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

public class Aplicaciones extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AplicacionAdapter adapter;
    private List<DataAplicacion> aplicaciones;
    private Recurso recurso;
    private TipoRecurso tipoRecurso;
    private Retrofit retrofit;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private WebServiceClient client;
    private String id = "";
    private ActionBar actBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplicaciones);
        setUpView();
        lanzarPeticion();
    }


    private void lanzarPeticion(){
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://granjapp2.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();

        WebServiceClient client = retrofit.create(WebServiceClient.class);
        if (isNoNull(client.getAplicaciones())) {
            Call<List<DataAplicacion>> call = client.getAplicaciones();
            call.enqueue(new Callback<List<DataAplicacion>>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call<List<DataAplicacion>> call, Response<List<DataAplicacion>> response) {
                    if (isNoNullAplicaciones(response.body())) {
                        aplicaciones = response.body();
                        lanzarPeticion2(aplicaciones);
                    }
                }

                @Override
                public void onFailure(Call<List<DataAplicacion>> call, Throwable t) {
                    Log.d("TAG1", "Error: " + t.getMessage());
                }
            });
        }
    }


    private void lanzarPeticion2(List<DataAplicacion> j){
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://granjapp2.appspot.com/resources/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();

        for(int i = 0; i <j.size(); i++) {
            int counter = i;

            WebServiceClient client = retrofit.create(WebServiceClient.class);
            if (isNoNullRecurso(client.getRecurso(String.valueOf(j.get(i).getRecurso())))) {
                Call<Recurso> call = client.getRecurso(String.valueOf(j.get(i).getRecurso()));
                call.enqueue(new Callback<Recurso>() {
                    @Override
                    public void onResponse(Call<Recurso> call, Response<Recurso> response) {
                        if (isCreated(response.body())) {
                            recurso = response.body();
                            j.get(counter).setRecurso(recurso.getName());
                            adapter.setData(j);
                        }
                    }

                    @Override
                    public void onFailure(Call<Recurso> call, Throwable t) {
                        Log.d("TAG1", "Error: " + t.getMessage());
                    }
                });
            }
        }

    }

    private void setUpView(){
        aplicaciones = new ArrayList<>();
        adapter = new AplicacionAdapter(aplicaciones);
        recyclerView = findViewById(R.id.recyclerViewAplicaciones);
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

    public static boolean isNoNull(Call<List<DataAplicacion>> call){
        if (call != null)
            return true;
        else
            return false;
    }

    public static boolean isNoNullAplicaciones(List<DataAplicacion>  call){
        if (call != null)
            return true;
        else
            return false;
    }

    public static boolean isNoNullRecurso( Call<Recurso> call){
        if (call != null)
            return true;
        else
            return false;
    }

    public static boolean isCreated(Recurso recurso){
        if (recurso != null)
            return true;
        else
            return false;
    }

    public void siguiente (View v){
        Intent intent = new Intent(this, AgregarAplicacion.class);
        startActivity(intent);
    }
}