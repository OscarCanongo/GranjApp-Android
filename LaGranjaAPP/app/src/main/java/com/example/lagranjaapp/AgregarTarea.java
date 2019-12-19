package com.example.lagranjaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lagranjaapp.ui.home.HomeFragment;

public class AgregarTarea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tarea);
    }

    public void agregar (View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
