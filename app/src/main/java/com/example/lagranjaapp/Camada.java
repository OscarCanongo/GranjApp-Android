package com.example.lagranjaapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Camada extends AppCompatActivity {
    String id = "";
    String name = "";
    String nacimiento = "";
    String machos = "";
    String hembras = "";
    String estadoSalud = "";
    String etapa = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camada);



        Bundle extras = getIntent().getExtras();
        if (isNoEmpty(extras)) {
            if (isNoNull(extras.getString("id")) && isNoNull(extras.getString("name")) && isNoNull(extras.getString("nacimiento")) && isNoNull(extras.getString("hembras")) && isNoNull(extras.getString("machos"))) {
                id = extras.getString("id");
                name = extras.getString("name");
                nacimiento = extras.getString("nacimiento");
                hembras = extras.getString("hembras");
                machos = extras.getString("machos");
            }
            if (isaNumber(extras.getString("salud"))) {
                if (Integer.parseInt(extras.getString("salud")) == 1) {
                    estadoSalud = "Saludable";
                } else {
                    estadoSalud = "Enfermo";
                }
            }
            if (isaNumber(extras.getString("etapa"))) {
                if (Integer.parseInt(extras.getString("etapa")) == 1) {
                    etapa = "Inicio";
                } else if (Integer.parseInt(extras.getString("etapa")) == 2) {
                    etapa = "Crecimiento";
                } else {
                    etapa = "Engorda";
                }
            }

        }

        TextView prueba2 = (TextView) findViewById(R.id.nacimiento);
        prueba2.setText(nacimiento);

        TextView prueba3 = (TextView) findViewById(R.id.descripcion);
        prueba3.setText(hembras);

        TextView prueba4 = (TextView) findViewById(R.id.machos);
        prueba4.setText(machos);

        TextView prueba5 = (TextView) findViewById(R.id.estadoSalud);
        prueba5.setText(estadoSalud);

        TextView prueba6= (TextView) findViewById(R.id.etapa);
        prueba6.setText(etapa);

        ActionBar actBar = getSupportActionBar();
        actBar.setTitle(name);
    }

    public static boolean isNoEmpty(Bundle extras){
        if (extras != null)
            return true;
        else
            return false;
    }


    public static boolean isNoNull(String call){
        if (call != null)
            return true;
        else
            return false;
    }

    public static boolean isaNumber(String number){
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException excepcion) {
            return false;
        }
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, EditarCamada.class);
        intent.putExtra("id", id);
        intent.putExtra("nacimiento", nacimiento);
        intent.putExtra("txtName", name);
        intent.putExtra("txtMachos", machos);
        intent.putExtra("txtHembras", hembras);
        startActivity(intent);
    }

}