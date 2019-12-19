package com.example.lagranjaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Zonas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonas);
    }

    public void abonos (View v){
        Intent intent = new Intent(this, Abonos.class);
        startActivity(intent);
    }

    public void cultivos (View v){
        Intent intent = new Intent(this, Cultivos.class);
        startActivity(intent);
    }

    public void insecticida (View v){
        Intent intent = new Intent(this, Insecticida.class);
        startActivity(intent);
    }

    public void fungicida (View v){
        Intent intent = new Intent(this, Fungicida.class);
        startActivity(intent);
    }

    public void repelente (View v){
        Intent intent = new Intent(this, Repelente.class);
        startActivity(intent);
    }

}
