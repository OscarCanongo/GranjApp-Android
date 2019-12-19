package com.example.lagranjaapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Usuario1 extends AppCompatActivity {
    private String nombreC = "", id ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario1);
        Bundle extras = getIntent().getExtras();
        if (isNoEmpty(extras)) {
            nombreC = extras.getString("nombreC");
            id = extras.getString("id");
        }
        if (isNoNull(findViewById(R.id.nombreUserPerfil))){
            TextView username = findViewById(R.id.nombreUserPerfil);
            TextView id1 = findViewById(R.id.id);
            username.setText(nombreC);
            id1.setText(id);
        }
        ActionBar actBar = getSupportActionBar();
        actBar.setTitle("Mi Perfil");
    }

    public void cambiarNombre (View v){
        Intent intent = new Intent(this, CambiarNombre.class);
        TextView id = findViewById(R.id.id);
        String id1 = id.getText().toString();
        intent.putExtra("id", id1);
        startActivity(intent);
    }

    public static boolean isNoEmpty(Bundle extras){
        if (extras != null)
            return true;
        else
            return false;
    }

    public static boolean isNoNull(TextView extras){
        if (extras != null)
            return true;
        else
            return false;
    }
}
