package com.example.lagranjaapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.lagranjaapp.model.Actividad;
import com.example.lagranjaapp.model.Animal;
import com.example.lagranjaapp.webService.WebServiceClient;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgregarAnimal extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText name;
    private DatePicker fecha;
    private EditText descripcion;
    private EditText machos;
    private Spinner etapa;
    private int intEtapa;
    private WebServiceClient client;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private Retrofit retrofit;
    private String[] etapas = {"Inicio", "Crecimiento", "Engorda"};
    private ArrayAdapter<String> comboAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_animal);

        ActionBar actBar = getSupportActionBar();
        actBar.setTitle("Agregar animal");


        name = (EditText) findViewById(R.id.nameCamada);
        fecha = (DatePicker) findViewById(R.id.fechaPicker);
        descripcion = (EditText) findViewById(R.id.descripcion);
        machos = (EditText) findViewById(R.id.machos);
        etapa = (Spinner) findViewById(R.id.etapa);
        comboAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, etapas);
        etapa.setAdapter(comboAdapter);
        etapa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("La posicion es: "+position);
                switch (parent.getId()){
                    case R.id.etapa:
                        intEtapa = position+1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://granjapp2.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        client = retrofit.create(WebServiceClient.class);

    }

    private void createPosts(String fecha, String name, int hembras, int machos, int etapa){

        Animal animal = new Animal(fecha, name, 3, hembras,machos, 1, etapa, 1);
        Call<Animal> call = client.postAnimal(animal);

        call.enqueue(new Callback<Animal>() {
            @Override
            public void onResponse(Call<Animal> call, Response<Animal> response) {
                Animal animalResponse = response.body();
            }

            @Override
            public void onFailure(Call<Animal> call, Throwable t) {

            }
        });
    }

    public void agregar (View v){
        Intent intent = new Intent(this, MainActivity.class);
        String name1 = name.getText().toString();
        String fecha1 = "";
        if(fecha.getMonth()<9){
            if(fecha.getDayOfMonth()<10){
                fecha1 = String.valueOf(fecha.getYear())+"-0"+String.valueOf(fecha.getMonth()+1)+"-0"+String.valueOf(fecha.getDayOfMonth());
            } else {
                fecha1 = String.valueOf(fecha.getYear())+"-0"+String.valueOf(fecha.getMonth()+1)+"-"+String.valueOf(fecha.getDayOfMonth());
            }
        } else {
            if(fecha.getDayOfMonth()<10){
                fecha1 = String.valueOf(fecha.getYear())+"-"+String.valueOf(fecha.getMonth()+1)+"-0"+String.valueOf(fecha.getDayOfMonth());
            } else {
                fecha1 = String.valueOf(fecha.getYear())+"-"+String.valueOf(fecha.getMonth()+1)+"-"+String.valueOf(fecha.getDayOfMonth());
            }
        }
        int descripcion1 = Integer.parseInt(descripcion.getText().toString());
        int machos1 = Integer.parseInt(machos.getText().toString());
        int etapa1 = intEtapa;
        createPosts(name1, fecha1, descripcion1, machos1, etapa1);


        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
