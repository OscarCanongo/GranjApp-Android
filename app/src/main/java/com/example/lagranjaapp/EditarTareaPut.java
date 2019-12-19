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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.example.lagranjaapp.model.Actividad;
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

public class EditarTareaPut extends AppCompatActivity {

    private EditText nombre, descripcion;
    private Spinner usuarioSpin;
    private RatingBar complejidad;
    private DatePicker fecha;
    private WebServiceClient client;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private Retrofit retrofit;
    private String id = "", actividad = "", fechaI = "" , desc = "", user = "";
    private int compl = 1;
    private ArrayList<String> usuarioArr = new ArrayList<>();
    private ArrayAdapter comboAdapter;
    private int intUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_tarea_put);

        ActionBar actBar = getSupportActionBar();
        actBar.setTitle("Editar Tarea");

        Bundle extras = getIntent().getExtras();
        if (isNoEmpty(extras)) {
            id = extras.getString("id");
            actividad = extras.getString("nombre");
            fechaI = extras.getString("fecha");
            desc = extras.getString("desc");
            compl = Integer.parseInt(extras.getString("compl"));
            user = extras.getString("idUser");
        }

        nombre = (EditText) findViewById(R.id.editNombre);
        nombre.setText(actividad);
        fecha = (DatePicker) findViewById(R.id.fechaInicio);
        descripcion = (EditText) findViewById(R.id.descripción);
        descripcion.setText(desc);
        complejidad = (RatingBar) findViewById(R.id.complejidadRate);
        complejidad.setProgress(compl);
        usuarioSpin = findViewById(R.id.usuario);

        lanzarPeticion(new OnUsuarioResponse() {
            @Override
            public void usuario(List<DataUsuario> usuario) {
                for (int i=0; i<usuario.size();i++)
                    usuarioArr.add(usuario.get(i).getName());
                ArrayAdapter<CharSequence> comboAdapter = new ArrayAdapter(EditarTareaPut.this,android.R.layout.simple_spinner_item,usuarioArr);
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

    private void updateActividad(String name, String fechaInicio, String descripcion, int complejidad, int usuario){
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://granjapp2.appspot.com/activities/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        client = retrofit.create(WebServiceClient.class);
        Actividad actividad = new Actividad(name, fechaInicio, 2, descripcion, complejidad, usuario, 1, 3 );
        Call<Actividad> call = client.putActividad(id,actividad);
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

    public static boolean isNoEmpty(Bundle extras){
        if (extras != null)
            return true;
        else
            return false;
    }

    public void siguiente (View v){
        Intent intent = new Intent(this, MainActivity.class);
        String name1 = nombre.getText().toString();
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

        updateActividad(name1, fecha1,  descripcion1,complejidad1, usuario1);
        startActivity(intent);
    }

    private void lanzarPeticion(OnUsuarioResponse callback){
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
}
