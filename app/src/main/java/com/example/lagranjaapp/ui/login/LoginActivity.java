package com.example.lagranjaapp.ui.login;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lagranjaapp.Adapter.UsuarioAdapter;
import com.example.lagranjaapp.MainActivity;
import com.example.lagranjaapp.OnUsuarioResponse;
import com.example.lagranjaapp.R;
import com.example.lagranjaapp.RegisterActivity;
import com.example.lagranjaapp.model.DataUsuario;
import com.example.lagranjaapp.webService.WebServiceClient;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;

public class LoginActivity extends AppCompatActivity {
    EditText usernameEditText, passwordEditText;
    TextView userPass, pass, username, email;
    private RecyclerView recyclerView;
    private UsuarioAdapter adapter;
    private List<DataUsuario> usuarios;
    private Retrofit retrofit;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private WebServiceClient client;
    private boolean passwordIsValid;
    private String nombre, nombreC, correo, estrellas, id, password, cadena, apellido;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.password);
        userPass = findViewById(R.id.userPassInvalid);
        pass = findViewById(R.id.passTooShort);
        username = findViewById(R.id.nombreUsuario);
        email = findViewById(R.id.emailUsuario);
        passwordIsValid = false;
        setTitle("Iniciar Sesión");

        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://granjapp2.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();
        client = retrofit.create(WebServiceClient.class);

        lanzarPeticion(usernameEditText.getText().toString(), new OnUsuarioResponse() {
            @Override
            public void usuario(List<DataUsuario> usuario) {
                usuarios = usuario;
            }
        });
    }

    public void login(View v){

        cadena = passwordEditText.getText().toString();

        for (int i = 0; i<usuarios.size(); i++){
            if (usuarios.get(i).getCorreo().equals(usernameEditText.getText().toString()) && usuarios.get(i).getPassword().equals(sha256(cadena))){
                passwordIsValid = true;
                nombre = usuarios.get(i).getName();
                apellido = usuarios.get(i).getLastname();
                correo = usuarios.get(i).getCorreo();
                estrellas = usuarios.get(i).getEstrellas();
                id = usuarios.get(i).getId();
                password = usuarios.get(i).getPassword();
                //equipo = usuarios.get(i).getEquipo();
                break;
            }
            else{
                passwordIsValid = false;
            }
        }

        if(usernameEditText.getText().toString().length() == 0 || passwordEditText.getText().toString() == "" || !passwordIsValid){
            userPass.setVisibility(View.VISIBLE);
            pass.setVisibility(View.INVISIBLE);
        } else if(passwordEditText.getTextSize() < 7){
            userPass.setVisibility(View.INVISIBLE);
            pass.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(getApplicationContext(),"Bienvenido " + nombre,Toast.LENGTH_SHORT).show();

            if (passwordIsValid) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("apellido", apellido);
                intent.putExtra("correo", correo);
                intent.putExtra("estrellas", estrellas );
                intent.putExtra("id", id );
                //intent.putExtra("equipo", equipo );
                startActivity(intent);
            }
        }
    }

    public void register(View v){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void lanzarPeticion(String mail, OnUsuarioResponse callback){
        Call<List<DataUsuario>> call = client.getUsuarios();
        call.enqueue(new Callback<List<DataUsuario>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<DataUsuario>>  call, Response<List<DataUsuario>> response) {
                callback.usuario((response.body()));
            }

            @Override
            public void onFailure(Call<List<DataUsuario>> call, Throwable t) {
                Log.d("TAG1", "Error: " + t.getMessage());
            }
        });
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
