package com.example.lagranjaapp;

import androidx.annotation.RequiresApi;
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
import android.widget.Spinner;

import com.example.lagranjaapp.model.Aplicacion;
import com.example.lagranjaapp.model.DataAplicacion;
import com.example.lagranjaapp.model.DataCultivos;
import com.example.lagranjaapp.model.DataRecursos;
import com.example.lagranjaapp.model.Planting;
import com.example.lagranjaapp.model.Recurso;
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

public class AgregarAplicacion extends AppCompatActivity {

    private EditText cultivo;
    private WebServiceClient client;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private Retrofit retrofit;
    private int id, idRecurso;
    private DatePicker fecha;
    private Spinner recurso;
    private ArrayList<String> recursosArr = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_aplicacion);

        fecha = (DatePicker) findViewById(R.id.fechaRecurso);
        recurso = findViewById(R.id.nameRecurso);

        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://granjapp2.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        client = retrofit.create(WebServiceClient.class);

        lanzarPeticion(new OnRecursosResponse() {
            @Override
            public void recursos(List<DataRecursos> recursos) {
                for (int i=0; i<recursos.size();i++)
                    recursosArr.add(recursos.get(i).getName());
                ArrayAdapter<CharSequence> comboAdapter = new ArrayAdapter(AgregarAplicacion.this,android.R.layout.simple_spinner_item,recursosArr);
                comboAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                recurso.setAdapter(comboAdapter);
                recurso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        idRecurso = Integer.parseInt(recursos.get(position).getId());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }

    private void createPosts(String fecha, int recursos){

        Aplicacion aplicacion = new Aplicacion(fecha,3,recursos);
        Call<Aplicacion> call = client.postAplicaciones(aplicacion);

        call.enqueue(new Callback< Aplicacion>() {
            @Override
            public void onResponse(Call< Aplicacion> call, Response< Aplicacion> response) {
                Aplicacion aplicacionResponse = response.body();
            }

            @Override
            public void onFailure(Call<Aplicacion> call, Throwable t) {

            }
        });
    }

    public void agregarAplicacion(View v){
        Intent intent = new Intent(this, MainActivity.class);

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
        int recurso1 = idRecurso;

        createPosts(fecha1, recurso1);


        startActivity(intent);
    }

    private void lanzarPeticion(OnRecursosResponse callback){
        Call<List<DataRecursos>> call = client.getRecursos();
        call.enqueue(new Callback<List<DataRecursos>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<DataRecursos>>  call, Response<List<DataRecursos>> response) {
                if(!response.isSuccessful()){
                    //Lance un toast
                }

                callback.recursos((response.body()));

            }

            @Override
            public void onFailure(Call<List<DataRecursos>> call, Throwable t) {
                Log.d("TAG1", "Error: " + t.getMessage());
            }
        });
    }
}
