package com.example.lagranjaapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.lagranjaapp.Adapter.SubzonaAdapter;
import com.example.lagranjaapp.Adapter.ZonaAdapter;
import com.example.lagranjaapp.model.Data;
import com.example.lagranjaapp.model.DataSubzone;
import com.example.lagranjaapp.model.DataZonas;
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

public class Subzona extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SubzonaAdapter adapter;
    private List<DataSubzone> subzonas;
    private Retrofit retrofit;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private WebServiceClient client;
    private String id = "";
    private ActionBar actBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subzona);
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
                .baseUrl("http://granjapp2.appspot.com/zone/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();

        WebServiceClient client = retrofit.create(WebServiceClient.class);
        if (isNoNull(client.getSubzones(id))) {
            Call<Zona> call = client.getSubzones(id);
            call.enqueue(new Callback<Zona>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call<Zona> call, Response<Zona> response) {
                    if (isCreated(response.body())) {
                        Zona tmp = response.body();
                        ArrayList<DataSubzone> data = tmp.getSubzones();
                        actBar.setTitle(tmp.getName());
                        adapter.setData(data);
                    }
                }

                @Override
                public void onFailure(Call<Zona> call, Throwable t) {
                    Log.d("TAG1", "Error: " + t.getMessage());
                }
            });
        }
    }

    private void setUpView(){
        subzonas = new ArrayList<>();
        adapter = new SubzonaAdapter(subzonas);
        recyclerView = findViewById(R.id.recyclerViewSubzona);
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

    public static boolean isNoNull(Call<Zona> call){
        if (call != null)
            return true;
        else
            return false;
    }

    public static boolean isCreated(Zona tmp){
        if (tmp != null)
            return true;
        else
            return false;
    }
}
