package com.example.lagranjaapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lagranjaapp.model.Actividad;
import com.example.lagranjaapp.model.Cultivo;
import com.example.lagranjaapp.model.DataCultivos;
import com.example.lagranjaapp.model.Planting;
import com.example.lagranjaapp.model.Usuario;
import com.example.lagranjaapp.webService.WebServiceClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgregarPlanting extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private Spinner cultivo;
    private WebServiceClient client;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private Retrofit retrofit;
    private int id, intCultivo;
    private List<DataCultivos> cultivos2;
    private ArrayList<String> cultivosArr = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_planting);

        Bundle extras = getIntent().getExtras();
        if (isNoEmpty(extras)) {
            id = Integer.parseInt(extras.getString("id"));
        }

        cultivo = findViewById(R.id.cultivo);

        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://granjapp2.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        client = retrofit.create(WebServiceClient.class);
        ActionBar actBar = getSupportActionBar();
        actBar.setTitle("Agregar Siembra");
        lanzarPeticion(new OnCultivoResponse() {
            @Override
            public void cultivos(List<DataCultivos> cultivos) {
                for (int i=0; i<cultivos.size();i++)
                    cultivosArr.add(cultivos.get(i).getName());
                ArrayAdapter<CharSequence> comboAdapter = new ArrayAdapter(AgregarPlanting.this,android.R.layout.simple_spinner_item,cultivosArr);
                comboAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                cultivo.setAdapter(comboAdapter);
                cultivo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        intCultivo = Integer.parseInt(cultivos.get(position).getId());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }

    public static boolean isNoEmpty(Bundle extras){
        if (extras != null)
            return true;
        else
            return false;
    }

    private void lanzarPeticion(OnCultivoResponse callback){
        Call<List<DataCultivos>> call = client.getCultivos();
        call.enqueue(new Callback<List<DataCultivos>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<DataCultivos>>  call, Response<List<DataCultivos>> response) {
                if(!response.isSuccessful()){
                    //Lance un toast
                }

                callback.cultivos((response.body()));

            }

            @Override
            public void onFailure(Call<List<DataCultivos>> call, Throwable t) {
                Log.d("TAG1", "Error: " + t.getMessage());
            }
        });
    }

    private void createPosts(int subzona, int cultivo){

        Planting planting = new Planting(1, cultivo, subzona);
        Call<Planting> call = client.postPlanting(planting);

        call.enqueue(new Callback<Planting>() {
            @Override
            public void onResponse(Call<Planting> call, Response<Planting> response) {
                Planting plantingResponse = response.body();
            }

            @Override
            public void onFailure(Call<Planting> call, Throwable t) {

            }
        });
    }

    public void agregarPlanting (View v){
        Intent intent = new Intent(this, MainActivity.class);
        int cultivo1 = intCultivo;

        createPosts(id, cultivo1);


        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
