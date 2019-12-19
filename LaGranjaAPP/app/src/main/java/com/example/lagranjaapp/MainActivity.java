package com.example.lagranjaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.Animales, R.id.Cultivos, R.id.Tareas, R.id.Tabla, R.id.Mas)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public void siguiente (View v){
        Intent intent = new Intent(this, AgregarTarea.class);
        startActivity(intent);
    }

    public void editar (View v){
        Intent intent = new Intent(this, EditarTarea.class);
        startActivity(intent);
    }

    public void zonas (View v){
        Intent intent = new Intent(this, Zonas.class);
        startActivity(intent);
    }

    public void subzona (View v){
        Intent intent = new Intent(this, Subzona.class);
        startActivity(intent);
    }

    public void usuario (View v){
        Intent intent = new Intent(this, Usuario.class);
        startActivity(intent);
    }

}
