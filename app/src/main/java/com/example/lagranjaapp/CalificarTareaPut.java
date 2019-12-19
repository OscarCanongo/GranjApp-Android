package com.example.lagranjaapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.lagranjaapp.model.Actividad;
import com.example.lagranjaapp.model.DataAplicacion;
import com.example.lagranjaapp.model.Usuario;
import com.example.lagranjaapp.webService.WebServiceClient;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CalificarTareaPut extends AppCompatActivity {

    private EditText nombre, fechaInicio, descripcion, usuario;
    private RatingBar complejidad;
    private WebServiceClient client;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private Retrofit retrofit;
    private String id = "", actividad1 = "", fecha = "" , desc = "", user = "", terminado="";
    private int compl = 1;
    private RatingBar calificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificar_tarea_put);

        ActionBar actBar = getSupportActionBar();
        actBar.setTitle("Editar Tarea");

        Bundle extras = getIntent().getExtras();
        if (isNoEmpty(extras)) {
            id = extras.getString("id");
            actividad1 = extras.getString("nombre");
            fecha = extras.getString("fecha");
            desc = extras.getString("desc");
            compl = Integer.parseInt(extras.getString("compl"));
            user = extras.getString("idUser");
            terminado = extras.getString("terminado");
        }


        calificacion = findViewById(R.id.complejidadRate2);


    }

    private void updateActividad(int calificacion1){
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://granjapp2.appspot.com/activities/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        client = retrofit.create(WebServiceClient.class);
        Actividad actividad = new Actividad(actividad1, fecha, 1, desc, compl, Integer.parseInt(user), calificacion1, 3 );
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

    private void lanzarPeticion(String id, OnUserResponse callback){
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://granjapp2.appspot.com/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        client = retrofit.create(WebServiceClient.class);
        Call<Usuario> call = client.getUsuario(id);
        call.enqueue(new Callback<Usuario>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<Usuario>  call, Response<Usuario> response) {
                callback.usuario((response.body()));
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("TAG1", "Error: " + t.getMessage());
            }
        });
    }

    private void updateStars(Usuario usuario, int calificacin1, String id){
        int x = usuario.getEstrellas();
        usuario.setEstrellas((x) + (calificacin1*compl));
        Call<Usuario> call = client.putUsuario(id,usuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                Usuario usuarioResponse = response.body();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });
    }


    public void siguiente (View v){
        Intent intent = new Intent(this, MainActivity.class);
        int calificacion1 = calificacion.getProgress();
        updateActividad(calificacion1);
        lanzarPeticion(user, new OnUserResponse() {
            @Override
            public void usuario(Usuario usuario) {
                Usuario usuario2 = usuario;
                updateStars(usuario2,calificacion1,user);
            }
        });
        startActivity(intent);
    }

    public static boolean isNoEmpty(Bundle extras){
        if (extras != null)
            return true;
        else
            return false;
    }


}
