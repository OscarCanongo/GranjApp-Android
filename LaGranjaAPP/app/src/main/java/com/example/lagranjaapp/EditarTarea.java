package com.example.lagranjaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class EditarTarea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_tarea);
    }

    public void editarTarea (View v){
        Intent intent = new Intent(this, Editar.class);
        startActivity(intent);
    }
}
