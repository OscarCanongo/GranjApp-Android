package com.example.lagranjaapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class EditarCamada extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText name, machos, hembras;
    private Spinner salud, etapa;
    private WebServiceClient client;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private Retrofit retrofit;
    private String id = "", nacimiento = "", txtName ="", txtEtapa="", txtMachos="", txtHembras="", txtSalud="";
    private String[] etapas = {"Inicio", "Crecimiento", "Engorda"};
    private String[] saludes = {"Saludable", "Enfermo"};
    private int intEtapas, intSaludes;
    private ArrayAdapter<String> comboAdapterEtapa, comboAdapterSalud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_camada);

        Bundle extras = getIntent().getExtras();
        if (isNoEmpty(extras)) {
            id = extras.getString("id");
            nacimiento = extras.getString("nacimiento");
            txtName = extras.getString("txtName");
            txtMachos = extras.getString("txtMachos");
            txtHembras = extras.getString("txtHembras");
            System.out.println(id);
        }
        name = (EditText) findViewById(R.id.name);
        name.setText(txtName);
        etapa = (Spinner) findViewById(R.id.etapa);
        machos = (EditText) findViewById(R.id.no_m);
        machos.setText(txtMachos);
        hembras = (EditText) findViewById(R.id.no_h);
        hembras.setText(txtHembras);  salud = (Spinner) findViewById(R.id.salud);


        comboAdapterEtapa = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, etapas);
        etapa.setAdapter(comboAdapterEtapa);
        etapa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getId()){
                    case R.id.etapa:
                        intEtapas = position+1;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        comboAdapterSalud = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, saludes);
        salud.setAdapter(comboAdapterSalud);
        salud.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getId()){
                    case R.id.salud:
                        intSaludes = position+1;
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
                .baseUrl("http://granjapp2.appspot.com/animal/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        client = retrofit.create(WebServiceClient.class);

        ActionBar actBar = getSupportActionBar();
        actBar.setTitle("Editar "+txtName);
    }

    public static boolean isNoEmpty(Bundle extras){
        if (extras != null)
            return true;
        else
            return false;
    }


    public static boolean isNoNull(String call){
        if (call != null)
            return true;
        else
            return false;
    }

    public static boolean isaNumber(String number){
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException excepcion) {
            return false;
        }
    }

    private void updateAnimal(String name, int etapa, int machos, int hembras, int salud){
        Animal animal = new Animal(name, nacimiento, 3, hembras, machos, salud, etapa, 3 );
        Call<Animal> call = client.putAnimal(id,animal);
        call.enqueue(new Callback<Animal>() {
            @Override
            public void onResponse(Call<Animal> call, Response<Animal> response) {
                Animal actividadResponse = response.body();
            }

            @Override
            public void onFailure(Call<Animal> call, Throwable t) {

            }
        });
    }

    public void siguiente (View v){
        Intent intent = new Intent(this, MainActivity.class);
        String name1 = name.getText().toString();
        //String nacimiento1 = nacimiento;
        int hembras1 = Integer.parseInt(hembras.getText().toString());
        int machos1 = Integer.parseInt(machos.getText().toString());
        int salud1 = intSaludes;
        int etapa1 = intEtapas;

        updateAnimal(name1, 2, machos1, hembras1, 2);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
