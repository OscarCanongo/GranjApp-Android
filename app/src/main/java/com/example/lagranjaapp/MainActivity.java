package com.example.lagranjaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lagranjaapp.model.Actividad;
import com.example.lagranjaapp.ui.login.LoginActivity;
import com.example.lagranjaapp.webService.WebServiceClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    private String id ="";
    private EditText nombre;
    private WebServiceClient client;
    private HttpLoggingInterceptor loggingInterceptor;
    private OkHttpClient.Builder httpClientBuilder;
    private Retrofit retrofit;
    private Actividad actividad2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.Animales, R.id.Cultivos, R.id.Tareas, R.id.Tabla, R.id.Perfil)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public void verEquipo (View v){
        Intent intent = new Intent(this, VerEquipo.class);
        startActivity(intent);
    }

    public void siguiente (View v){
        Intent intent = new Intent(this, AgregarTarea.class);
        startActivity(intent);
    }

    public void aplicaciones (View v){
        Intent intent = new Intent(this, Aplicaciones.class);
        startActivity(intent);
    }

    public void agregarAnimal (View v){
        Intent intent = new Intent(this, AgregarAnimal.class);
        startActivity(intent);
    }

    public void editar (View v){
        Intent intent = new Intent(this, EditarTarea.class);
        startActivity(intent);
    }

    public void subzona (View v){
        Intent intent = new Intent(this, Subzona.class);
        startActivity(intent);
    }

    public void usuario (View v){
        Intent intent = new Intent(this, CambiarNombre.class);
        //TextView nombre = findViewById(R.id.nombreUsuario);
        TextView id = findViewById(R.id.idTarea);
        //String nombreC = nombre.getText().toString();
        String id1 = id.getText().toString();
        TextView txtnombre = findViewById(R.id.txtNombreP);
        TextView txtapel = findViewById(R.id.txtApellidoP);
        String nombreUser = txtnombre.getText().toString();
        String apellido = txtapel.getText().toString();

        //intent.putExtra("nombreC", nombreC);
        intent.putExtra("id", id1);
        intent.putExtra("nombre", nombreUser);
        intent.putExtra("apellido", apellido);
        startActivity(intent);
    }

    public void usuarioEquipo (View v){
        Intent intent = new Intent(this, InformacionEquipo.class);
        TextView nombre = findViewById(R.id.nombreUsuario);
        TextView id = findViewById(R.id.idTarea);
        String nombreC = nombre.getText().toString();
        String id1 = id.getText().toString();
        intent.putExtra("nombreC", nombreC);
        intent.putExtra("id", id1);
        startActivity(intent);
    }

    public void cerrarSesion (View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void detallesTarea(View v){
        //TextView id = findViewById(R.id.idTarea);
        Intent intent = new Intent(this, PopDetalles.class);
        TextView name = findViewById(R.id.actividadName);
        //System.out.println("EL NOMBRE ES " + id.toString());
        TextView detalles = findViewById(R.id.descripcion);
        TextView complejidad = findViewById(R.id.complejidad);
        TextView usuario = findViewById(R.id.tareaAsignacion);
        intent.putExtra("name", name.getText());
        intent.putExtra("detalles", detalles.getText());
        intent.putExtra("complejidad", complejidad.getText());
        intent.putExtra("usuario", usuario.getText());
        startActivity(intent);
    }

    public void calificarTarea(View v){
        Intent intent = new Intent(this, CalificarActividad.class);
        startActivity(intent);
    }
}
