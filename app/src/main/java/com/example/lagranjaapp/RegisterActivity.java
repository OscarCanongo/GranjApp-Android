package com.example.lagranjaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lagranjaapp.model.Rol;
import com.example.lagranjaapp.ui.login.LoginActivity;
import com.example.lagranjaapp.webService.WebServiceClient;
import com.example.lagranjaapp.model.Usuario;

import java.security.MessageDigest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    private EditText nombre;
    private EditText apellido;
    private EditText correoNuevo;
    private EditText password, passConf;
    private TextView txtMis, txtShort, txtCorreo;
    private WebServiceClient client;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actBar = getSupportActionBar();
        actBar.setTitle("Registrarse");

        nombre = (EditText) findViewById(R.id.nombre);
        apellido = (EditText) findViewById(R.id.fechaInicio);
        correoNuevo = (EditText) findViewById(R.id.descripción);
        password = (EditText) findViewById(R.id.passwordReg);
        passConf = findViewById(R.id.passConf);
        txtMis = findViewById(R.id.pwMismatch);
        txtShort = findViewById(R.id.pwShort);
        txtCorreo = findViewById(R.id.correoInval);

        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://granjapp2.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        client = retrofit.create(WebServiceClient.class);
    }

    private void createPosts(String name, String lastname, String correo, String password, OnRegisterResponse callback){

        Usuario usuario = new Usuario(name, lastname, correo,1,1, password);
        Call<Usuario> call = client.postUsuario(usuario);

        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                callback.usuario((response.body()));
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });
    }

    private void createPostsRol(int id, int role){

        Rol rol = new Rol(id, role);
        Call<Rol> call = client.postRol(rol);

        call.enqueue(new Callback<Rol>() {
            @Override
            public void onResponse(Call<Rol> call, Response<Rol> response) {
                Rol rolResponse = response.body();
            }

            @Override
            public void onFailure(Call<Rol> call, Throwable t) {

            }
        });
    }

    public void siguiente (View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        String name1 = nombre.getText().toString();
        String apellido1 = apellido.getText().toString();
        String correoNuevo1 = correoNuevo.getText().toString();
        String password1 = password.getText().toString();
        System.out.println(password1);
        String password2 = passConf.getText().toString();
        System.out.println();

        if(correoNuevo1.contains("@")) {
            txtCorreo.setVisibility(View.INVISIBLE);
            if (password1.length() > 7) {
                if (password1.equals(password2)) {
                    createPosts(name1, apellido1, correoNuevo1, sha256(password1), new OnRegisterResponse() {
                        @Override
                        public void usuario(Usuario usuario) {
                            Usuario usuario2 = usuario;
                            int x = usuario2.getId();
                            txtMis.setVisibility(View.INVISIBLE);
                            txtShort.setVisibility(View.INVISIBLE);
                            txtCorreo.setVisibility(View.INVISIBLE);
                            createPostsRol(x, 3);
                        }
                    });
                    startActivity(intent);
                } else {
                    txtMis.setVisibility(View.VISIBLE);
                    txtShort.setVisibility(View.INVISIBLE);
                }
            } else {
                txtShort.setVisibility(View.VISIBLE);
                txtMis.setVisibility(View.INVISIBLE);
            }
        } else {
            txtCorreo.setVisibility(View.VISIBLE);
            txtShort.setVisibility(View.INVISIBLE);
            txtMis.setVisibility(View.INVISIBLE);
        }
    }

    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
