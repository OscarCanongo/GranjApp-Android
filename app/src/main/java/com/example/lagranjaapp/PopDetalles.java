package com.example.lagranjaapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PopDetalles extends AppCompatActivity {
    private String name;
    private String detalles;
    private String complejidad;
    private String usuario;
    TextView nombre;
    TextView detalles1;
    RatingBar complejidad1;
    TextView usuario1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_detalles);

        Bundle extras = getIntent().getExtras();
        if (isNoEmpty(extras)) {
            name = extras.getString("name");
            detalles = extras.getString("detalles");
            complejidad = extras.getString("complejidad");
            usuario = extras.getString("usuario");
        }
        System.out.println("EL NAME ES " +name);
        nombre = (TextView) findViewById(R.id.txtNombreTarea);
        nombre.setText(name);
        detalles1 = (TextView) findViewById(R.id.txtDescTarea);
        detalles1.setText(detalles);
        //complejidad1 = (RatingBar) findViewById(R.id.txtComplejidadTarea);
        //complejidad1.setNumStars(Integer.parseInt(complejidad));
        usuario1 = (TextView) findViewById(R.id.txtAsigTarea);
        usuario1.setText(usuario);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(width,(int) (height*0.6));
    }

    public void tareas(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public static boolean isNoEmpty(Bundle extras){
        if (extras != null)
            return true;
        else
            return false;
    }
}
