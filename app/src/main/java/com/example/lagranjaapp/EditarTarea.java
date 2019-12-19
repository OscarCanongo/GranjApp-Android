package com.example.lagranjaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.lagranjaapp.Adapter.EditarActividadAdapter;
import com.example.lagranjaapp.model.DataActividades;
import com.example.lagranjaapp.model.DataUsuario;
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

public class EditarTarea extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditarActividadAdapter adapter;
    private List<DataActividades> actividades;
    private List<DataActividades> actividadesTMP;
    private List<DataUsuario> usuarios;
    private Retrofit retrofit;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private WebServiceClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_tarea);

        setUpView();
        lanzarPeticion2();
    }

    private void lanzarPeticion2 (){
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://granjapp2.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();

        WebServiceClient client = retrofit.create(WebServiceClient.class);
        Call<List<DataUsuario>> call = client.getUsuarios();

        call.enqueue(new Callback<List<DataUsuario>>() {
            @Override
            public void onResponse(Call<List<DataUsuario>> call, Response<List<DataUsuario>> response) {
                usuarios = response.body();
                int actCounter = 0;
                for (int i = 0; i < usuarios.size(); i++){
                    actividadesTMP = usuarios.get(i).getActivities();
                    for (int j = 0; j< actividadesTMP.size(); j++){
                        actividades.add(actividadesTMP.get(j));
                        actividades.get(actCounter).setUsuario(usuarios.get(i).getName());
                        actCounter++;
                    }

                }
                adapter.setData(actividades);
            }

            @Override
            public void onFailure(Call<List<DataUsuario>> call, Throwable t) {
                Log.d("TAG1", "Error: " + t.getMessage());
            }
        });
    }



    private void setUpView(){
        actividades = new ArrayList<>();
        usuarios = new ArrayList<>();
        adapter = new EditarActividadAdapter(actividades);
        recyclerView = findViewById(R.id.recyclerViewEditarTarea);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(lim);
        recyclerView.setAdapter(adapter);
    }
}
