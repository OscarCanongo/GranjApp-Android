package com.example.lagranjaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Cultivos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivos);
    }

    public void tipoCultivo (View v){
        Intent intent = new Intent(this, TipoCultivo.class);
        startActivity(intent);
    }
}
