package com.example.lagranjaapp;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lagranjaapp.Adapter.VerEquipoAdapter;
import com.example.lagranjaapp.model.DataEquipos;
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

public class VerEquipo extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VerEquipoAdapter adapter;
    private List<DataEquipos> equipos;
    private Retrofit retrofit;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private WebServiceClient client;
    private String id = "";
    private ActionBar actBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_equipo);
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
        if (isNoNull(client.getEquipos())) {
            Call<List<DataEquipos>> call = client.getEquipos();
            call.enqueue(new Callback<List<DataEquipos>>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call<List<DataEquipos>> call, Response<List<DataEquipos>> response) {
                    if (isCreated(response.body())) {
                        List<DataEquipos> tmp = response.body();
                        adapter.setData(tmp);
                    }
                }

                @Override
                public void onFailure(Call<List<DataEquipos>> call, Throwable t) {
                    Log.d("TAG1", "Error: " + t.getMessage());
                }
            });
        }
    }

    public static boolean isNoEmpty(Bundle extras){
        if (extras != null)
            return true;
        else
            return false;
    }

    public static boolean isNoNull(Call<List<DataEquipos>> call){
        if (call != null)
            return true;
        else
            return false;
    }

    public static boolean isCreated(List<DataEquipos> tmp){
        if (tmp != null)
            return true;
        else
            return false;
    }

    private void setUpView(){
        equipos = new ArrayList<>();
        adapter = new VerEquipoAdapter(equipos);
        recyclerView = findViewById(R.id.recyclerViewVerEquipo);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(lim);
        recyclerView.setAdapter(adapter);
        //actBar = getSupportActionBar();
    }
}
