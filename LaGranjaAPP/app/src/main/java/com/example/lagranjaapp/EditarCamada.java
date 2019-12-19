package com.example.lagranjaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EditarCamada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_camada);

        String id = "";
        String nacimiento = "";
        String machos = "";
        String hembras = "";
        String estadoSalud = "";
        String etapa = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            id = extras.getString("id");
            nacimiento= extras.getString("nacimiento");
            hembras = extras.getString("hembras");
            machos = extras.getString("machos");
            estadoSalud = extras.getString("estadoSalud");
            etapa = extras.getString("etapa");
        }

        TextView prueba = (TextView) findViewById(R.id.nombreCamada);
        prueba.setText(id);

        TextView prueba2 = (TextView) findViewById(R.id.nacimiento);
        prueba2.setText(nacimiento);

        TextView prueba3 = (TextView) findViewById(R.id.hembras);
        prueba3.setText(hembras);

        TextView prueba4 = (TextView) findViewById(R.id.machos);
        prueba4.setText(machos);

        TextView prueba5 = (TextView) findViewById(R.id.estadoSalud);
        prueba5.setText(estadoSalud);

        TextView prueba6= (TextView) findViewById(R.id.etapa);
        prueba6.setText(etapa);

    }

    public void editar (View v){
        Intent intent = new Intent(this, Camada.class);
        startActivity(intent);
    }
}