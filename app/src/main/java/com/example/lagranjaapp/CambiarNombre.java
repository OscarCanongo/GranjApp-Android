package com.example.lagranjaapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.lagranjaapp.model.Usuario;
import com.example.lagranjaapp.ui.login.LoginActivity;
import com.example.lagranjaapp.webService.WebServiceClient;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CambiarNombre extends AppCompatActivity {
    private String id ="", nombre = "", apel = "";
    private EditText editNombre, editApel;
    private WebServiceClient client;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private Retrofit retrofit;
    private Usuario usuario2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_nombre);
        Bundle extras = getIntent().getExtras();
        if (isNoEmpty(extras)) {
            id = extras.getString("id");
            nombre = extras.getString("nombre");
            apel = extras.getString("apellido");
        }
        ActionBar actBar = getSupportActionBar();
        actBar.setTitle("Editar Nombre");

        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://granjapp2.appspot.com/users/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        client = retrofit.create(WebServiceClient.class);
        getUsuario1(id);

        editNombre = (EditText) findViewById(R.id.editNombre);
        editApel = (EditText) findViewById(R.id.editApel);

        editNombre.setText(nombre);
        editApel.setText(apel);
    }

    public void cambiarName(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        String name1 = editNombre.getText().toString();
        String apelEditado = editApel.getText().toString();
        updateNombre(name1, apelEditado);
        startActivity(intent);
    }

    public static boolean isNoEmpty(Bundle extras){
        if (extras != null)
            return true;
        else
            return false;
    }

    private void updateNombre(String name, String last){
        Usuario usuario = usuario2;
        usuario.setName(name);
        usuario.setLastname(last);
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

    public void getUsuario1(String id){
        Call<Usuario> call = client.getUsuario(id);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                usuario2 = response.body();
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                Log.d("TAG1", "Error: " + t.getMessage());
            }
        });
    }
}
