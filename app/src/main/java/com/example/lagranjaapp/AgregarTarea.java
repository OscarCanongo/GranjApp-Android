package com.example.lagranjaapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lagranjaapp.model.Actividad;
import com.example.lagranjaapp.model.DataAplicacion;
import com.example.lagranjaapp.model.DataUsuario;
import com.example.lagranjaapp.webService.WebServiceClient;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgregarTarea extends AppCompatActivity {

    private EditText name, descripcion;
    private Spinner usuarioSpin;
    private DatePicker fecha;
    private RatingBar complejidad;
    private WebServiceClient client;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private Retrofit retrofit;
    private ArrayList<String> usuarioArr = new ArrayList<>();
    private ArrayAdapter comboAdapter;
    private int intUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tarea);


        name = (EditText) findViewById(R.id.name);
        fecha = (DatePicker) findViewById(R.id.fecha);
        descripcion = (EditText) findViewById(R.id.descripcion);
        complejidad = (RatingBar) findViewById(R.id.complejidadRate);
        usuarioSpin = findViewById(R.id.usuarioSpin);


        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://granjapp2.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        client = retrofit.create(WebServiceClient.class);

        lanzarPeticion(new OnUsuarioResponse() {
            @Override
            public void usuario(List<DataUsuario> usuario) {
                for (int i=0; i<usuario.size();i++)
                    usuarioArr.add(usuario.get(i).getName());
                ArrayAdapter<CharSequence> comboAdapter = new ArrayAdapter(AgregarTarea.this,android.R.layout.simple_spinner_item,usuarioArr);
                comboAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                usuarioSpin.setAdapter(comboAdapter);
                usuarioSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        intUser = Integer.parseInt(usuario.get(position).getId());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });

    }

    private void createPosts(String name, String fecha, String descripcion, int complejidad, int usuario){

        Actividad actividad = new Actividad(name, fecha, 2,descripcion, complejidad, usuario,1,3);
        Call<Actividad> call = client.postActividades(actividad);

        call.enqueue(new Callback<Actividad>() {
            @Override
            public void onResponse(Call<Actividad> call, Response<Actividad> response) {
                Actividad actividadResponse = response.body();
            }

            @Override
            public void onFailure(Call<Actividad> call, Throwable t) {

            }
        });
    }

    private void lanzarPeticion(OnUsuarioResponse callback){
        Call<List<DataUsuario>> call = client.getUsuarios();
        call.enqueue(new Callback<List<DataUsuario>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<DataUsuario>>  call, Response<List<DataUsuario>> response) {
                if(!response.isSuccessful()){
                    //Lance un toast
                }

                callback.usuario((response.body()));

            }

            @Override
            public void onFailure(Call<List<DataUsuario>> call, Throwable t) {
                Log.d("TAG1", "Error: " + t.getMessage());
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
        String descripcion1 = descripcion.getText().toString();
        int complejidad1 = 1;
        if(complejidad.getProgress() > 0) {
            complejidad1 = complejidad.getProgress();
        } else {
            complejidad1 = 1;
        }
        int usuario1 = intUser;

        createPosts(name1, fecha1, descripcion1, complejidad1, usuario1);


        startActivity(intent);
    }

}