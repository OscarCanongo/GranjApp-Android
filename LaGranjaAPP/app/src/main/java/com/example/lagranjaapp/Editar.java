package com.example.lagranjaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Editar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
    }

    public void editar (View v){
        Intent intent = new Intent(this, EditarTarea.class);
        startActivity(intent);
    }
}
